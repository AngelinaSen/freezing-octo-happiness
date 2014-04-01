package ru.mrgrechkinn.java.foh.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import ru.mrgrechkinn.java.foh.dao.ArticleDAO;
import ru.mrgrechkinn.java.foh.dao.ArticleDAOSql;
import ru.mrgrechkinn.java.foh.model.Article;

public class JTreeInit {

    public JTree init() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        JTree tree = new JTree(root);
        ArticleDAO articleDao = new ArticleDAOSql();
        List<Article> articles = new ArrayList<Article>();
        articles.addAll(articleDao.getAllArticle());
        Calendar calendar0 = Calendar.getInstance();
        if(articles.isEmpty()) {
            return tree;
        } else {
            calendar0.setTime(articles.get(0).getDate());
            DefaultMutableTreeNode sub0 = new DefaultMutableTreeNode(
                    calendar0.get(Calendar.YEAR));
            root.add(sub0);
            DefaultMutableTreeNode sub1 = new DefaultMutableTreeNode(
                    changeNumericFormat(calendar0.get(Calendar.MONTH)));
            sub0.add(sub1);
            DefaultMutableTreeNode sub2 = new DefaultMutableTreeNode(articles
                    .get(0).getSubject());
            sub1.add(sub2);
            Calendar calendar1 = Calendar.getInstance();
            for (int i = 1; i < articles.size(); i++) {
                calendar1.setTime(articles.get(i).getDate());
                if (calendar0.get(Calendar.YEAR) == calendar1.get(Calendar.YEAR)) {
                    if (calendar0.get(Calendar.MONTH) == calendar1
                            .get(Calendar.MONTH)) {
                        DefaultMutableTreeNode ssub2 = new DefaultMutableTreeNode(
                                articles.get(i).getSubject());
                        sub1.add(ssub2);
                    } else {
                        DefaultMutableTreeNode ssub1 = new DefaultMutableTreeNode(
                                changeNumericFormat(calendar1.get(Calendar.MONTH)));
                        DefaultMutableTreeNode ssub2 = new DefaultMutableTreeNode(
                                articles.get(i).getSubject());
                        sub1.add(ssub1);
                        ssub1.add(ssub2);
                    }
                } else {
                    DefaultMutableTreeNode ssub0 = new DefaultMutableTreeNode(
                            calendar1.get(Calendar.YEAR));
                    DefaultMutableTreeNode ssub1 = new DefaultMutableTreeNode(
                            changeNumericFormat(calendar1.get(Calendar.MONTH)));
                    DefaultMutableTreeNode ssub2 = new DefaultMutableTreeNode(
                            articles.get(i).getSubject());
                    root.add(ssub0);
                    ssub0.add(ssub1);
                    ssub1.add(ssub2);
                }
            }
            return tree;
        }
    }

    private static String changeNumericFormat(int number) {
        switch (number) {
            case 0:
                return "January";
            case 1:
                return "February";
            case 2:
                return "March";
            case 3:
                return "April";
            case 4:
                return "May";
            case 5:
                return "June";
            case 6:
                return "July";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "October";
            case 10:
                return "November";
            case 11:
                return "December";
            default:
                throw new IllegalArgumentException("Something strange happend");
        }
    }

}
