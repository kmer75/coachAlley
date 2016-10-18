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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by kevin on 14/10/2016.
 */

@Controller
public class CoachController {

    @Autowired
    UserService userService;
    @Autowired
    InscriptionUserService inscriptionUserService;
    @Autowired
    CheckFieldService checkFieldService;

/* enregistrement utilisateur -> role coach */

    //formulaire verification du mail si existe ou pas en base

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String verif(Model model) {
        User user = new User();
        model.addAttribute("coach", new User());
        return "verifEmail";
    }

    //si existe: message, sinon go formulaire
/*
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String verif(@Valid @ModelAttribute("coach") User coach, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        try {
            checkFieldService.verifierEmailInscription(coach.getEmail());
        } catch (CheckEmailException e) {
            System.out.println("passe ds le catch");
            redirectAttributes.addFlashAttribute("messageInfo", e.getMessage());
            return "redirect:/form";
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("messageInfo", "Une erreur est apparu lors l'envoie du mail afin de réactiver votre compte, vueillez réessayer ultérieurement");
            return "redirect:/form";
        }
        return "user";
    }
*/
    //enregistrement et envoie du mail

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveRole(@Valid @ModelAttribute("coach") User coach, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {;
            return "user";
        }

        try {
            inscriptionUserService.creerUser(coach);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageDanger", "il y a eu une erreur lors de l'enregistrement, veuillez reessayer");
            return "redirect:/user";
        }
        redirectAttributes.addFlashAttribute("messageSuccess", "ajouté avec succes");
        redirectAttributes.addFlashAttribute("messageInfo", "merci " + coach.getLastname()+ " de vous etre inscrit, " +
                "un email vous a ete envoyé pour valider le mail a l'adresse "+coach.getEmail());

        return "redirect:/index";
    }

        /* ----------------------------------------------------------------------------- */

    /* activation du compte */

    @RequestMapping(value = "/activer", method = RequestMethod.GET)
    public String show(Model model, @RequestParam(value = "token") String token, RedirectAttributes redirectAttributes) {
            try {
                inscriptionUserService.activerCompte(token);
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
    public String verifMailForMdp(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            userService.forgetPasswordEnvoieMail(email);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("messageInfo", e.getMessage());
            return "redirect:/forgetPassword";
        }
        redirectAttributes.addFlashAttribute("messageInfo", "un email vous a été envoyé afin de réinitialiser votre mot de passe");
        return "redirect:/index";
    }

}
