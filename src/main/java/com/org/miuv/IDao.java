package com.org.miuv;

import java.util.List;

/**
 *
 * @author alsorc
 * @param <T>
 */
public interface IDao<T> {
    
    public boolean insertRecord(T t);
    public boolean deleteRecord(T t);
    public boolean updateRecord(T t);
    public List<T> getRecords();
    public T getOneRecord(T t);
    
    
}
