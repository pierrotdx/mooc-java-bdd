package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.tennis.core.DataSourceProvider.getSingleDataSourceInstance;

public class TournoiRepositoryImpl {
    public Long create(Tournoi tournoi) {
        Connection conn = null;
        try {
            DataSource dataSource = getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO TOURNOI (NOM, CODE) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tournoi.getNom());
            statement.setString(2, tournoi.getCode());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                Long id = rs.getLong(1);
                tournoi.setId(id);
            }

            System.out.println("Tournoi créé.");
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
        return tournoi.getId();
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
        Connection conn = null;
        try {
            DataSource dataSource = getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");
            statement.setLong(1, id);

            statement.executeUpdate();
            System.out.println("Tournoi supprimé.");
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

    public Tournoi getById(Long id) {
        Session session = null;
        Tournoi tournoi = null;
        try {
            System.out.println("Tournoi lu.");
            session = HibernateUtil.getSessionFactory().openSession();
            tournoi = session.get(Tournoi.class, id);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
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
