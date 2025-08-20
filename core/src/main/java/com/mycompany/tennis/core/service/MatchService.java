package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
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

            Score score = match.getScore();
            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(score.getId());
            scoreFullDto.setSet1(score.getSet1());
            scoreFullDto.setSet2(score.getSet2());
            scoreFullDto.setSet3(score.getSet3());
            scoreFullDto.setSet4(score.getSet4());
            scoreFullDto.setSet5(score.getSet5());

            matchDto.setScore(scoreFullDto); // relation bi-directionnelle
            scoreFullDto.setMatch(matchDto);

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

    public void tapisVert(Long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            Match match = this.matchRepository.getById(id);
            Joueur ancienVainqueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainqueur);

            match.getScore().setSet1((byte)0);
            match.getScore().setSet2((byte)0);
            match.getScore().setSet3((byte)0);
            match.getScore().setSet4((byte)0);
            match.getScore().setSet5((byte)0);

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
    }
}
