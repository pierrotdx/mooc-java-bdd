package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

import java.sql.SQLOutput;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurService joueurService = new JoueurService();

        Joueur noah = new Joueur();
        noah.setPrenom("Yannik"); // faute délibérée pour ensuite update
        noah.setNom("Noah");
        noah.setSexe('H');
        joueurService.createJoueur(noah);

        System.out.println("L'idendifiant du joueur créé est " + noah.getId());

        Joueur joueur1 = joueurService.getJoueur(1L);
        System.out.println("Le premier joueur de la base est " + joueur1.getPrenom() + " " + joueur1.getNom());

        TournoiService tournoiService = new TournoiService();
        Tournoi tournoi1 = tournoiService.getTournoi(1L);
        System.out.println("Le premier tournoi de la base est " + tournoi1.getNom());

        Tournoi openGrenoble = new Tournoi();
        openGrenoble.setNom("Open de Grenoble");
        openGrenoble.setCode("OG");
        tournoiService.createTournoi(openGrenoble);
        System.out.println("Le tournoi de Grenoble a été créé avec l'id " + openGrenoble.getId());
    }
}
