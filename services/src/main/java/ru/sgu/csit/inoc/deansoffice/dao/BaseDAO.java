package ru.sgu.csit.inoc.deansoffice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 11, 2010
 * Time: 10:36:13 AM
 */
public interface BaseDAO<T, ID extends Serializable> {
    void save(T entity);

    List<T> findAll(Class<T> aClass);
}