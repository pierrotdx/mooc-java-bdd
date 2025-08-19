package com.mycompany.tennis.consoleui;

import com.mycompany.tennis.consoleui.controller.JoueurController;
import com.mycompany.tennis.consoleui.controller.TournoiController;

public class UI {
    public static void main(String[] args) {
        JoueurController joueurController = new JoueurController();
        joueurController.supprimeJoueur();

//        TournoiController tournoiController = new TournoiController();
//        tournoiController.creerTournoi();
    }
}
