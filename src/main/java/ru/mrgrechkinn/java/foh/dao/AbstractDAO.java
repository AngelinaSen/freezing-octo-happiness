package ru.mrgrechkinn.java.foh.dao;

import java.util.List;

import ru.mrgrechkinn.java.foh.model.Article;
import ru.mrgrechkinn.java.foh.model.Entity;


public interface AbstractDAO<T extends Entity> {

    boolean save(Article articleEntity);

    //boolean delete(T userEntity);	

	boolean delete(Article article);

	Entity getArticleById(long id);

	List<Entity> getAllArticles();

	boolean save(Entity entity);

	boolean delete(Entity userEntity);

}
