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

import ru.mrgrechkinn.java.foh.model.Article;

public class ArticleDAOSql implements ArticleDAO {

    private static final Logger LOG = Logger.getLogger(ArticleDAOSql.class);

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASS = "";

    public ArticleDAOSql() {
        try {
            Class.forName(DRIVER);
            init();
        } catch (ClassNotFoundException e) {
            LOG.error(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(Article article) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("insert into article values(default, ?, ?, ?)");
            statement.setString(1, article.getSubject());
            statement.setString(2, article.getContent());
            statement.setString(3, article.getAuthor());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Article article) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from article where subject = ?");
            statement.setString(1, article.getSubject());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getAllArticle() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<Article>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from article");
            if (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setSubject(resultSet.getString("subject"));
                article.setContent(resultSet.getString("content"));
                article.setAuthor(resultSet.getString("author"));
                articles.add(article);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return articles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article getArticleById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from article where id = ?");
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getLong("id"));
                article.setSubject(resultSet.getString("subject"));
                article.setContent(resultSet.getString("content"));
                article.setAuthor(resultSet.getString("author"));
                return article;
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return null;
    }

    public List<Article> findAllSubjectByAuthor(Article article) {
        List<Article> articles = new ArrayList<Article>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from article where author = ?");
            statement.setString(1, article.getAuthor());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Article newArticle = new Article();
                newArticle.setId(resultSet.getLong("id"));
                newArticle.setSubject(resultSet.getString("subject"));
                newArticle.setContent(resultSet.getString("content"));
                newArticle.setAuthor(resultSet.getString("author"));
                articles.add(article);
            }
        } catch (SQLException e) {
            LOG.error(e);
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }
        return articles;
    }

    private void init() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ARTICLE ("
                    + " ID INT PRIMARY KEY AUTO_INCREMENT,"
                    + " SUBJECT VARCHAR(255),"
                    + " CONTENT VARCHAR(255),"
                    + " AUTHOR VARCHAR(255),"
                    + " FOREIGN KEY (AUTHOR) REFERENCES USER (LOGIN))");
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
