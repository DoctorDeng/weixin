package com.doctor.nyqx.commons.mail.core;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
import java.util.Vector;  

import com.doctor.nyqx.commons.mail.entity.MailContent;

import lotus.domino.Database;  
import lotus.domino.Document;  
import lotus.domino.NotesException;  
import lotus.domino.NotesFactory;  
import lotus.domino.Session; 

public class NotesMailManager {
	private String recipients;  
    private String dominoServerName;  
    private String userFilePath;  
    private String host;  
    private String userName;  
    private String password;      
  
      
    public Session getNotesSession(){  
        Session session = null;       
  
        try {  
            session = NotesFactory.createSession(host,userName,password);             
        } catch (NotesException e) {  
          System.out.println("Error happens when creating session using DIIOP port.");    
          e.printStackTrace();  
          return null;  
        }  
        return session;  
    }  
      
    public Session getNotesSession(String host,String userName,String passwd){  
        this.setHost(host);  
        this.setUserName(userName);  
        this.setPassword(passwd);  
        return getNotesSession();  
    }  
      
    public void sendMail(Session session,MailContent mailContent){  
        if (session == null){  
            System.out.println("Fail to send mail for: session is null!");  
            return;  
        }         
        boolean isloaded = loadProperty();  
        Database database = null;  
        Document mailDom = null;          
        if (isloaded){                
            try {  
                System.out.println("User: " + session.getUserName());  
                
                database = session.getDatabase(dominoServerName,userFilePath,true);               
                System.out.println("Database URL: "+database.getURL());  
                mailDom = database.createDocument();     
                mailDom.replaceItemValue("Form",mailContent.getForm());     
                mailDom.replaceItemValue("Subject",mailContent.getSubject());  
                mailDom.replaceItemValue("Body",mailContent.getBody());  
                Vector<String>  recipientsVector = new Vector<String>();  
                String [] recipientArray = recipients.split(",");  
                System.out.print("send to: ");  
                for(String rept:recipientArray){  
                    recipientsVector.add(rept);  
                    System.out.print(rept+"  ");  
                }  
                mailDom.save();  
                mailDom.send(recipientsVector);              
            } catch (NotesException e) {  
                System.out.println("Fail to send mail for NotesException!");  
                e.printStackTrace();  
                return;  
            }finally{  
                try {  
                    if (mailDom != null){  
                        mailDom.recycle();  
                        mailDom = null;  
                    }  
                    if (database != null){  
                        database.recycle();  
                        database = null;  
                    }  
                    if (session != null ){  
                        session.recycle();  
                        session = null;  
                    }  
                }catch (NotesException e1){  
                      
                }  
            }  
          System.out.println("\n Done! The mail has been successfully sent.");    
        }             
          
    }  
      
    public boolean loadProperty(){  
        InputStream in = this.getClass().getResourceAsStream("/mail-config.properties");  
        Properties props = new Properties();  
         try {  
            props.load(in);  
        } catch (IOException e) {  
            try {  
                if (in != null){  
                    in.close();  
                }  
                in = null;  
            } catch (IOException e1) {        
                e1.printStackTrace();  
            }  
            e.printStackTrace();  
            return false;  
        }  
        dominoServerName = props.getProperty("dominoServerName");  
        userFilePath = props.getProperty("userFilePath");  
        recipients = props.getProperty("recipientsAdress");  
        host = props.getProperty("host");  
        userName = props.getProperty("userName");  
        password = props.getProperty("password");         
        return true;  
    }  
  
    public String getRecipients() {  
        return recipients;  
    }  
  
    public void setRecipients(String recipients) {  
        this.recipients = recipients;  
    }  
  
    public String getDominoServerName() {  
        return dominoServerName;  
    }  
  
    public void setDominoServerName(String dominoServerName) {  
        this.dominoServerName = dominoServerName;  
    }  
  
    public String getUserFilePath() {  
        return userFilePath;  
    }  
  
    public void setUserFilePath(String userFilePath) {  
        this.userFilePath = userFilePath;  
    }  
  
    public String getHost() {  
        return host;  
    }  
  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }         

}
