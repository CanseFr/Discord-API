package com.canse.discord.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class EmailServiceImpl  {
    private final static String EMAIL_CONFIRMATION_SUBJECT = "Confirmation Inscription Alert Mns";
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendConfirmationEmail(String provisoirPassword,String firstanme, String lastname, String email) {
        // Build email
        String message = "Bonjour "+ firstanme + " " + lastname +", " +
                        "\n\nVous venez d'etre inscrit sur la plateforme alerte MNS \n" +
                        "Votre mot de passe provisoir est : " +
                        "\n" +
                        "\n \t  - "+ provisoirPassword + " " +
                        "\n" +
                        "\nVeuillez vous connecter puis initialiser votre mot de passe personnel dans les réglages." +
                        "\n\nCordialement," +
                        "\nL'administration.";
        String from = "julien.cansell@stagiairesmns.fr";
        send(email, from, message);
    }

    @Async
    void send(String to, String from, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(email);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            //LOGGER.error("failed to send email", e);   // TODO : Fixer cette erreur
            throw new IllegalStateException("failed to send email");
        }
    }
}
