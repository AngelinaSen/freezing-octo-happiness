package ru.mrgrechkinn.java.foh.model;

//TODO: Класс должен реализовывать интерфейс Entity, я об этом писал в описании задачи
public class Article {
	
    // TODO: Зачем ты использовал static модификатор, это не правильно (почитать и переписать), отписать мне
    // почему это не правильно
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
