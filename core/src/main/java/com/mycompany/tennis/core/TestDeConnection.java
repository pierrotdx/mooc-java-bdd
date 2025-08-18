package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
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
