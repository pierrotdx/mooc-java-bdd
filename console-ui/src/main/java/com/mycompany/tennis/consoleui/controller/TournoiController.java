package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {
    private TournoiService tournoiService;

    public TournoiController() {
        this.tournoiService = new TournoiService();
    }

    public void afficheDetailsTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi");
        Long id = scanner.nextLong();
        Tournoi tournoi = this.tournoiService.getTournoi(id);
        System.out.println("Le tournoi selectionné est " + tournoi.getNom());
    }

    public void creerTournoi() {
        Tournoi tournoi = new Tournoi();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du tournoi");
        String nom = scanner.nextLine();
        tournoi.setNom(nom);

        System.out.println("Entrez le code du tournoi");
        String code = scanner.nextLine();
        tournoi.setCode(code);

        this.tournoiService.createTournoi(tournoi);
        System.out.println("Le tournoi a été créé avec l'id " + tournoi.getId());
    }

    public void supprimeTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi que vous voulez supprimer");
        Long id = scanner.nextLong();
        this.tournoiService.supprimerTournoi(id);
    }
}
