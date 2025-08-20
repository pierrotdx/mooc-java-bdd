package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public TournoiDto getTournoiDto(Long id) {
        Session session = null;
        Transaction tx = null;
        TournoiDto tournoiDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Tournoi tournoi = this.tournoiRepository.getById(id);

            tournoiDto = new TournoiDto();
            tournoiDto.setId(tournoi.getId());
            tournoiDto.setCode(tournoi.getCode());
            tournoiDto.setNom(tournoi.getNom());

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tournoiDto;
    }

    public void createTournoi(TournoiDto tournoiDto) {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(tournoiDto.getId());
        tournoi.setCode(tournoiDto.getCode());
        tournoi.setNom(tournoiDto.getNom());
        this.tournoiRepository.create(tournoi);
    }

    public void supprimerTournoi(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            this.tournoiRepository.delete(id);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
