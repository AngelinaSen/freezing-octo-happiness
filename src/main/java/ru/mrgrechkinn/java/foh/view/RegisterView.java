package ru.mrgrechkinn.java.foh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.controller.RegisterController;
import ru.mrgrechkinn.java.foh.controller.UserController;


public class RegisterView extends JFrame {
    
    private JPanel windowContent;
    public JLabel labelNotification;
    public JTextField textFieldLogin;
    public JPasswordField passwordField;
    public JTextField textFieldFIO;
    public JButton buttonRegister;
    public JButton buttonExit;
    public JFrame windowRegister;
    
    public RegisterView() {
        
        windowContent = new JPanel();
        windowContent.setLayout(new BoxLayout(windowContent, BoxLayout.PAGE_AXIS));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        labelNotification = new JLabel("Enter the registration data");
        p1.add(labelNotification);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelLogin = new JLabel("Login ");
        textFieldLogin = new JTextField(10);
        p2.add(labelLogin);
        p2.add(textFieldLogin);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelPassword = new JLabel("Password ");
        passwordField = new JPasswordField(10);
        p3.add(labelPassword);
        p3.add(passwordField);
        
        JPanel p4 = new JPanel();
        JLabel labelFIO = new JLabel("FIO ");
        textFieldFIO = new JTextField(10);
        p4.add(labelFIO);
        p4.add(textFieldFIO);
        
        JPanel p5 = new JPanel();
        buttonRegister = new JButton("Register");
        buttonExit = new JButton("Exit");
        p5.add(buttonRegister);
        p5.add(buttonExit);
        
        windowContent.add(p1);
        windowContent.add(p2);
        windowContent.add(p3);
        windowContent.add(p4);
        windowContent.add(p5);
        
        windowRegister = new JFrame("Register window");
        windowRegister.setContentPane(windowContent);
        windowRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRegister.setSize(300, 300);
        windowRegister.setVisible(true);
        
        RegisterController registerController = new RegisterController(this);
        
        buttonRegister.addActionListener(registerController);
        buttonExit.addActionListener(registerController);
    }

}
