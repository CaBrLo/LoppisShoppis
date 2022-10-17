package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SiteuserController
{
    @Autowired
    SiteuserRepository repository;

    @Autowired
    private DataSource dataSource;

    @PostMapping("/")
    String firstPage(@RequestParam String username, @RequestParam String password, HttpSession session)
    {
        //System.out.printf("Input name was: %s and password was: %s",username,password);
        //System.out.println();

        String pw = "";
        Siteuser siteuser = new Siteuser();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Siteuser WHERE username = '" + username + "'")) {

            if (rs.next()){
                // todo spara hela användaren
                siteuser.setId(rs.getInt("id"));
                siteuser.setUsername(rs.getString("username"));
                siteuser.setPassword(rs.getString("password"));
                //pw = rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Siteuser siteuser = repository.findByUser_name(username);


        if(password.equals(siteuser.getPassword()))
        {
            session.setAttribute("siteuser",siteuser);
            System.out.printf("\n\n\n\nINLOGGAD SOM %s %s med password %s",siteuser.getUsername(),siteuser.getPassword());

            //return "frontpage";
        }
        return "frontpage";
    }

    @GetMapping("/logoutuser")
    public String logout(HttpSession session, HttpServletResponse res)
    {
        session.removeAttribute("siteuser");
        return "redirect:/";
    }

    @GetMapping("/newUser")
    public String showRegistrationForm(Model model) {
        Siteuser user = new Siteuser();

        model.addAttribute("user", user);

        return "newUser";
    }
}
