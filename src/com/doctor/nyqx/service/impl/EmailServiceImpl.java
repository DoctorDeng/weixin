package com.doctor.nyqx.service.impl;

import java.util.List;

import com.doctor.nyqx.dao.EmailDao;
import com.doctor.nyqx.dao.impl.EmailDaoImpl;
import com.doctor.nyqx.entity.Email;
import com.doctor.nyqx.service.EmailService;
public class EmailServiceImpl  implements EmailService{
	private EmailDao emailDao = new EmailDaoImpl();
	
	@Override
	public List<Email> getAllEmail() {
		return emailDao.selectAllEmail();
	}
}
