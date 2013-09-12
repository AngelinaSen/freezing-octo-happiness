package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.Article;


public interface ArticleDAO extends AbstractDAO<Article> {

    Article getArticleById(long id);

    List<Article> getAllArticles();

}
