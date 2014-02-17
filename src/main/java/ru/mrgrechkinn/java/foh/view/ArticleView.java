package ru.mrgrechkinn.java.foh.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import ru.mrgrechkinn.java.foh.controller.ArticleController;
import ru.mrgrechkinn.java.foh.controller.JTreeInit;
import ru.mrgrechkinn.java.foh.controller.JTreeListener;

public class ArticleView extends JFrame implements View {

    public JButton buttonCreateArticle;
    public JButton buttonLogOut;
    public JTextField fieldSubject;
    public JTextArea fieldContent;
    public JMenuItem create;
    public JMenuItem exit;

    public ArticleView() {
        super("Work place");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(750, 450));
        setResizable(false);

        JPanel panelFrame = new JPanel();
        panelFrame.setLayout(new BoxLayout(panelFrame, BoxLayout.X_AXIS));

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

        JPanel panelSubject = new JPanel();
        panelSubject.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        fieldSubject = new JTextField(40);
        panelSubject.add(new JLabel("Subject"));
        panelSubject.add(fieldSubject);

        JPanel panelContent = new JPanel();
        panelContent.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        fieldContent = new JTextArea(15, 40);
        panelContent.add(new JLabel("Content"));
        panelContent.add(fieldContent);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

        buttonCreateArticle = new JButton("create article");
        buttonLogOut = new JButton("log out");
        panelButton.add(buttonCreateArticle);
        panelButton.add(buttonLogOut);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        create = file.add("Create article");
        exit = file.add("Exit");
        file.add(create);
        file.add(exit);
        setJMenuBar(menuBar);

        panelRight.add(Box.createHorizontalStrut(20));
        panelRight.add(panelSubject);
        panelRight.add(panelContent);
        panelRight.add(panelButton);

        JTree tree = new JTreeInit().init();
        panelFrame.add(Box.createHorizontalStrut(15));
        panelFrame.add(tree);
        panelFrame.add(Box.createHorizontalStrut(15));
        panelFrame.add(panelRight);
        add(panelFrame);

        ArticleController articleController = new ArticleController(this);
        buttonCreateArticle.addActionListener(articleController);
        buttonLogOut.addActionListener(articleController);
        create.addActionListener(articleController);
        exit.addActionListener(articleController);
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new JTreeListener(this));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
