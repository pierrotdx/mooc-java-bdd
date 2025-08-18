package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();

        List<Tournoi> tournois = tournoiRepository.list();
        for (Tournoi tournoi : tournois) {
            System.out.println(tournoi.getNom() + " (" + tournoi.getCode() + ")");
        }

        Tournoi rollandGarros =  tournoiRepository.getById(2L);
        System.out.println(rollandGarros.getNom() + " a le code " + rollandGarros.getCode());

        Tournoi grenoble = new Tournoi();
        grenoble.setNom("Open de Grenoble");
        grenoble.setCode("OG");
        long id = tournoiRepository.create(grenoble);
        grenoble.setId(id);

        grenoble = tournoiRepository.getById(grenoble.getId());
        grenoble.setNom("FR Open de Grenoble");
        tournoiRepository.update(grenoble);

        tournoiRepository.delete(grenoble.getId());
    }
}
