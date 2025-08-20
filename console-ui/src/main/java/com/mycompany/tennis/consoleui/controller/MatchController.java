package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.MatchService;

import java.util.Scanner;

public class MatchController {
    private MatchService matchService;

    public MatchController() {
        this.matchService = new MatchService();
    }

    public void afficherDetailsMatch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre l'identifiant du match dont vous voulez les détails");
        Long identifiant = scanner.nextLong();

        MatchDto matchDto = this.matchService.getMatch(identifiant);

        JoueurDto vainqueur = matchDto.getVainqueur();
        JoueurDto finaliste = matchDto.getFinaliste();
        System.out.println("Il s'agit d'un match de " + matchDto.getEpreuve().getAnnee() + " qui s'est déroulé à " + matchDto.getEpreuve().getTournoi().getNom());
        System.out.printf("%s %s a remporté le match face à %s %s\n", vainqueur.getPrenom(), vainqueur.getNom(), finaliste.getPrenom(), finaliste.getNom());

        ScoreFullDto scoreFullDto = matchDto.getScore();
        if (scoreFullDto.getSet1() != null) {
            System.out.println("Set 1: " + scoreFullDto.getSet1());
        }
        if (scoreFullDto.getSet2() != null) {
            System.out.println("Set 2: " + scoreFullDto.getSet2());
        }
        if (scoreFullDto.getSet3() != null) {
            System.out.println("Set 3: " + scoreFullDto.getSet3());
        }
        if (scoreFullDto.getSet4() != null) {
            System.out.println("Set 4: " + scoreFullDto.getSet4());
        }
        if (scoreFullDto.getSet5() != null) {
            System.out.println("Set 5: " + scoreFullDto.getSet5());
        }

    }
}
