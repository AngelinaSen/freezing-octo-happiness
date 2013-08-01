package ru.mrgrechkinn.java.foh.dao;

import ru.mrgrechkinn.java.foh.model.User;

public class Test {
    
    public static void main(String[] args) {
        
        UserDAOImpl dao = new UserDAOImpl();
        User one = new User();
        
        one.setId(2);
        one.setLogin("Login2");
        one.setPassword("Password");
        one.setFullName("FIO");
        
        //dao.save(one);
        
        //dao.getUserById(4);
        
        dao.delete(one);
        
        //dao.getAllUsers();
        
        //System.out.println(one.getId() + " " + one.getLogin() + " " + one.getPassword() + " " + one.getFullName());
    }

}
