package ru.mrgrechkinn.java.foh.controller;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import ru.mrgrechkinn.java.foh.dao.ArticleDAOSql;
import ru.mrgrechkinn.java.foh.model.Article;
import ru.mrgrechkinn.java.foh.view.ArticleView;

public class JTreeListener implements TreeSelectionListener {

    public ArticleView parentWorkplaceView;

    public JTreeListener(ArticleView workplaceView) {
        parentWorkplaceView = workplaceView;
    }

    @Override
    public void valueChanged(TreeSelectionEvent se) {
        JTree tree = (JTree) se.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                .getLastSelectedPathComponent();
        ArticleDAOSql articleDao = new ArticleDAOSql();
        Article article = new Article();

        if (selectedNode.isLeaf()) {
            article = articleDao.getArticleBySubject(selectedNode.toString());
            parentWorkplaceView.fieldSubject.setText(article.getSubject());
            parentWorkplaceView.fieldContent.setText(article.getContent());
        }
    }

}
