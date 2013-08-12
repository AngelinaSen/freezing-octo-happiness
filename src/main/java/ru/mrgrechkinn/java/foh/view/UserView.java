package ru.mrgrechkinn.java.foh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.controller.UserController;


public class UserView {
    
    private JPanel windowContent;
    public JTextField fieldLogin;
    public JPasswordField fieldPassword;
    public JLabel labelNotification;
    private JButton buttonRegister;
    private JButton buttonLogin;
    private File file;
    
    public UserView() {
        
        windowContent = new JPanel();
        windowContent.setLayout(new BoxLayout(windowContent, BoxLayout.PAGE_AXIS));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelLogin = new JLabel("Login: ");
        fieldLogin = new JTextField(10);
        p1.add(labelLogin);
        p1.add(fieldLogin);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JLabel labelPassword = new JLabel("Password: ");
        fieldPassword = new JPasswordField(10);
        p2.add(labelPassword);
        p2.add(fieldPassword);
        
        JPanel panelNotificatopn = new JPanel();
        panelNotificatopn.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        labelNotification = new JLabel("");
        panelNotificatopn.add(labelNotification);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        buttonRegister = new JButton("Register");
        buttonLogin = new JButton("Login");
        p3.add(buttonRegister);
        p3.add(buttonLogin);
        
        windowContent.add(p1);
        windowContent.add(p2);
        windowContent.add(panelNotificatopn);
        windowContent.add(p3);
        
        JFrame myWindow = new JFrame("Login window");
        myWindow.setContentPane(windowContent);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(300, 300);
        myWindow.setVisible(true);
        
        /*
        windowContent = new JPanel();
        windowContent.setLayout(new BoxLayout(windowContent, BoxLayout.PAGE_AXIS));
        
        Box box1 = Box.createHorizontalBox();
        JLabel labelLogin = new JLabel("Login: ");
        fieldLogin = new JTextField(10);
        box1.add(labelLogin);
        box1.add(fieldLogin);
        
        Box box2 = Box.createHorizontalBox();
        JLabel labelPassword = new JLabel("Password: ");
        fieldPassword = new JPasswordField(10);
        box2.add(labelPassword);
        box2.add(fieldPassword);
        
        Box box3 = Box.createHorizontalBox();
        buttonRegister = new JButton("Register");
        buttonLogin = new JButton("Login");
        box3.add(buttonRegister);
        box3.add(buttonLogin);
        
        windowContent.add(box1);
        windowContent.add(box2);
        windowContent.add(box3);
        
        JFrame myWindow = new JFrame("Login window");
        myWindow.setContentPane(windowContent);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(300, 300);
        myWindow.setVisible(true);*/
        
        UserController usercontroller = new UserController(this);
        
        buttonLogin.addActionListener(usercontroller);
        
    }
    
}
