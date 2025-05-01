package com.example.DATK_backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private final JavaMailSender javaMailSender;
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendOtpMail (String toEmail, String Otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã OTP đặt lại mật khẩu");
        message.setText("OTP của bạn là: " + Otp);
        javaMailSender.send(message);
    }
}
