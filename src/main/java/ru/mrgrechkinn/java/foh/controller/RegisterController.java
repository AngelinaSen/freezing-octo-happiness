package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ru.mrgrechkinn.java.foh.dao.UserDAO;
import ru.mrgrechkinn.java.foh.dao.UserDAOImpl;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.view.RegisterView;


public class RegisterController implements ActionListener {

    RegisterView parentRegisterView;
    
    public RegisterController(RegisterView parentRegisterView) {
        this.parentRegisterView = parentRegisterView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == parentRegisterView.buttonRegister) {
            onRegister(e);
        }
        if (e.getSource() == parentRegisterView.buttonExit) {
        	parentRegisterView.windowRegister.setVisible(false);
            parentRegisterView.windowRegister.dispose();
        }
    }
    
    private void onRegister(ActionEvent e) {
    	
        String displayFieldTextLogin = parentRegisterView.textFieldLogin.getText();
        char[] displayFieldTextPassword = parentRegisterView.passwordField.getPassword();
        
        UserDAO user = new UserDAOImpl();
        User newUser = new User();
        
        List<User> users = new ArrayList<User>();
        users.addAll(user.getAllUsers());
        
        for (User u: users) {
        	if ("".equals(displayFieldTextLogin) || "".equals(new String(displayFieldTextPassword)) || "".equals(parentRegisterView.textFieldFullName.getText())) {
        		parentRegisterView.labelNotification.setText("input all fields");
        		break;
        	}
        	if (displayFieldTextLogin.equals(u.getLogin())) {
        		parentRegisterView.labelNotification.setText("incorrect input user, user exist");
        	}
        	else {
        		newUser.setLogin(displayFieldTextLogin);
        		newUser.setPassword(new String(displayFieldTextPassword));
        		newUser.setFullName(parentRegisterView.textFieldFullName.getText());
        		user.save(newUser);
        		parentRegisterView.windowRegister.setVisible(false);
        	}
        }
        
    }
    
}
