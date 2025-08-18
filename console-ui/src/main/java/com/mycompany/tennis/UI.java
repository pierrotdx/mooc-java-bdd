package com.mycompany.tennis;

import com.mycompany.tennis.controller.JoueurController;
import com.mycompany.tennis.controller.TournoiController;

public class UI {
    public static void main(String[] args) {
        TournoiController tournoiController = new TournoiController();
        tournoiController.creerTournoi();
    }
}
