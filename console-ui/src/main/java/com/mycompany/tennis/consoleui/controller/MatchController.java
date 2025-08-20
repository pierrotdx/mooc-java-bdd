package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.service.MatchService;
import org.jvnet.staxex.MtomEnabled;

import java.util.Scanner;

public class MatchController {
    private MatchService matchService;

    public MatchController() {
        this.matchService = new MatchService();
    }

    public void afficherDetailsMatch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre l'identifiant du match dont vous voules les détails");
        Long identifiant = scanner.nextLong();

        MatchDto matchDto = this.matchService.getMatch(identifiant);

        JoueurDto vainqueur = matchDto.getVainqueur();
        JoueurDto finaliste = matchDto.getFinaliste();
        System.out.println("Il s'agit d'un match de " + matchDto.getEpreuve().getAnnee() + " qui s'est déroulé à " + matchDto.getEpreuve().getTournoi().getNom());
        System.out.printf("%s %s a remporté le match face à %s %s\n", vainqueur.getPrenom(), vainqueur.getNom(), finaliste.getPrenom(), finaliste.getNom());
    }
}
