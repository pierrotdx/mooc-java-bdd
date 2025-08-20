package com.mycompany.tennis.consoleui;

import com.mycompany.tennis.consoleui.controller.*;

public class UI {
    public static void main(String[] args) {
        JoueurController joueurController = new JoueurController();
        joueurController.afficheListeJoueurs();

//        TournoiController tournoiController = new TournoiController();
//        tournoiController.supprimeTournoi();

//        ScoreController scoreController = new ScoreController();
//        scoreController.supprimerScore();

//        EpreuveController epreuveController = new EpreuveController();
//        epreuveController.afficheDetailsEpreuve();

//        MatchController matchController = new MatchController();
//        matchController.ajouterMatch();
    }
}
