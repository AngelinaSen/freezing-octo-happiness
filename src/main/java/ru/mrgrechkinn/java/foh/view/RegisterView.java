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

import ru.mrgrechkinn.java.foh.controller.RegisterController;


public class RegisterView extends JFrame implements View{

    public JLabel labelNotification;
    public JTextField fieldLogin;
    public JPasswordField fieldPassword;
    public JTextField fieldFullName;
    public JButton buttonRegister;
    public JButton buttonCancel;

    public RegisterView() {
        super("Register window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(4, 4, 5, 10));

        fieldLogin = new JTextField(10);
        detailsPanel.add(new JLabel("Login:"));
        detailsPanel.add(fieldLogin);

        fieldPassword = new JPasswordField(10);
        detailsPanel.add(new JLabel("Password:"));
        detailsPanel.add(fieldPassword);

        fieldFullName = new JTextField(10);
        detailsPanel.add(new JLabel("Full Name:"));
        detailsPanel.add(fieldFullName);

        JPanel panelNotificatopn = new JPanel();
        panelNotificatopn.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelNotificatopn.setPreferredSize(new Dimension(250, 50));
        labelNotification = new JLabel("");
        panelNotificatopn.add(labelNotification);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        buttonRegister = new JButton("Register");
        buttonCancel = new JButton("Cancel");
        buttonsPanel.add(buttonRegister);
        buttonsPanel.add(buttonCancel);

        add(detailsPanel);
        add(panelNotificatopn);
        add(buttonsPanel);

        RegisterController registerController = new RegisterController(this);

        buttonRegister.addActionListener(registerController);
        buttonCancel.addActionListener(registerController);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
