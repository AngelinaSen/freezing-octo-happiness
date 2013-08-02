package ru.mrgrechkinn.java.foh.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.mrgrechkinn.java.foh.model.User;
import ru.mrgrechkinn.java.foh.util.IOUtils;

public class UserDAOImpl implements UserDAO {

    public static final String fileName = "test.txt";
    private File file;
    
    
    public UserDAOImpl(){
        file = new File(fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean save(User user) {
        try {
            FileWriter writer = new FileWriter(file, true);
            
            writer.write(String.valueOf(user.getId()) + "\n");
            writer.write(user.getLogin() + "\n");
            writer.write(user.getPassword() + "\n");
            writer.write(user.getFullName() + "\n");
            
            IOUtils.closeQuietly(writer);
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        User user = new User();
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            List<String> list = new ArrayList<String>();
            
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                    list.add(s);
            }
            
            for (int i = 0; i < list.size();) {
                if (Long.parseLong(list.get(i)) == id) {
                    System.out.println(list.get(i + 1));
                }
                i += 4;
            }
            
            for (int i = 0; i < list.size(); ){
                if (Long.parseLong(list.get(i)) == id){
                    user.setId(id);
                    user.setLogin(list.get(i + 1));
                    user.setPassword(list.get(i + 2));
                    user.setFullName(list.get(i + 3)); break;
                }
                i += 4;
            }
            
            IOUtils.closeQuietly(reader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return user;
    }

    @Override
    public boolean delete(User user) {
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            List <String> list = new ArrayList<String>();
            List <String> listTwo = new ArrayList<String>();
            
            try {
                for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                    list.add(s);
                }
                IOUtils.closeQuietly(reader);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            for (int i = 1; i < list.size(); ){
                if (!user.getLogin().equals(list.get(i))){
                    listTwo.add(list.get(i - 1));
                    listTwo.add(list.get(i));
                    listTwo.add(list.get(i + 1));
                    listTwo.add(list.get(i + 2));
                }
                i += 4;
            }
            
            try {
                FileWriter writer = new FileWriter(file);
                
                for (int i = 0; i < listTwo.size(); i++){
                    writer.write(listTwo.get(i) + "\n");
                }
                IOUtils.closeQuietly(writer);
                return true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            IOUtils.closeQuietly(reader);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List <User> listUser = new ArrayList<User>();
        List <String> list = new ArrayList<String>();
        User user = new User();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            
            try {
                for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                    list.add(s);
                }
                for (int i = 0; i < list.size(); ) {
                    user.setId(Long.parseLong(list.get(i)));
                    user.setLogin(list.get(i));
                    user.setPassword(list.get(i + 2));
                    user.setFullName(list.get(i + 1));
                    listUser.add(user);
                    i += 4;
                }
                IOUtils.closeQuietly(reader);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listUser;
    }
    
    
}
