package ru.mrgrechkinn.java.foh.dao;

import ru.mrgrechkinn.java.foh.model.User;

public class Test {
    
    public static void main(String[] args) {
        
        UserDAOImpl dao = new UserDAOImpl();
        User one = new User();
        
        one.setLogin("Login");
        one.setPassword("Password");
        one.setFullName("FIO");
        
        dao.save(one);
        
        //dao.getUserById(3);
        
        //dao.delete(one);
        
        //dao.getAllUsers();
        
    }

}
