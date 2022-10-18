package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

import static java.lang.String.format;

@Repository
public class SiteuserRepository
{
    @Autowired
    private DataSource dataSource;

    public Siteuser findUser(Integer id)
    {
        Siteuser user = null;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, username, password FROM siteuser WHERE id = " + id)) {

            if (rs.next()){
                user = (new Siteuser(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Siteuser findUser(String username)
    {
        Siteuser user = null;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Siteuser WHERE username = '" + username + "'")) {

            if (rs.next()){
                user = (new Siteuser(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public void createUser(Siteuser user)
    {
        byte[] hashedPw = null;

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //md.update(salt);
            hashedPw = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashedPw.length; i++) {
            sb.append(Integer.toString((hashedPw[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        String generatedPassword = sb.toString();


        String sqlQueryString = format("INSERT INTO SITEUSER (USERNAME, PASSWORD) VALUES ('%s','%s');", user.getUsername(), generatedPassword);

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO SITEUSER(USERNAME, PASSWORD) VALUES (?,?) ")) {
            ps.setString(1, user.getUsername());
            ps.setString(2, hashedPw.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
