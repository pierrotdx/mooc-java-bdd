package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true","COURSDB","coursdb");

            PreparedStatement statement = conn.prepareStatement("SELECT NOM, PRENOM, ID FROM JOUEUR WHERE ID=?");
            long identifiant = 12L;
            statement.setLong(1, identifiant);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                final String nom = rs.getString("NOM");
                final String prenom = rs.getString("PRENOM");
                final long id = rs.getInt("ID");
                System.out.printf("Le/la joueur/joueuse %s %s est représenté(e) par l'id %d%n.", prenom, nom, id);
            }

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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
