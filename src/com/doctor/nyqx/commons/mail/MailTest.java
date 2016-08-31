package com.doctor.nyqx.commons.mail;

import java.util.Date;

import com.doctor.nyqx.commons.mail.core.NotesMailManager;
import com.doctor.nyqx.commons.mail.entity.MailContent;

import lotus.domino.Session;  
  
public class MailTest {  
  
    public static void main(String[] args) {  
        NotesMailManager manager = new NotesMailManager();  
        boolean isloaded = manager.loadProperty();  
        if (isloaded){  
            Session session = manager.getNotesSession();  
            MailContent mc = new MailContent("System mail--test","for mail test ! \n"+(new Date()).toString());  
            manager.sendMail(session,mc);  
        }  
    }  
}  
