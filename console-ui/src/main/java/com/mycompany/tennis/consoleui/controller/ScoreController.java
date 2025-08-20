package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.ScoreService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ScoreController {
    private ScoreService scoreService = null;

    public ScoreController() {
        this.scoreService = new ScoreService();
    }

    public void afficheDetailsScore() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant du score ?");
        Long identifiant = scanner.nextLong();

        ScoreFullDto scoreFullDto = this.scoreService.getScore(identifiant);

        EpreuveFullDto epreuveFullDto = scoreFullDto.getMatch().getEpreuve();
        System.out.println("Tournoi : " + epreuveFullDto.getTournoi().getNom());
        System.out.println("Année de l'épreuve : " + epreuveFullDto.getAnnee());
        System.out.println("Type de l'épreuve : " + epreuveFullDto.getTypeEpreuve());
        System.out.println("Score :");
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
