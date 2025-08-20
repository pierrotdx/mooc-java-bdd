package com.mycompany.tennis.consoleui.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.List;
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
        EpreuveFullDto epreuve = this.epreuveService.getEpreuveDetaillee(id);
        System.out.println("Le nom du tournoi est " + epreuve.getTournoi().getNom());
        for (JoueurDto joueurDto : epreuve.getParticipants()) {
        System.out.println(joueurDto.getPrenom() + " " + joueurDto.getNom());
        }
    }

    public void afficheRolandGarros() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez l'identifiant de l'épreuve");
        Long id = scanner.nextLong();
        EpreuveLightDto epreuve = this.epreuveService.getEpreuveLightDto(id);
    }

    public void afficheListeEpreuves() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le code du tournoi ?");
        String codeTournoi = scanner.nextLine();

        List<EpreuveFullDto> epreuveFullDtos = this.epreuveService.getListeEpreuves(codeTournoi);
        for (EpreuveFullDto epreuveFullDto : epreuveFullDtos) {
            System.out.println(epreuveFullDto.getId() + " " + epreuveFullDto.getAnnee() + " " + epreuveFullDto.getTypeEpreuve() + " " + epreuveFullDto.getTournoi().getNom());
        }
    }
}
