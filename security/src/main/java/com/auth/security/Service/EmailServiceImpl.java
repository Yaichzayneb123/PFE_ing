package com.auth.security.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Transactional
@Service
public class EmailServiceImpl implements EmailService  {


      private JavaMailSender mailSender;

        public void sendEmail(String to, String subject, String body) {
           MimeMessage message = mailSender.createMimeMessage();
            try {
               MimeMessageHelper helper = new MimeMessageHelper(message, true);
               helper.setTo(to);
               helper.setSubject(subject);
               helper.setText("Bonjour\n\n " + body);
               mailSender.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            }
        }
    }

