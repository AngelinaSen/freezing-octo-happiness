package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.Article;

public interface ArticleDAO {
    
    boolean save(Article article);
    
    boolean delete(Article article);
    
    List<Article> getAllArticle();
    
    Article getArticleById(long id);

}
