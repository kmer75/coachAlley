package com.example.controllers;

import com.example.services.EmailSenderServiceImpl;
import com.example.Utils.UrlContextPath;
import com.example.entities.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kevin on 12/10/2016.
 */
@Controller
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    EmailSenderServiceImpl emailSender;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }




}
