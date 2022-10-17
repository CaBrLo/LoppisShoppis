package com.group5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
        String sqlQueryString = format("INSERT INTO SITEUSER (USERNAME, PASSWORD) VALUES ('%s','%s');", user.getUsername(), user.getPassword());

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO SITEUSER(USERNAME, PASSWORD) VALUES (?,?) ")) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
