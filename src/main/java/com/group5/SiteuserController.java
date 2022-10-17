package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class SiteuserController
{
    @Autowired
    SiteuserRepository repository;

    @GetMapping("/login")
    String getlogin() {
        return "login";
    }


    @PostMapping("/login")
    String postlogin(@RequestParam String username, @RequestParam String password, HttpSession session)
    {
        //System.out.printf("\n\n\n\nAngivet username: %s med password: %s",username,password);

        Siteuser user = repository.findUser(username);

        if(user == null) {
            System.out.printf("\n\n\n\nAnvändaren %s finns inte", username);
            return "login";
        }

        if(password.equals(user.getPassword())) {
            System.out.printf("\n\n\n\nINLOGGAD SOM %s med password %s", user.getUsername(), user.getPassword());
            session.setAttribute("siteuser",user);
            return "frontpage";
        }
        else
            System.out.printf("Fel lösenord för användare %s", user.getUsername());

        return "login";
    }


    @GetMapping("/logoutuser")
    public String logout(HttpSession session, HttpServletResponse res)
    {
        session.removeAttribute("siteuser");
        return "redirect:/";
    }

    @GetMapping("/newuser")
    public String showRegistrationForm(Model model) {
        Siteuser user = new Siteuser();
        model.addAttribute("user", user);

        return "newuser";
    }

    @PostMapping("/newuser")
    public String registerUserAccount(@ModelAttribute Siteuser user, BindingResult bindingResult, Model model)
    {
        if(repository.findUser(user.getUsername()) != null)
        {
            System.out.println("User already exists");
            return "newuser";
        }
        model.addAttribute("user",user);
        repository.createUser(user);

        return "frontpage";
    }

    @GetMapping("/userpage")
    public String getUserPage(HttpSession session)
    {
        if(session.getAttribute("siteuser") == null)
            return "login";

        return "userpage";
    }
}
