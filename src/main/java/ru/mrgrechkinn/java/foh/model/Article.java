package ru.mrgrechkinn.java.foh.model;

public class Article {
	
	private static String id;
    private static String content;
    private static String subject;
    
    
    public static String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public static String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public static String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    

}
