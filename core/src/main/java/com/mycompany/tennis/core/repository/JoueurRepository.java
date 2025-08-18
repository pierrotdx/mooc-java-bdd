package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepository {

    public void create(Joueur joueur) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");
            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getPrenom().toString());

            statement.executeUpdate();
            System.out.println("Joueur créé.");
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

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");
            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getPrenom().toString());
            statement.setLong(4, joueur.getId());

            statement.executeUpdate();
            System.out.println("Joueur modifié.");
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

    public void delete(Long id) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
            statement.setLong(1, id);

            statement.executeUpdate();
            System.out.println("Joueur supprimé.");
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

    public Joueur getById(Long id) {
        Connection conn = null;
        Joueur joueur = null;
        try {
            BasicDataSource dataSource = new BasicDataSource();
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }
            System.out.println("Joueur lu.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueur;
    }

    public List<Joueur> list() {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<Joueur>();
        try {
            BasicDataSource dataSource = new BasicDataSource();
            int nbPools = 5;
            dataSource.setInitialSize(nbPools);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            dataSource.setUsername("COURSDB");
            dataSource.setPassword("coursdb");
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE, ID FROM JOUEUR");


            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }
            System.out.println("Joueur lus.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }
}
