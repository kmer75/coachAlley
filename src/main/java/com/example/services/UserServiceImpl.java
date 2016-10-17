package com.example.services;

import com.example.Utils.CheckEmailException;
import com.example.Utils.UrlContextPath;
import com.example.entities.Role;
import com.example.entities.Token;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by kevin on 12/10/2016.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailSenderServiceImpl emailSender;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByTokenToken(String token) {
        return userRepository.findUserByTokenToken(token);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void creerCoach(User u, HttpServletRequest hsr) throws MessagingException {
        Token t = new Token(tokenService.createToken());
        Role role = roleService.findRoleByName("COACH");
        u.getRoles().add(role);
        t.setUser(u);
        this.save(u);
        tokenService.save(t);
        envoyerEmailActivationCompte(u.getEmail(), t, hsr);
    }

    @Override
    public void envoyerEmailActivationCompte(String email, Token token, HttpServletRequest hsr) throws MessagingException {
        String url = UrlContextPath.getURLWithContextPath(hsr);
        String to = email; String  subject = "Activation du compte";
        String content = " <!DOCTYPE html><html><head><title>Page Title</title></head><body>";
        content += "<p>yo mon gava bien ou koi !? tu t'es bien inscris, tu n'as plus qu'a cliquer sur ce lien pour activer ton compte : </p>";
        content += "<p><a href='" + url + "/activer?token=" + token.getToken() + "'>activer</a></p>";
        content += "</body></html>";
        emailSender.send(to, subject, content);
    }

    @Override
    public void verifierEmail(String email) throws CheckEmailException {
        User user = userRepository.findUserByEmail(email);
        if(user != null) {
            if(!user.isEnabled()) {
                try {
                    emailSender.send(user.getEmail(), "activer compte", "salut nouveau lien pour activer compte");
                } catch (MessagingException e) {
                    System.out.println("email non envoyé");
                    throw new CheckEmailException("cet email est déjà utilisé, mais vous n'avez pas active le compte, nous avons tenté de vous envoyer un mail pour le réactiver mais cela a échoué." +
                            "Réessayez plus tard et si le problème persiste veuillez nous contacter");
                }
                throw new CheckEmailException("cet email est déjà utilisé, mais vous n'avez pas active le compte, un mail vous a été envoyé pour le faire");
            } else throw new CheckEmailException("cet email est déjà utilisé");
        }

    }

    @Override
    public User activerCompte(String token) throws Exception{
        User u = this.findUserByTokenToken(token);
        if(u == null) {
            throw new Exception("User non trouvé");
        }
        u.setConfirmPassword(u.getPassword()); //pour valider validator de l'entite
        u.setEnabled(true);
        u.setAccountNonLocked(true);
        System.err.println("accountnonlocked "+u.isAccountNonLocked());
        System.err.println("apres set accountnonlocked "+u.isAccountNonLocked());
        this.save(u);
        return u;
    }

    @Override
    public void forgetPasswordEnvoieMail(String email) throws CheckEmailException {
        User user = userRepository.findUserByEmail(email);
        String uuid = UUID.randomUUID().toString();
        String [] mdp = uuid.split("-");
        if(user != null) {
            if(!user.isEnabled()) {
                throw new CheckEmailException("Votre compte n'a pas été activé");
            } else {
                    try {
                        emailSender.send(user.getEmail(), "mot de passe oublié", "Un nouveau mot de passe vous a été assigné : " + mdp[0]);
                        user.setPasswordAndConfirmPassword(mdp[0]);
                        this.save(user);
                        } catch (MessagingException e) {
                            System.out.println("email non envoyé");
                            throw new CheckEmailException("Il y a eu un problème lors de l'envoie du mail de réinitialisation du mot de passe, veuillez réessayer ultérieurement");
                         }
                    }
            } else throw new CheckEmailException("Aucun compte ne correspond à cette adresse email");
    }

}
