package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.text.Highlighter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.tennis.core.DataSourceProvider.getSingleDataSourceInstance;

public class TournoiRepositoryImpl {
    public void create(Tournoi tournoi) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();;
        em.persist(tournoi);
        System.out.println("Tournoi créé.");
    }

    public void update(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE TOURNOI SET NOM=?, CODE=? WHERE ID=?");
            statement.setString(1, tournoi.getNom());
            statement.setString(2, tournoi.getCode());
            statement.setString(3, tournoi.getId().toString());

            statement.executeUpdate();
            System.out.println("Tournoi modifié.");
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
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, id);
        em.remove(tournoi);
    }

    public Tournoi getById(Long id) {
        EntityManager em = null;
        Tournoi tournoi = null;
        try {
            em = EntityManagerHolder.getCurrentEntityManager();
            tournoi = em.find(Tournoi.class, id);
            System.out.println("Tournoi lu.");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return tournoi;
    }

    public List<Tournoi> list() {
        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<Tournoi>();
        try {
            DataSource dataSource = getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT NOM, CODE, ID FROM TOURNOI");

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }
            System.out.println("Tournois lus.");
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
        return tournois;
    }
}
