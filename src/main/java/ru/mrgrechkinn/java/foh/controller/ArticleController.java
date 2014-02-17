package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.log4j.Logger;

import ru.mrgrechkinn.java.foh.dao.ArticleDAO;
import ru.mrgrechkinn.java.foh.dao.ArticleDAOSql;
import ru.mrgrechkinn.java.foh.model.Article;
import ru.mrgrechkinn.java.foh.view.ArticleView;
import ru.mrgrechkinn.java.foh.view.UserView;

public class ArticleController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(ArticleController.class);

    public ArticleView parentWorkplaceView;

    public static String login;

    public ArticleController(ArticleView workplaceView) {
        parentWorkplaceView = workplaceView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == parentWorkplaceView.buttonCreateArticle) {
            onCreateArticle();
        }
        if (e.getSource() == parentWorkplaceView.buttonLogOut) {
            parentWorkplaceView.setVisible(false);
            parentWorkplaceView.dispose();
            new UserView();
        }
        if (e.getSource() == parentWorkplaceView.create) {
            onCreateArticle();
        }
        if (e.getSource() == parentWorkplaceView.exit) {
            parentWorkplaceView.setVisible(false);
            parentWorkplaceView.dispose();
            new UserView();
        }
    }

    private void onCreateArticle() {
        String displayFieldSubject = parentWorkplaceView.fieldSubject.getText();
        String displayFieldContent = parentWorkplaceView.fieldContent.getText();

        ArticleDAO articledao = new ArticleDAOSql();
        Article article = new Article();

        if (displayFieldSubject.isEmpty() || displayFieldContent.isEmpty()) {
            LOG.info("field is empty");
        } else {
            article.setSubject(displayFieldSubject);
            article.setContent(displayFieldContent);
            article.setAuthor(login);
            articledao.save(article);

            parentWorkplaceView.fieldSubject.setText("");
            parentWorkplaceView.fieldContent.setText("");
        }

    }

}
