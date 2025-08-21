package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EpreuveRepositoryImpl {
    public Epreuve getById(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, id);
        System.out.println("Epreuve lue.");
        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();

        // `e.tournoi.code` (join) est utilisable car on a déclaré la relation entre Epreuve et Tournoi comme `@ManyToOne`
        // en revanche, le `fetch` (dynamic fetching) fonctionne avec tous types de relations
        TypedQuery<Epreuve> query = em.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code = ?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuves lues.");
        return epreuves;
    }
}
