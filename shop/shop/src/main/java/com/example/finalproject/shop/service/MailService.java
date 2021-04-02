package com.example.finalproject.shop.service;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface MailService {
	void sendSimpleMessage(String to, String subject, String text);
}

@Service
@Transactional
class MailServieImpl implements MailService{
	
	private static Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	public JavaMailSender emailSender;
	@Async
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			helper.setFrom("tuandzpc2000@gmail.com");
			helper.setText(text, true);
			helper.setTo(to);
			helper.setSubject(subject);

			emailSender.send(message);
			System.out.println("đã gửi mail");
		} catch (Exception ex) {
			logger.error("Email sending ex: " + ex);
		}
		
	}
	
}
