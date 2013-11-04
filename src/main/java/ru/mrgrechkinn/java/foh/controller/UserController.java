package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mrgrechkinn.java.foh.dao.UserDAO;
import ru.mrgrechkinn.java.foh.dao.UserDAOImpl;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.view.RegisterView;
import ru.mrgrechkinn.java.foh.view.UserView;


public class UserController implements ActionListener {
    
    public UserView parentView;
    
    public UserController(UserView parentView) {
        this.parentView = parentView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == parentView.buttonRegister) {
            new RegisterView();
        }
        if (e.getSource() == parentView.buttonLogin) {
            onLogin(e);
        }
    }
    
    private void onLogin(ActionEvent e) {
        
        String displayFieldTextLogin = parentView.fieldLogin.getText();
        char[] displayFieldTextPassword = parentView.fieldPassword.getPassword();
        
        UserDAO user = new UserDAOImpl();
        List<User> users = new ArrayList<User>();
        
        users.addAll(user.getAllUsers());
        
        for (User u: users) {
        	if (u.getLogin().equals(displayFieldTextLogin) && u.getPassword().equals(new String(displayFieldTextPassword))) {
        		parentView.labelNotification.setText(u.getLogin() + " logged");
        		break;
        	}
        	else {
        		parentView.labelNotification.setText("user name or login is incorrect");
        	}
        }
        Arrays.fill(displayFieldTextPassword, (char) 0);
        
    }

}
