package com.doctor.nyqx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doctor.nyqx.commons.util.ValidateUtil;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	
	@RequestMapping("/validate")
	public void validate(String signature,String timestamp,String nonce,String echostr,HttpServletResponse response) throws IOException {
		if (ValidateUtil.checkSignature(signature, timestamp, nonce)) {
			response.getWriter().println(echostr);
		}
	}
	@RequestMapping("/test") 
	@ResponseBody
	public String test(){
		return "test";
	}
}
