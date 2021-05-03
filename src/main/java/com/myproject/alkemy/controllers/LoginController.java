package com.myproject.alkemy.controllers;

import com.myproject.alkemy.models.entity.Student;
import com.myproject.alkemy.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class LoginController {

    @Autowired
    private IStudentService studentService;


    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash, Authentication authentication) {

        /*if(principal != null) { //Si es distinto de null es porque ya habia iniciado sesión anteriormente.
            flash.addFlashAttribute("info", "You have already logged in previously");
            return "redirect:/list"; //Se evita que vuelva a iniciarse sesion.
        }*/

        if(principal != null){
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                if(grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
                    flash.addFlashAttribute("info", "You have already logged in previously");
                    return "redirect:/admin";
                } else {
                    flash.addFlashAttribute("info", "You have already logged in previously");
                    return "redirect:/list";
                }
            }
        }

        if(error != null) {
            model.addAttribute("error", "Incorrect user or password. Try again");
        }

        if(logout != null) {
            model.addAttribute("logout", "You have successfully logged out");
        }

        return "login";

    }
}
