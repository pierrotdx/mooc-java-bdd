package com.mycompany.tennis.consoleui;

import com.mycompany.tennis.consoleui.controller.EpreuveController;
import com.mycompany.tennis.consoleui.controller.JoueurController;
import com.mycompany.tennis.consoleui.controller.ScoreController;
import com.mycompany.tennis.consoleui.controller.TournoiController;

public class UI {
    public static void main(String[] args) {
//        JoueurController joueurController = new JoueurController();
//        joueurController.rennomeJoueur();

//        TournoiController tournoiController = new TournoiController();
//        tournoiController.supprimeTournoi();

//        ScoreController scoreController = new ScoreController();
//        scoreController.afficheDetailsScore();

        EpreuveController epreuveController = new EpreuveController();
        epreuveController.afficheRolandGarros();
    }
}
