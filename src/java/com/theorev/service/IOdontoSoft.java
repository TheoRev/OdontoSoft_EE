package com.theorev.service;

import java.util.List;

public interface IOdontoSoft<T> {

    public boolean create(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(T t) throws Exception;

    public List<T> findAll(String tabla, String limit, String orderBy) throws Exception;

    public T findById(Object... valrs) throws Exception;

    public List<T> findBy(Object... valrs) throws Exception;
}
