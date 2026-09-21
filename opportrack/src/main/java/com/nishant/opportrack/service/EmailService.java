package com.nishant.opportrack.service;

import com.nishant.opportrack.model.OpportunityItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendDigestEmail(String toEmail, List<OpportunityItem> items) {
        StringBuilder body = new StringBuilder();
        body.append("Naye opportunities mile hain:\n\n");

        for (OpportunityItem item : items) {
            body.append(item.getTitle())
                .append(" (").append(item.getSource()).append(")\n")
                .append(item.getLink()).append("\n\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("New Opportunities Update - OpporTrack");
        message.setText(body.toString());

        mailSender.send(message);
    }
}