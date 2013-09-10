package ru.mrgrechkinn.java.foh.model;

public class Article implements Entity {
	
    // TODO: потому что в данном случае переменные относились к классу а не к объектам
    // 
	private  long id;
    private  String content;
    private  String subject;      
   
    
    public  String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }

	@Override
	public long getId() {
		return id;		
	}

	@Override
	public void setId(long id) {
		this.id = id;
		
	}
    

}
