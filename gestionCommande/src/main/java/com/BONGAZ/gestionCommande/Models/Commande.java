package com.BONGAZ.gestionCommande.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commande_all" )

public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private LocalDateTime dateCommande;
    private double montantTotal;
    private String referenceClient;
    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL, orphanRemoval = true)

    private List<ElementCommande> elementscommande;

    public static Iterable<? extends ElementCommande> getElementscommande() {
        return getElementscommande();
    }


    public List<ElementCommande> getElementsCommande() {
        if (elementscommande == null) {
            elementscommande = new ArrayList<>(); // Initialise la liste si elle est actuellement nulle
        }
        return elementscommande;
    }
    private String instructionsSpeciales;
    private boolean servicesComplementaires;
    private boolean livraison;
    private String statut;





}
