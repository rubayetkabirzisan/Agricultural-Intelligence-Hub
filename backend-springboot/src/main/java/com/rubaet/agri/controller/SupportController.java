package com.rubaet.agri.controller;

import com.rubaet.agri.dto.SupportRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path}/support")
public class SupportController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.support.email:support@agrihub.com}")
    private String supportEmail;

    @PostMapping
    public Map<String, String> submitSupportRequest(@Valid @RequestBody SupportRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(supportEmail);  // Fixed recipient — prevents open relay
        message.setSubject("AgriHub Support Request from " + request.getEmail());
        message.setText(
            "New support request received:\n\n" +
            "From: " + request.getEmail() + "\n" +
            "Phone: " + request.getPhone() + "\n\n" +
            "Message:\n" + request.getMessage() + "\n"
        );

        mailSender.send(message);

        return Map.of("message", "Support request submitted. We will contact you shortly.");
    }
}
