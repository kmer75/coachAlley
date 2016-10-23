package com.example.controllers;

import com.example.services.EmailSenderServiceImpl;
import com.example.Utils.UrlContextPath;
import com.example.entities.User;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
    public String index(Model model, Principal principal) {
        String name = principal.getName();
        if(name != null) model.addAttribute("username", name);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log() {
        return "redirect:/login";
    }




}
