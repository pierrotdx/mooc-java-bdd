package com.mycompany.tennis;

import com.mycompany.tennis.controller.JoueurController;
import com.mycompany.tennis.controller.TournoiController;
import com.mycompany.tennis.core.entity.Joueur;

public class UI {
    public static void main(String[] args) {
//        JoueurController joueurController = new JoueurController();
//        joueurController.afficheDetailsJoueur();

        TournoiController tournoiController = new TournoiController();
        tournoiController.afficheDetailsTournoi();
    }
}
