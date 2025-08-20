package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.TournoiDto;
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
        TournoiDto tournoiDto = this.tournoiService.getTournoiDto(id);
        System.out.println("Le tournoi selectionné est " + tournoiDto.getNom());
    }

    public void creerTournoi() {
        TournoiDto tournoiDto = new TournoiDto();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom du tournoi");
        String nom = scanner.nextLine();
        tournoiDto.setNom(nom);

        System.out.println("Entrez le code du tournoi");
        String code = scanner.nextLine();
        tournoiDto.setCode(code);

        this.tournoiService.createTournoi(tournoiDto);
        System.out.println("Le tournoi a été créé avec l'id " + tournoiDto.getId());
    }

    public void supprimeTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du tournoi que vous voulez supprimer");
        Long id = scanner.nextLong();
        this.tournoiService.supprimerTournoi(id);
    }
}
