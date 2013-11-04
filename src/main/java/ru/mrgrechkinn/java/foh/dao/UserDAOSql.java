package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.User;

public class UserDAOSql implements UserDAO {

	public UserDAOSql() {
//		if ()
	}
	
	@Override
	public boolean save(User userEntity) {
		
		return false;
	}

	@Override
	public User getUserById(long id) {
		
		return null;
	}

	@Override
	public boolean delete(User userEntity) {
		
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		
		return null;
	}

}
