package ru.mrgrechkinn.java.foh.dao;

import ru.mrgrechkinn.java.foh.model.User;

public class Test {
    
    public static void main(String[] args) {
        
        UserDAOImpl dao = new UserDAOImpl();
        User one = new User();
        
        one.setLogin("Login2");
        one.setPassword("Password77");
        one.setFullName("FIO777");
        
        dao.save(one);
        
        //dao.getUserById(1);
        
        //dao.delete(one);
        
        //dao.getAllUsers();
        
    }

}
