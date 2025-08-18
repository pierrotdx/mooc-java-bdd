package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurServie;

    public JoueurController() {
        this.joueurServie = new JoueurService();
    }

    public void afficheDetailsJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        Joueur joueur = this.joueurServie.getJoueur(identifiant);
        System.out.println("Le joueur selectionné s'appelle " + joueur.getPrenom() + " " + joueur.getNom());
    }
}
