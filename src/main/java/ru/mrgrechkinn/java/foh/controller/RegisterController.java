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

import ru.mrgrechkinn.java.foh.dao.UserDAOImpl;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.util.IOUtils;
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
            onExit(e);
        }
    }
    
    private void onRegister(ActionEvent e) {
        
        String displayFieldTextLogin = parentRegisterView.textFieldLogin.getText();
        char[] displayFieldTextPassword = parentRegisterView.passwordField.getPassword();
        
        File file = new File(UserDAOImpl.fileName);
        List<User> users = new ArrayList<User>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for(String s = reader.readLine(); s != null; s = reader.readLine()) {
                User newUser = new User();
                newUser.setId(Long.parseLong(s));
                newUser.setLogin(reader.readLine());
                newUser.setPassword(reader.readLine());
                newUser.setFullName(reader.readLine());
                users.add(newUser);
                
            }
            IOUtils.closeQuietly(reader);
            UserDAOImpl userdao = new UserDAOImpl();
            for (User u: users) {
                if (displayFieldTextLogin.equals("") || "".equals(new String (displayFieldTextPassword)) || parentRegisterView.textFieldFIO.getText().equals("")) {
                    parentRegisterView.labelNotification.setText("input all polya");
                    break;
                }
                if (displayFieldTextLogin.equals(u.getLogin())) {
                    parentRegisterView.labelNotification.setText("incorrect input user, user exist");
                } 
                else {
                    User newUser = new User();
                    newUser.setLogin(parentRegisterView.textFieldLogin.getText());
                    newUser.setPassword(new String(displayFieldTextPassword));
                    newUser.setFullName(parentRegisterView.textFieldFIO.getText());
                    userdao.save(newUser);
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
    
    private void onExit(ActionEvent e) {
        parentRegisterView.windowRegister.setVisible(false);;
        parentRegisterView.windowRegister.dispose();
    }

}
