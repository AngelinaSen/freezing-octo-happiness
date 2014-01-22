package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import org.apache.log4j.Logger;

import ru.mrgrechkinn.java.foh.dao.UserDAO;
import ru.mrgrechkinn.java.foh.dao.UserDAOSql;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.view.RegisterView;
import ru.mrgrechkinn.java.foh.view.UserView;
import ru.mrgrechkinn.java.foh.view.WorkplaceView;


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
        
        if (displayFieldLogin.isEmpty()) {
            LOG.info("field login is empty");
            parentView.labelNotification.setText("field login is empty");
        } else {
            User user = new User();
            UserDAO userDao = new UserDAOSql();
            user = userDao.findUserByLogin(displayFieldLogin);
            if (user.getLogin().equals(displayFieldLogin) && user.getPassword().equals(new String(displayFieldPassword))) {
                LOG.info(user.getLogin() + " logged");
                WorkplaceController.login = user.getLogin();
                parentView.setVisible(false);
                new WorkplaceView();
            } else {
                LOG.info("user name or login is incorrect");
                parentView.labelNotification.setText("user name or login is incorrect");
            }
        }
        Arrays.fill(displayFieldPassword, (char) 0);
        
    }

}
