package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.List;
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

    public void rennomeJoueur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant du joueur dont vous voulez afficher les informations ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Quel est le nouveau nom que vous souhaitez lui attibuer ?");
        String nouveauNom = scanner.nextLine();

        this.joueurServie.renomme(identifiant, nouveauNom);
    }

    public void changeDeSexe() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant du joueur que vous voulez mettre à jour ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Quel est le nouveau sexe que vous voulez lui attribuer ?");
        Character nouveauSexe = scanner.nextLine().charAt(0);

        this.joueurServie.changerDeSexe(identifiant, nouveauSexe);
    }

    public void supprimeJoueur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est l'identifiant du joueur à supprimer ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine();

        this.joueurServie.deleteJoueur(identifiant);
    }

    public void afficheListeJoueurs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous une liste homme (H) ou femme (F) ?");
        char sexe = scanner.nextLine().charAt(0);

        List<JoueurDto> joueursDto = this.joueurServie.getListeJoueurs(sexe);
        for (JoueurDto joueurDto : joueursDto) {
            System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
        }
    }
}
