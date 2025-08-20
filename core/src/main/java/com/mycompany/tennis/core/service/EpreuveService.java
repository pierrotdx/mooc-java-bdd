package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveFullDto(Long id) {
        Session session = null;
        Epreuve epreuve = null;
        Transaction tx = null;
        EpreuveFullDto dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = this.epreuveRepository.getById(id);
            Hibernate.initialize(epreuve.getTournoi());
            tx.commit();
            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            dto.setTournoi(tournoiDto);
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return dto;
    }

    public EpreuveLightDto getEpreuveLightDto(Long id) {
        Session session = null;
        Epreuve epreuve = null;
        Transaction tx = null;
        EpreuveLightDto dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = this.epreuveRepository.getById(id);
            tx.commit();
            dto = new EpreuveLightDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return dto;
    }
}
