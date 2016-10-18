package com.example.controllers;

import com.example.Utils.CheckEmailException;
import com.example.entities.Role;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by kevin on 14/10/2016.
 */
@SessionAttributes("user")
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    InscriptionUserService inscriptionUserService;
    @Autowired
    AuthentificationService authentificationService;

/* enregistrement utilisateur -> role coach */

    //formulaire verification du mail si existe ou pas en base

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String checkMailForm(Model model) {
        return "verifEmail";
    }

    //si existe: message, sinon go formulaire

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String checkMail(@RequestParam(value = "email") String email, Model model, RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        try {
            inscriptionUserService.afficherFormulaireInscription(email, hsr);
        } catch (CheckEmailException e) {
            System.out.println("passe ds le catch");
            redirectAttributes.addFlashAttribute("messageInfo", e.getMessage());
            return "redirect:/form";
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("messageInfo", "Une erreur est apparu lors l'envoie du mail afin de réactiver votre compte, vueillez réessayer ultérieurement");
            return "redirect:/form";
        }
        model.addAttribute("user", new User(email));
        return "user";
    }

    //enregistrement et envoie du mail

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        if(bindingResult.hasErrors()) {;
            return "user";
        }

        try {
            inscriptionUserService.creerUser(user, hsr);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "il y a eu une erreur lors de l'enregistrement, veuillez reessayer");
            return "redirect:/user";
        }
        redirectAttributes.addFlashAttribute("messageSuccess", "ajouté avec succes");
        redirectAttributes.addFlashAttribute("messageInfo", "merci " + user.getLastname()+ " de vous etre inscrit, " +
                "un email vous a ete envoyé pour valider le mail a l'adresse "+user.getEmail());

        return "redirect:/index";
    }

        /* ----------------------------------------------------------------------------- */

    /* activation du compte */

    @RequestMapping(value = "/activer", method = RequestMethod.GET)
    public String activer(Model model, @RequestParam(value = "token") String token, RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
            try {
                inscriptionUserService.activerCompte(token, hsr);
            }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("messageDanger", e.getMessage());
            return "redirect:/index";
        }
        redirectAttributes.addFlashAttribute("messageSuccess", "votre compte a été activé avec succès, vous pouvez vous connecter à présent");
        return "redirect:/index";
    }

        /* ----------------------------------------------------------------------------- */


    /* Oubli du mot de passe */

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String forgetPassword(Model model) {
        return "forgetPassword";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String verifMailForMdp(@RequestParam("email") String email, RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        try {
            authentificationService.forgetPasswordProcessFormIdentification(email, hsr);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageInfo", e.getMessage());
            return "redirect:/forgetPassword";
        }

        redirectAttributes.addFlashAttribute("messageSuccess", "un email vous a été envoyé afin de réinitialiser votre mot de passe");
        return "redirect:/index";
    }

    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String initPasswordForm(Model model, @RequestParam(value = "token") String token, RedirectAttributes redirectAttributes, HttpServletRequest hsr) {
        try {
            User u = authentificationService.forgetTokenExist(token, hsr);
            model.addAttribute("token", token);
            model.addAttribute("user", u);
            System.out.println(u);
            return "initPassword";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("messageInfo", e.getMessage());
            return "redirect:/index";
        }

    }

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public String initPassword(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,@RequestParam("token") String token, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
        System.out.println(user);
        System.out.println(token);
        if(bindingResult.hasFieldErrors("password") && bindingResult.hasFieldErrors("confirmPassword")) {
            System.out.println(bindingResult.getAllErrors());
            //return "/forget?token="+token;
        }
        System.out.println("mdp -> " + user.getPassword() + " confirm -> "+ user.getConfirmPassword());
        authentificationService.saveUserPasswordDeleteToken(user, token);
        sessionStatus.setComplete();
        redirectAttributes.addFlashAttribute("messageSuccess", "le mot de passe a été changé avec succès");
        return "redirect:/index";
    }
}
