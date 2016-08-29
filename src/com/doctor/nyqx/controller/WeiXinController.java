package com.doctor.nyqx.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doctor.nyqx.commons.util.ValidateUtil;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	
	@RequestMapping(value= "/validate" , method= RequestMethod.POST)
	public String validate(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		return "forward:/message/messageJudge";
	}
	/**
	 * 验证Token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value= "/validate" , method= RequestMethod.GET)
	@ResponseBody
	public String validateGet(String signature,String timestamp,String nonce,String echostr)  {
		
		if (ValidateUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		} 
		return "";
	}
}
