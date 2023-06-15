package com.canse.discord.controllers;

import com.canse.discord.services.impl.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/sendMail")
    public void sendMail(){

        emailService.sendConfirmationEmail("666","Julien", "Cansell","jucansell@gmail.com");
    }
}
