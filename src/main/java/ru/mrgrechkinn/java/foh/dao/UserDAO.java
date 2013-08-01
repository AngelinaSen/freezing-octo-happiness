package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.User;


/**
 * @author Eugene Rudenko
 */
public interface UserDAO {

    boolean save(User userEntity);

    User getUserById(long id);

    boolean delete(User userEntity);

    List<User> getAllUsers();

}
