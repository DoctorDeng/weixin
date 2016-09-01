package com.doctor.nyqx.commons.notesMail;

import java.util.Date;

import com.doctor.nyqx.commons.mail.core.NotesMailManager;
import com.doctor.nyqx.commons.mail.entity.MailContent;

import lotus.domino.NotesException;
import lotus.domino.NotesFactory;
import lotus.domino.NotesThread;
import lotus.domino.Session;

public class NotesMailThread extends NotesThread{
	/*static {       
		System.loadLibrary("nlsxbe.dll");    
	}*/
	@Override
	public void runNotes() throws NotesException {
		super.runNotes();
		NotesMailManager manager = new NotesMailManager();
		Session session = NotesFactory.createSession();
		MailContent mc = new MailContent("Notes客户端发送邮件，测试","这是测试邮件! \n"+(new Date()).toString());  
        manager.sendMail(session, mc);
	}
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		NotesMailThread notes = new NotesMailThread();
		notes.start();
	}
}
