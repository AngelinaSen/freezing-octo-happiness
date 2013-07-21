package ru.mrgrechkinn.java.foh.dao;

import java.util.Collection;

import ru.mrgrechkinn.java.foh.model.Entity;


/**
 * @author Eugene Rudenko
 */
public interface UserDAO {

    boolean save(Entity userEntity);

    Entity getUserById(long id);

    boolean delete(Entity userEntity);

    Collection<Entity> getAllUsers();

}
