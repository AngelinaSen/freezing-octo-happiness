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

public class UserDAOImpl implements UserDAO {

    public static final String fileName = "D:\\endrysan\\text.txt";
    private File file;
    //public static final int nList = 50;
    
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
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(String.valueOf(user.getId()) + "\n");
            writer.write(user.getLogin() + "\n");
            writer.write(user.getPassword() + "\n");
            writer.write(user.getFullName() + "\n");
            //writer.write("\n");
            writer.close();
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
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            List<String> list = new ArrayList<String>();
            //for (int i = 0; i < nList; i ++){
            while (reader.readLine() != null) {
                String line;
                if ((line = reader.readLine()) != null)
                {
                    list.add(line);
                }
            }
            
            //System.out.println(list.size());
            for (int i = 1; i < list.size(); ){
                if (Long.parseLong(list.get(i - 1)) == id){
                    System.out.println(list.get(i));
                }
                else{
                    System.out.print("");
                }
                i += 4;
            }
            
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public boolean delete(User user) {
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            List <String> list = new ArrayList<String>();
            List <String> listTwo = new ArrayList<String>();
            
            //for (int i = 0; i < nList; i++){
            try {
                while (reader.readLine() != null) {
                    String line;
                    try {
                        line = reader.readLine();
                        if (line != null){
                            list.add(line);
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //почему здесь не могу закрыть поток?
            //reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            List <String> list = new ArrayList<String>();
            
            try {
                while (reader.readLine() != null){
                    list.add(reader.readLine());
                }
                
                for (String s: list){
                    System.out.println(s);
                }
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
}
