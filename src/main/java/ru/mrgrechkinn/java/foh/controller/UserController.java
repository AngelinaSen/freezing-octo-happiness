package ru.mrgrechkinn.java.foh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.mrgrechkinn.java.foh.dao.UserDAOImpl;
import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.util.IOUtils;
import ru.mrgrechkinn.java.foh.view.RegisterView;
import ru.mrgrechkinn.java.foh.view.UserView;


public class UserController implements ActionListener {
    
    public UserView parentView;
    private RegisterView parentRegister;
    
    public UserController(UserView parentView) {
        this.parentView = parentView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == parentView.buttonRegister) {
            onRegister(e);
        }
        if (e.getSource() == parentView.buttonLogin) {
            onLogin(e);
        }
    }
    private void onRegister(ActionEvent e) {
        
        parentRegister = new RegisterView();
        
    }
    private void onLogin(ActionEvent e) {
        
        /**
         * Этот код не правильный, контроллер не должен знать как и откуда доставать пользователей
         * надо использовать сервисы, но пока их нету, надо использовать UserDAO
         * 
         * Переписать весь код
         */
        
        
        
        /*String displayFieldTextLogin = parentView.fieldLogin.getText();
        char[] displayFieldTextPassword = parentView.fieldPassword.getPassword(); 
        
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
                if (displayFieldTextLogin.equals(u.getLogin()) && u.getPassword().equals(new String(displayFieldTextPassword))) {
                    parentView.labelNotification.setText(u.getLogin() + " logged");
                    break;
                }
                else {
                    parentView.labelNotification.setText("user name or login is incorrect");
                }
            }
            Arrays.fill(displayFieldTextPassword, (char) 0);
            
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }*/
    }

}
