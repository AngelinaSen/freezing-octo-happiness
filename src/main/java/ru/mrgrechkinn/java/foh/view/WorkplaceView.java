package ru.mrgrechkinn.java.foh.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.controller.WorkplaceController;

public class WorkplaceView extends JFrame implements View {

    public JButton buttonCreateArticle;
    public JButton buttonLogOut;
    public JTextField fieldSubject;
    public JTextArea fieldContent;

    public WorkplaceView() {
        super("Work place");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(550, 400));
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

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

        add(panelSubject);
        add(panelContent);
        add(panelButton);

        WorkplaceController workplaceController = new WorkplaceController(this);
        buttonCreateArticle.addActionListener(workplaceController);
        buttonLogOut.addActionListener(workplaceController);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
