package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.mrgrechkinn.java.foh.dao.ArticleDAO;
import ru.mrgrechkinn.java.foh.dao.ArticleDAOSql;
import ru.mrgrechkinn.java.foh.model.Article;
import ru.mrgrechkinn.java.foh.view.UserView;
import ru.mrgrechkinn.java.foh.view.WorkplaceView;

public class WorkplaceController implements ActionListener {

    public WorkplaceView parentWorkplaceView;

    public static String login;

    public WorkplaceController(WorkplaceView workplaceView) {
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
    }

    private void onCreateArticle() {
        String displayFieldSubject = parentWorkplaceView.fieldSubject.getText();
        String displayFieldContent = parentWorkplaceView.fieldContent.getText();

        ArticleDAO articledao = new ArticleDAOSql();
        Article article = new Article();

        article.setSubject(displayFieldSubject);
        article.setContent(displayFieldContent);
        article.setAuthor(login);
        articledao.save(article);

        parentWorkplaceView.fieldSubject.setText("");
        parentWorkplaceView.fieldContent.setText("");
    }

}
