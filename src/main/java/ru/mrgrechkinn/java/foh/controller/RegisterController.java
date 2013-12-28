package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ru.mrgrechkinn.java.foh.dao.UserDAO;
import ru.mrgrechkinn.java.foh.dao.UserDAOSql;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.view.RegisterView;
import ru.mrgrechkinn.java.foh.view.UserView;


public class RegisterController implements ActionListener {

    public RegisterView parentRegisterView;
    
    private static final Logger LOG = Logger.getLogger(RegisterController.class);
    
    public RegisterController(RegisterView parentRegisterView) {
        this.parentRegisterView = parentRegisterView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == parentRegisterView.buttonRegister) {
            onRegister(e);
        }
        if (e.getSource() == parentRegisterView.buttonCancel) {
        	parentRegisterView.setVisible(false);
            parentRegisterView.dispose();
            new UserView();
        }
    }
    
    private void onRegister(ActionEvent e) {
        
        String displayFieldLogin = parentRegisterView.fieldLogin.getText();
        char[] displayFieldPass = parentRegisterView.fieldPassword.getPassword();
        String displayFieldFullName = parentRegisterView.fieldFullName.getText();
        
        UserDAO userdao = new UserDAOSql();
        User newUser = new User();
        
        List<User> users = new ArrayList<User>();
        users.addAll(userdao.getAllUsers());
        if (!users.isEmpty()) {
            for (User u: users) {
                if ("".equals(displayFieldLogin) || "".equals(new String(displayFieldPass)) || "".equals(displayFieldFullName)) {
                    LOG.info("input all fields");
                    parentRegisterView.labelNotification.setText("input all fields");
                    break;
                }
                if (displayFieldLogin.equals(u.getLogin())) {
                    LOG.info("incorrect input user, user exist");
                    parentRegisterView.labelNotification.setText("incorrect input user, user exist");
                    break;
                }
                else {
                    newUser.setLogin(displayFieldLogin);
                    newUser.setPassword(new String(displayFieldPass));
                    newUser.setFullName(displayFieldFullName);
                    userdao.save(newUser);
                    LOG.info(displayFieldLogin + "registered");
                    parentRegisterView.setVisible(false);
                    parentRegisterView.dispose();
                    new UserView();
                    break;
                }
            }
        }
        else {
            if ("".equals(displayFieldLogin) || "".equals(new String(displayFieldPass)) || "".equals(displayFieldFullName)) {
                LOG.info("input all fields");
                parentRegisterView.labelNotification.setText("input all fields");
            }
            else {
                newUser.setLogin(displayFieldLogin);
                newUser.setPassword(new String(displayFieldPass));
                newUser.setFullName(displayFieldFullName);
                userdao.save(newUser);
                LOG.info(displayFieldLogin + "registered");
                parentRegisterView.setVisible(false);
            }
        }
        
    }
    
}
