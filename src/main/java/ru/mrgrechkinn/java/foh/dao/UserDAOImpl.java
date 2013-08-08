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
        boolean isNewUser = true;
        long lastUserId = 0;
        List<User> users = new ArrayList<User>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // Считываем пользователей
            for (String s = reader.readLine(); s != null; s = reader.readLine()) {
                User newUser = new User();
                long userId = Long.parseLong(s);
                if (userId > lastUserId) {
                    lastUserId = userId;
                }
                newUser.setId(userId);
                newUser.setLogin(reader.readLine());
                newUser.setPassword(reader.readLine());
                newUser.setFullName(reader.readLine());
                users.add(newUser);
            }

            if (!users.isEmpty()) {
                for (User u : users) {
                    if (u.getLogin().equals(user.getLogin())) {
                        u.setFullName(user.getFullName());
                        u.setPassword(user.getPassword());
                        isNewUser = false;
                        break;
                    }
                }
            }

            if (isNewUser) {
                user.setId(lastUserId + 1);
                users.add(user);
            }
            IOUtils.closeQuietly(reader);

            FileWriter writer = new FileWriter(file);
            for (User u : users) {
                writer.write(u.getId() + "\n");
                writer.write(u.getLogin() + "\n");
                writer.write(u.getPassword() + "\n");
                writer.write(u.getFullName() + "\n");
            }
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
                    user.setLogin(list.get(i + 1));
                    user.setPassword(list.get(i + 2));
                    user.setFullName(list.get(i + 3));
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
