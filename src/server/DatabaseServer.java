/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GeolseuDei
 */
public class DatabaseServer {

    String iduser;
    String tipeuser;

    public void insertLog(String id, String event, String waktu) {
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "insert into log (id_anggota,event,waktu) values('" + id + "','" + event + "','" + waktu + "')"
                );

                int a = sql.executeUpdate();
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public boolean Login(String username, String password) {
        boolean status = false;
        Connection myCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/programpendataan", "root", "");
            if (!myCon.isClosed()) {
                PreparedStatement sql = (PreparedStatement) myCon.prepareStatement(
                        "select * from login where username='" + username + "' and password='" + password + "'"
                );
                ResultSet hasil = sql.executeQuery();
                if (hasil.next()) {
                    iduser = hasil.getString("id");
                    tipeuser = hasil.getString("status");
                    status = true;
                }
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (myCon != null) {
                    myCon.close();
                }
            } catch (SQLException e) {
            }
        }
        return status;
    }
}
