package com.mycompany.tennis.consoleui.controller;

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

    public void creerJoueur() {
        Joueur joueur = new Joueur();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est le nom du joueur ?");
        String nom = scanner.nextLine();
        joueur.setNom(nom);

        System.out.println("Quel est le prénom du joueur ?");
        String prenom = scanner.nextLine();
        joueur.setPrenom(prenom);

        System.out.println("Quel est le sexe du joueur (H/F) ?");
        Character sexe = scanner.nextLine().charAt(0);
        joueur.setSexe(sexe);

        this.joueurServie.createJoueur(joueur);
        System.out.println("Le joueur a été créé avec l'id " + joueur.getId());
    }
}
