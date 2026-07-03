package org.jesperancinha.titletextadder.api.dao;

import java.io.Serializable;

public interface TitleDaoInterface<T, Id extends Serializable> {

	public void persist(T entity);

	public void update(T entity);

	public void delete(T entity);

}