package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import static com.mycompany.tennis.core.DataSourceProvider.getSingleDataSourceInstance;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        System.out.println("Joueur créé.");
    }

    public void delete(Long id) {
       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       Joueur joueur = this.getById(id);
       session.delete(joueur);
    }

    public Joueur getById(Long id) {
        Session session = null;
        Joueur joueur = null;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);
        System.out.println("Joueur lu.");
        return joueur;
    }

    public List<Joueur> list(char sexe) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createQuery("select j from Joueur j where j.sexe = ?0", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueurs = query.getResultList();
        System.out.println("Joueur lus.");
        return joueurs;
    }
}
