package com.BONGAZ.gestionCommande.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "elementCommande" )
public class ElementCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Commande commande;

    @ManyToOne
    private Produit produit;
    private int quantite;
    private Date dateCreation;



}
