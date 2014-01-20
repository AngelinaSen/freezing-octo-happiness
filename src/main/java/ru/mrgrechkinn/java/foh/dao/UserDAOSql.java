package ru.mrgrechkinn.java.foh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import ru.mrgrechkinn.java.foh.model.User;

public class UserDAOSql implements UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAOSql.class);
    
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:h2:~/test";
    private static final String DRIVER = "org.h2.Driver";
    
    public UserDAOSql() {
        try {
            Class.forName(DRIVER);
            init();
        } catch (ClassNotFoundException e) {
            LOG.fatal(e);
        }
    }
    
    @Override
    public boolean save(User newUser) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("insert into user values (?, ?, ?)");
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, newUser.getFullName());
            statement.executeUpdate();
            return true;
        } catch(SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean delete(User newUser) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from user where login = ?");
            statement.setString(1, newUser.getLogin());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while(resultSet.next()) {
                User newUser = new User();
                newUser.setLogin(resultSet.getString("login"));
                newUser.setPassword(resultSet.getString("pass"));
                newUser.setFullName(resultSet.getString("full_name"));
                users.add(newUser);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return users;
    }
    
    @Override
    public User findUserByLogin(String login) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * into user where login = ?");
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User newUser = new User();
                newUser.setLogin(resultSet.getString("login"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setFullName(resultSet.getString("full_name"));
                return newUser;
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        
        return null;
    }
    
    private void init() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS USER ("
                    + " LOGIN VARCHAR(255) PRIMARY KEY NOT NULL,"
                    + " PASS VARCHAR(255)," 
                    + " FULL_NAME VARCHAR(255))");
            
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
