package com.doctor.nyqx.commons.mail.entity;

public class MailContent {
	
	    private String form;  
	    private String subject;  
	    private String body;  
	      
	    MailContent(){        
	    }  
	      
	    MailContent(String body){  
	        this(null,null,body);  
	    }  
	      
	    public MailContent(String subject,String body){  
	        this(null,subject,body);  
	    }  
	      
	    MailContent(String form,String subject,String body){  
	        this.form = form;  
	        this.subject = subject;  
	        this.body = body;  
	    }     
	      
	      
	    public String getForm() {  
	        return form;  
	    }  
	    public void setForm(String form) {  
	        this.form = form;  
	    }  
	    public String getSubject() {  
	        return subject;  
	    }  
	    public void setSubject(String subject) {  
	        this.subject = subject;  
	    }  
	    public String getBody() {  
	        return body;  
	    }  
	    public void setBody(String body) {  
	        this.body = body;  
	    }  

}
