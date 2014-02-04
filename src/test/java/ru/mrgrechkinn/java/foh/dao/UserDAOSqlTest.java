package ru.mrgrechkinn.java.foh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.junit.Assert;
import org.junit.Test;

import ru.mrgrechkinn.java.foh.model.User;

public class UserDAOSqlTest {

    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:~/test";
    
    @Test
    public void testSave() {
        User user = new User();
        UserDAO userDao = new UserDAOSql();
        user.setLogin("zeus");
        user.setPassword("123");
        user.setFullName("GOD");
        userDao.save(user);
        
        User userDB = new User();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user where login = 'zeus'");
            if(resultSet.next()) {
                userDB.setLogin(resultSet.getString("login"));
                userDB.setPassword(resultSet.getString("pass"));
                userDB.setFullName(resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        
        Assert.assertEquals(user.getLogin(), userDB.getLogin());
        Assert.assertEquals(user.getPassword(), userDB.getPassword());
        Assert.assertEquals(user.getFullName(), userDB.getFullName());
        
    }
    
    @Test
    public void testFindUserByLogin() {
        UserDAO userDao = new UserDAOSql();
        
        User userDB = new User();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user where login = 'zeus'");
            if(resultSet.next()) {
                userDB.setLogin(resultSet.getString("login"));
                userDB.setPassword(resultSet.getString("pass"));
                userDB.setFullName(resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        
        User user = new User();
        user = userDao.findUserByLogin("zeus");
        
        Assert.assertEquals(userDB.getLogin(), user.getLogin());
        Assert.assertEquals(userDB.getPassword(), user.getPassword());
        Assert.assertEquals(userDB.getFullName(), user.getFullName());
        
    }
    
    @Test
    public void testDelete() {
        User user = new User();
        UserDAO userDao = new UserDAOSql();
        user.setLogin("zeus");
        user.setPassword("123");
        user.setFullName("GOD");
        userDao.delete(user);
        
        Assert.assertNull((User) userDao.findUserByLogin("zeus"));
    }
    
    @Test
    public void testGetAllUsers() {
        List<User> usersDB = new ArrayList<User>();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            User newUser = new User();
            while(resultSet.next()) {
                newUser.setLogin(resultSet.getString("login"));
                newUser.setPassword(resultSet.getString("pass"));
                newUser.setFullName(resultSet.getString("full_name"));
                usersDB.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        
        List<User> users = new ArrayList<User>();
        UserDAO userDao = new UserDAOSql();
        users.addAll(userDao.getAllUsers());
        
        Assert.assertEquals(usersDB.size(), users.size());
        
        //в цикле можно проверять?
        /* 
        for(int i = 0; i < usersDB.size(); i++) {
            Assert.assertEquals(usersDB.get(i), users.get(i));
        }*/
        
    }

}
