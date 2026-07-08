package com.rubaet.agri.controller;

import com.rubaet.agri.dto.SupportRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${app.api.base-path}/support")
public class SupportController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public Map<String, String> submitSupportRequest(@Valid @RequestBody SupportRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getEmail());
        message.setSubject("AGRICULTURAL INTELLIGENCE HUB");
        message.setText("Thank you for your contact. If you need any help then you may come to our office directly for a live session.\n\n" +
                "Our office address is given below:\n" +
                "MILITARY INSTITUTE OF SCIENCE AND TECHNOLOGY, DHAKA.\n\n" +
                "MIRPUR CANTONMENT, DHAKA.\n\n" +
                "THANK YOU AND STAY WITH US\n\n" +
                "ARIF ABDULLAH\nCEO OF A.I.H");

        mailSender.send(message);

        return Map.of("message", "Support request processed and email sent.");
    }
}
