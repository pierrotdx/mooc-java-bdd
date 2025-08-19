package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.mycompany.tennis.core.DataSourceProvider.getSingleDataSourceInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(joueur);
            tx.commit();
            System.out.println("Joueur créé.");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null)  {
            tx.rollback();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            DataSource dataSource = getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");
            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getSexe().toString());
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
            DataSource dataSource = getSingleDataSourceInstance();

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
        Session session = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            joueur = session.get(Joueur.class, id);
            System.out.println("Joueur lu.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return joueur;
    }

    public List<Joueur> list() {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<Joueur>();
        try {
            DataSource dataSource = getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE, ID FROM JOUEUR");


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
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

    public void renomme(Long id, String nouveauNom) {
        Session session = null;
        Joueur joueur = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueur = session.get(Joueur.class, id);
            joueur.setNom(nouveauNom);
            tx.commit();
            System.out.println("Nom du joueur modifié " + joueur.getNom());
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
