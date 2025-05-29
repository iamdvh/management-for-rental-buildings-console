package com.javacore.repository;

import java.util.List;

public interface JdbcRespository<T> {
	public List<T> findAll();
	public T findById(Long id);
	public List<T> findByCondition(String sql);
	public void insert(Object object);
	public void delete(Long id);
	public void update(Object object);
}
