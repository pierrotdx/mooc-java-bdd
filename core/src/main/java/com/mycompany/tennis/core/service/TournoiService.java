package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public Tournoi getTournoi(Long id) {
        return this.tournoiRepository.getById(id);
    }

    public void createTournoi(Tournoi tournoi) {
        this.tournoiRepository.create(tournoi);
    }

}
