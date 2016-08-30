package com.doctor.nyqx.service;

import java.util.List;

import com.doctor.nyqx.entity.Email;

public interface EmailService {
	/**
	 * 获得所有的邮件信息
	 * @return
	 */
	public List<Email> getAllEmail();
}
