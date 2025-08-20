package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EpreuveRepositoryImpl {
    public Epreuve getById(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Epreuve epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lue.");
        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        // `e.tournoi.code` (join) est utilisable car on a déclaré la relation entre Epreuve et Tournoi comme `@ManyToOne`
        Query<Epreuve> query = session.createQuery("select e from Epreuve e where e.tournoi.code = ?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuves lues.");
        return epreuves;
    }
}
