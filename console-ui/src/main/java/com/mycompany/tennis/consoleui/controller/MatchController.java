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

    public void tapisVert() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant du match dont vous voulez effectuer le tapis vert ?");
        Long identifiant = scanner.nextLong();

        this.matchService.tapisVert(identifiant);
    }

    public void ajouterMatch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant de l'épreuve du match à ajouter ?");
        Long epreuveId = scanner.nextLong();
        scanner.nextLine();
        MatchDto matchDto = new MatchDto();
        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(epreuveId);

        System.out.println("Quel est l'identifiant du vainqueur du match à ajouter ?");
        Long vainqueurId = scanner.nextLong();
        scanner.nextLine();
        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);

        System.out.println("Quel est l'identifiant du finaliste du match à ajouter ?");
        Long finalisteId = scanner.nextLong();
        scanner.nextLine();
        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);

        System.out.println("Quelle est la valeur du set 1 ?");
        Byte set1 = scanner.nextByte();
        scanner.nextLine();
        ScoreFullDto scoreFullDto = new ScoreFullDto();
        scoreFullDto.setSet1(set1);

        System.out.println("Quelle est la valeur du set 2 ?");
        Byte set2 = scanner.nextByte();
        scanner.nextLine();
        scoreFullDto.setSet2(set2);

        System.out.println("Quelle est la valeur du set 3 ?");
        Byte set3 = scanner.nextByte();
        scanner.nextLine();
        scoreFullDto.setSet3(set3);

        System.out.println("Quelle est la valeur du set 4 ?");
        Byte set4 = scanner.nextByte();
        scanner.nextLine();
        scoreFullDto.setSet4(set4);

        System.out.println("Quelle est la valeur du set 5 ?");
        Byte set5 = scanner.nextByte();
        scanner.nextLine();
        scoreFullDto.setSet5(set5);

        matchDto.setScore(scoreFullDto);
        scoreFullDto.setMatch(matchDto);

        this.matchService.createMatch(matchDto);
    }

    public void supprimerMatch() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre l'identifiant du match que vous voulez supprimer");
        Long identifiant = scanner.nextLong();

        this.matchService.deleteMatch(identifiant);
    }
}
