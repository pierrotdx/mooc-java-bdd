package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import com.mycompany.tennis.core.service.ScoreService;

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

        Score score = this.scoreService.getScore(identifiant);

        System.out.println("Le score est le suivant :");
        if (score.getSet1() != null) {
            System.out.println("Set 1: " + score.getSet1());
        }
        if (score.getSet2() != null) {
            System.out.println("Set 2: " + score.getSet1());
        }
        if (score.getSet3() != null) {
            System.out.println("Set 3: " + score.getSet1());
        }
        if (score.getSet4() != null) {
            System.out.println("Set 4: " + score.getSet1());
        }
        if (score.getSet5() != null) {
            System.out.println("Set 5: " + score.getSet1());
        }
    }
}
