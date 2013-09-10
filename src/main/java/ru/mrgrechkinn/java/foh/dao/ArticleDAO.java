package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.Entity;
import ru.mrgrechkinn.java.foh.model.User;


public interface ArticleDAO extends AbstractDAO<Entity> {

    Entity getArticleById(long id);

    List<Entity> getAllArticles();

    
}
