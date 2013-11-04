package ru.mrgrechkinn.java.foh.view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.controller.RegisterController;


public class RegisterView extends JFrame implements View{
    
    private JPanel windowContent;
    public JLabel labelNotification;
    public JTextField textFieldLogin;
    public JPasswordField passwordField;
    public JTextField textFieldFullName;
    public JButton buttonRegister;
    public JButton buttonExit;
    public JFrame windowRegister;
    
    public RegisterView() {
        
        windowContent = new JPanel();
        windowContent.setLayout(new BoxLayout(windowContent, BoxLayout.PAGE_AXIS));
        
        JPanel panelNotification = new JPanel();
        panelNotification.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        labelNotification = new JLabel("Enter the registration data");
        panelNotification.add(labelNotification);
        
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelLogin = new JLabel("Login ");
        textFieldLogin = new JTextField(10);
        panelLogin.add(labelLogin);
        panelLogin.add(textFieldLogin);
        
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelPassword = new JLabel("Password ");
        passwordField = new JPasswordField(10);
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);
        
        JPanel panelFullName = new JPanel();
        JLabel labelFullName = new JLabel("Full Name ");
        textFieldFullName = new JTextField(10);
        panelFullName.add(labelFullName);
        panelFullName.add(textFieldFullName);
        
        JPanel panelButtons = new JPanel();
        buttonRegister = new JButton("Register");
        buttonExit = new JButton("Exit");
        panelButtons.add(buttonRegister);
        panelButtons.add(buttonExit);
        
        windowContent.add(panelNotification);
        windowContent.add(panelLogin);
        windowContent.add(panelPassword);
        windowContent.add(panelFullName);
        windowContent.add(panelButtons);
        
        windowRegister = new JFrame("Register window");
        windowRegister.setContentPane(windowContent);
        windowRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowRegister.setSize(300, 300);
        windowRegister.setVisible(true);
        windowRegister.setResizable(false);
        windowRegister.setLocationRelativeTo(null);
        
        RegisterController registerController = new RegisterController(this);
        
        buttonRegister.addActionListener(registerController);
        buttonExit.addActionListener(registerController);
    }

}
