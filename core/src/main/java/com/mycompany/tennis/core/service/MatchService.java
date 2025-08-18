package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.dao.MatchDaoImpl;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {
    private MatchDaoImpl matchDao;

    public MatchService() {
        this.matchDao = new MatchDaoImpl();
    }

    public void enregistrerNouveauMatch(Match match) {
        this.matchDao.createMatchWithScore(match);
    }
}
