package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class TestDeConnection {
    public static void main(String... args){
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();

        List<Joueur> joueurs = joueurRepository.list();
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getPrenom() + " " + joueur.getNom());
        }

        Joueur bartoli =  joueurRepository.getById(21l);
        System.out.println(bartoli.getPrenom() + " " + bartoli.getNom());

        Joueur noah = new Joueur();
        noah.setPrenom("Yannik"); // faute délibérée pour ensuite update
        noah.setNom("Noah");
        noah.setSexe('H');
        long id = joueurRepository.create(noah);
        noah.setId(id);

        noah = joueurRepository.getById(noah.getId());
        noah.setPrenom("Yannick");
        joueurRepository.update(noah);

        joueurRepository.delete(noah.getId());



    }
}
