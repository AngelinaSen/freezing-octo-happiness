package ru.mrgrechkinn.java.foh.dao;

import ru.mrgrechkinn.java.foh.model.Entity;


public interface AbstractDAO<T extends Entity> {

    boolean save(T entity);

    boolean delete(T userEntity);

}
