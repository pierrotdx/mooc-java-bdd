package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuve(Long id) {
        Session session = null;
        Epreuve epreuve = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = this.epreuveRepository.getById(id);
            System.out.println("L'épreuve selectionnée se déroule en " + epreuve.getAnnee() + " et il s'agit du tournoi " + epreuve.getTournoi().getNom());
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return epreuve;
    }
}
