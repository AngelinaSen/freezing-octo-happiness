package ru.mrgrechkinn.java.foh.dao;

import java.util.Collection;

import ru.mrgrechkinn.java.foh.model.User;


/**
 * @author Eugene Rudenko
 */
public interface UserDAO {

    boolean save(User userEntity);

    User getUserById(long id);

    boolean delete(User userEntity);

    Collection<User> getAllUsers();

}
