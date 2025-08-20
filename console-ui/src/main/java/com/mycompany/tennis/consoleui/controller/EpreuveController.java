package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {
    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

    public void afficheDetailsEpreuve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'épreuve");
        Long id = scanner.nextLong();
        Epreuve epreuve = this.epreuveService.getEpreuve(id);
    }
}
