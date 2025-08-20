package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {
    private EpreuveService epreuveService;

    public EpreuveController() {
        this.epreuveService = new EpreuveService();
    }

    public void afficheDerniereEpreuve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'épreuve");
        Long id = scanner.nextLong();
        EpreuveFullDto epreuve = this.epreuveService.getEpreuveFullDto(id);
        System.out.println("Le nom du tournoi est " + epreuve.getTournoi().getNom());
    }

    public void afficheRolandGarros() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'épreuve");
        Long id = scanner.nextLong();
        EpreuveLightDto epreuve = this.epreuveService.getEpreuveLightDto(id);
    }
}
