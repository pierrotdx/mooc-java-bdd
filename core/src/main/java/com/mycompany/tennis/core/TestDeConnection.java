package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurService joueurService = new JoueurService();

        Joueur noah = new Joueur();
        noah.setPrenom("Yannik"); // faute délibérée pour ensuite update
        noah.setNom("Noah");
        noah.setSexe('H');
        joueurService.createJoueur(noah);

        System.out.println("L'idendifiant du joueur créé est " + noah.getId());
    }
}
