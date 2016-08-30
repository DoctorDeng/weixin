package com.doctor.nyqx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import com.doctor.nyqx.entity.Email;
import com.doctor.nyqx.service.EmailService;
import com.doctor.nyqx.service.impl.EmailServiceImpl;

/**
 * 定时发短信类
 * 
 * @author Doctor
 *
 */
public class TimingSendMailController {
	private ScheduledExecutorService service;
	private EmailService emailService = new EmailServiceImpl();
	public void init() {
		Runnable ru = () -> {
			System.out.println("哈哈哈!");
		};
		service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(ru, 10, 1, TimeUnit.SECONDS);
	}
	
	public void sendMails() {
		List<String> emailList = new ArrayList<>();
		List<Email> emails = emailService.getAllEmail();
		for(Email email:emails) {
			emailList.add(email.getUemail());
			System.out.println(email.getUemail());
		}
	}
	
	/**
	 * 设置计时的时间
	 * 
	 * @param delayTime
	 * @param intervalTime
	 */
	public void setTimingTime(int delayTime, int intervalTime) {
		if(!service.isShutdown()) {
			
		}
	}

	public static void main(String[] args) {
		TimingSendMailController timing = new TimingSendMailController();
		timing.sendMails();
	}

}
