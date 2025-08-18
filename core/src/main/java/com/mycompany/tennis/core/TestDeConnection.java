package com.mycompany.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("tennis");
            dataSource.setUseSSL(false);
            dataSource.setAllowPublicKeyRetrieval(true);
            dataSource.setUser("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement statement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, sexe);

            statement.executeUpdate();

            nom = "Johannson";
            prenom = "Thomas";
            sexe = "H";
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, sexe);

            statement.executeUpdate();
            conn.commit();
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
