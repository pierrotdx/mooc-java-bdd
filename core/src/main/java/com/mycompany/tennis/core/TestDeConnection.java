package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepository;

import java.sql.*;
import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepository joueurRepository = new JoueurRepository();

        List<Joueur> joueurs = joueurRepository.list();

        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getPrenom() + " " + joueur.getNom());
        }
    }
}
