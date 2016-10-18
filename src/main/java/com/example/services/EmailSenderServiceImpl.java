package com.example.services;

import com.example.Utils.UrlContextPath;
import com.example.entities.Token;
import com.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kevin on 15/10/2016.
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService{

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @Override
    public void send(String to, String subject, String content) throws MessagingException {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void envoyerMailDuToken(User user, Token token, HttpServletRequest hsr) throws MessagingException {
        String url = UrlContextPath.getURLWithContextPath(hsr);
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("lastname", user.getLastname());
        context.setVariable("url", url);
        context.setVariable("token", "/activer?token="+token.getToken());
        final String content = templateEngine.process("token", context);
        this.send(user.getEmail(), "activer votre compte", content);
    }


    @Override
    public void envoyerMailDuMdpOublie(User user, Token token, HttpServletRequest hsr) throws MessagingException {
        String url = UrlContextPath.getURLWithContextPath(hsr);
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("lastname", user.getLastname());
        context.setVariable("url", url);
        context.setVariable("token", "/forget?token="+token.getToken());
        final String content = templateEngine.process("token", context);
        this.send(user.getEmail(), "réinitialiser mot de passe", content);
    }

    //methode livré de thymeleaf
    public void sendMailWithInline(
            final String recipientName, final String recipientEmail, final String imageResourceName,
            final byte[] imageBytes, final String imageContentType, final Locale locale)
            throws MessagingException {

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("name", recipientName);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
        ctx.setVariable("imageResourceName", imageResourceName); // so that we can reference it from HTML

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message =
                new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject("Example HTML email with inline image");
        message.setFrom("thymeleaf@example.com");
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf
        final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
        message.setText(htmlContent, true); // true = isHtml

        // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
        final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
        message.addInline(imageResourceName, imageSource, imageContentType);

        // Send mail
        javaMailSender.send(mimeMessage);

    }



}
