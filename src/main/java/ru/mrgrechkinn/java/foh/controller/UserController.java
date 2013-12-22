package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import ru.mrgrechkinn.java.foh.dao.UserDAO;
import ru.mrgrechkinn.java.foh.dao.UserDAOSql;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.view.RegisterView;
import ru.mrgrechkinn.java.foh.view.UserView;


public class UserController implements ActionListener {
    
    public UserView parentView;
    
    private static final Logger LOG = Logger.getLogger(UserController.class);
    
    public UserController(UserView parentView) {
        this.parentView = parentView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == parentView.buttonRegister) {
            new RegisterView();
            parentView.setVisible(false);
        }
        if (e.getSource() == parentView.buttonLogin) {
            onLogin(e);
        }
    }
    
    private void onLogin(ActionEvent e) {
        
        String displayFieldLogin = parentView.fieldLogin.getText();
        char[] displayFieldPassword = parentView.fieldPassword.getPassword();
        
        UserDAO user = new UserDAOSql();
        List<User> users = new ArrayList<User>();
        
        users.addAll(user.getAllUsers());
        
        if (users.isEmpty()) {
            LOG.debug("no registered users");
            parentView.labelNotification.setText("user name or login is incorrect");
        }
        else {
            for (User u: users) {
                if (u.getLogin().equals(displayFieldLogin) && u.getPassword().equals(new String(displayFieldPassword))) {
                    LOG.debug(u.getLogin() + " logged");
                    parentView.labelNotification.setText(u.getLogin() + " logged");
                    break;
                }
                else {
                    LOG.debug("user name or login is incorrect");
                    parentView.labelNotification.setText("user name or login is incorrect");
                }
            }
        }
        
        Arrays.fill(displayFieldPassword, (char) 0);
        
    }

}
