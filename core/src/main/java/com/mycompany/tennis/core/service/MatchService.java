package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;

public class MatchService {
    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;

    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match) {
        matchRepository.create(match);
        scoreRepository.create(match.getScore());
    }

    public MatchDto getMatch(Long id) {
        Session session = null;
        Transaction tx = null;
        MatchDto matchDto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Match match = this.matchRepository.getById(id);
            matchDto = new MatchDto();
            matchDto.setId(match.getId());

            Joueur finaliste = match.getFinaliste();
            JoueurDto finalisteDto = new JoueurDto();
            finalisteDto.setId(finaliste.getId());
            finalisteDto.setNom(finaliste.getNom());
            finalisteDto.setPrenom(finaliste.getPrenom());
            finalisteDto.setSexe(finaliste.getSexe());
            matchDto.setFinaliste(finalisteDto);

            Joueur vainqueur = match.getVainqueur();
            JoueurDto vainqueurDto = new JoueurDto();
            vainqueurDto.setId(vainqueur.getId());
            vainqueurDto.setNom(vainqueur.getNom());
            vainqueurDto.setPrenom(vainqueur.getPrenom());
            vainqueurDto.setSexe(vainqueur.getSexe());
            matchDto.setVainqueur(vainqueurDto);

            Epreuve epreuve = match.getEpreuve();
            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();
            epreuveFullDto.setId(epreuve.getId());
            epreuveFullDto.setAnnee(epreuve.getAnnee());
            epreuveFullDto.setTypeEpreuve(epreuve.getTypeEpreuve());
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            epreuveFullDto.setTournoi(tournoiDto);
            matchDto.setEpreuve(epreuveFullDto);

            tx.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return matchDto;
    }
}
