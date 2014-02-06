package ru.mrgrechkinn.java.foh.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.controller.UserController;


public class UserView extends JFrame implements View {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public JTextField fieldLogin;
    public JPasswordField fieldPassword;
    public JLabel labelNotification;
    public JButton buttonRegister;
    public JButton buttonLogin;

    public UserView() {
        super("Login window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(2, 2, 5, 10));

        fieldLogin = new JTextField(10);
        detailsPanel.add(new JLabel("Login:"));
        detailsPanel.add(fieldLogin);

        fieldPassword = new JPasswordField(10);
        detailsPanel.add(new JLabel("Password:"));
        detailsPanel.add(fieldPassword);

        JPanel panelNotificatopn = new JPanel();
        panelNotificatopn.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelNotificatopn.setPreferredSize(new Dimension(DEFAULT_WIDTH - 50, 50));
        labelNotification = new JLabel("");
        panelNotificatopn.add(labelNotification);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        buttonRegister = new JButton("Register");
        buttonLogin = new JButton("Login");
        buttonsPanel.add(buttonRegister);
        buttonsPanel.add(buttonLogin);

        add(detailsPanel);
        add(panelNotificatopn);
        add(buttonsPanel);

        UserController userController = new UserController(this);
        buttonRegister.addActionListener(userController);
        buttonLogin.addActionListener(userController);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
