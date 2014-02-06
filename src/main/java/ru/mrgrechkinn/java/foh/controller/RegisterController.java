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


public class RegisterController implements ActionListener {

    private static final Logger LOG = Logger.getLogger(RegisterController.class);

    public RegisterView parentRegisterView;

    public RegisterController(RegisterView parentRegisterView) {
        this.parentRegisterView = parentRegisterView;
    }

    /**
     * {@inheritDoc}
     */
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
        char[] displayFieldPassword = parentRegisterView.fieldPassword.getPassword();
        String displayFieldFullName = parentRegisterView.fieldFullName.getText();

        if (displayFieldLogin.isEmpty() || (new String(displayFieldPassword)).isEmpty() || displayFieldFullName.isEmpty()) {
            LOG.info("input all field");
            parentRegisterView.labelNotification.setText("input all field");
        } else {
            UserDAO userdao = new UserDAOSql();
            if (userdao.findUserByLogin(displayFieldLogin) != null) {
                LOG.info("incorrect input user, user exist");
                parentRegisterView.labelNotification.setText("incorrect input user, user exist");
            } else {
                User newUser = new User();
                newUser.setLogin(displayFieldLogin);
                newUser.setPassword(new String(displayFieldPassword));
                newUser.setFullName(displayFieldFullName);
                userdao.save(newUser);
                LOG.info(displayFieldLogin + " registered");
                parentRegisterView.setVisible(false);
                parentRegisterView.dispose();
                new UserView();
            }
        }
        Arrays.fill(displayFieldPassword, (char) 0);
    }

}
