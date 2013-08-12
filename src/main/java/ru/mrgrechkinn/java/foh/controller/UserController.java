package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ru.mrgrechkinn.java.foh.dao.UserDAOImpl;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.util.IOUtils;
import ru.mrgrechkinn.java.foh.view.UserView;


public class UserController implements ActionListener {
    
    UserView parent;
    
    public UserController(UserView parent) {
        this.parent = parent;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //JButton clickedButton = (JButton) e.getSource();
        
        String displayFieldTextLogin = parent.fieldLogin.getText();
        String displayFieldTextPassword = parent.fieldPassword.getText(); 
        
        File file = new File(UserDAOImpl.fileName);
        List<User> users = new ArrayList<User>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                User newUser = new User();
                newUser.setId(Long.parseLong(s));
                newUser.setLogin(reader.readLine());
                newUser.setPassword(reader.readLine());
                newUser.setFullName(reader.readLine());
                users.add(newUser);
            }
            IOUtils.closeQuietly(reader);
            
            for (User u: users) {
                if (displayFieldTextLogin.equals(u.getLogin()) && displayFieldTextPassword.equals(u.getPassword())) {
                    //JOptionPane.showConfirmDialog(null, u.getLogin() + " logged", "Confirmation window", JOptionPane.PLAIN_MESSAGE);
                    parent.labelNotification.setText(u.getLogin() + " logged");
                    break;
                }
                else {
                    parent.labelNotification.setText("* user name or login is incorrect");
                }
            }
            
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }

}
