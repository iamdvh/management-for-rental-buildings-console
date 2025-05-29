package com.javacore.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javacore.annotation.Column;
import com.javacore.annotation.Entity;
import com.javacore.annotation.Table;
import com.javacore.mapper.ResultSetMapper;
import com.javacore.repository.JdbcRespository;
import com.javacore.utils.ConnectionUtils;

public class SimpleRepository<T> implements JdbcRespository<T> {
	private Class<T> tClass;

	public SimpleRepository() {
		tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// search
		try {
			String name = null;
			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				name = table.name();
			}
			String query = "select * from " + name + "";
			con = ConnectionUtils.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			ResultSetMapper<T> resultSetMaper = new ResultSetMapper<>();
			return resultSetMaper.mapRow(rs, tClass);
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		}
		return new ArrayList<>();
	}

	@Override
	public T findById(Long id) {
		List<T> results = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// search
		try {
			String name = null;
			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				name = table.name();
			}
			String query = "select * from " + name + " where id = " + id;
			con = ConnectionUtils.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			ResultSetMapper<T> resultSetMaper = new ResultSetMapper<>();
			results = resultSetMaper.mapRow(rs, tClass);
			return results.size() > 0 ? results.get(0) : null;
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<T> findByCondition(String sql) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String name = null;
			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				name = table.name();
			}
			con = ConnectionUtils.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMapper<T> resultSetMaper = new ResultSetMapper<>();
			return resultSetMaper.mapRow(rs, tClass);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		}
		return null;
	}

	@Override
	public void insert(Object object) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionUtils.getConnection();
			StringBuilder sql = createSQLInsert();
			stmt = con.prepareStatement(sql.toString());
			Class<?> zClass = object.getClass();
			Field[] fields = zClass.getDeclaredFields();
			int para = 1;
			for (Field field : fields) {
				field.setAccessible(true);
				stmt.setObject(para, field.get(object));
				para++;
			}
			Class<?> parentClass = zClass.getSuperclass();
			int parentPara = fields.length + 1;
			while (parentClass != null) {
				for (Field field : parentClass.getDeclaredFields()) {
					field.setAccessible(true);
					stmt.setObject(parentPara, field.get(object));
					para++;
				}
				parentClass = parentClass.getSuperclass();
			}
			stmt.executeUpdate();

		} catch (IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}

	}

	private StringBuilder createSQLInsert() {
		String buildingName = null;
		if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
			Table table = tClass.getAnnotation(Table.class);
			buildingName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder value = new StringBuilder("");
		for (Field field : tClass.getDeclaredFields()) {
			if (fields.length() > 1) {
				fields.append(",");
				value.append(",");
			}
			if (field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				value.append("?");
			}
		}
		Class<?> parentClass = tClass.getSuperclass();
		while (parentClass != null) {
			for (Field field : parentClass.getDeclaredFields()) {
				if (fields.length() > 1) {
					fields.append(",");
					value.append(",");
				}
				if (field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					value.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}

		StringBuilder sql = new StringBuilder();
		sql.append("insert into " + buildingName + "(" + fields.toString() + ") value(" + value.toString() + ")");
		return sql;
	}

	@Override
	public void delete(Long id) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			String tableName = null;
			if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				tableName = table.name();
			}
			con = ConnectionUtils.getConnection();
			String sql = "Delete from "+tableName+" where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Object object) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionUtils.getConnection();
			StringBuilder sql = createSQLUpdate(object);
			stmt = con.prepareStatement(sql.toString());
			Class<?> zClass = object.getClass();
			int para = 1;
			for (Field field : zClass.getDeclaredFields()) {
				field.setAccessible(true);
				if(field.get(object) != null) {					
					stmt.setObject(para, field.get(object));
					para++;
				}
			}
			Class<?> parentClass = zClass.getSuperclass();
			int parentPara = para;
			Field[] fields = parentClass.getDeclaredFields();
			fields[0].setAccessible(true);
			stmt.setObject(parentPara, fields[0].get(object));
			stmt.executeUpdate();

		} catch (IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
	}

	private StringBuilder createSQLUpdate(Object object) {
		
		String buildingName = null;
		try {
			if (tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
				Table table = tClass.getAnnotation(Table.class);
				buildingName = table.name();
			}
			Class<?> zClass = object.getClass();
			StringBuilder fields = new StringBuilder("");
			for (Field field : zClass.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.get(object) != null) {
					if (fields.length() > 1) {
						fields.append(",");
					}
					if (field.isAnnotationPresent(Column.class) ) {
						Column column = field.getAnnotation(Column.class);
						fields.append(column.name() + " = ?");
					}
				}
			}
			StringBuilder sql = new StringBuilder();
			sql.append("update " + buildingName + " set " + fields.toString() +" where id = ?");
			return sql;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;

	}
}
