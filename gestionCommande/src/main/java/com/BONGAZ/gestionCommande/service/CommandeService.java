package com.BONGAZ.gestionCommande.service;

import com.BONGAZ.gestionCommande.Models.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class CommandeService {
    @Autowired
    private  CommandeRepository commandeRepository;

    @Autowired
    private ProduitRepository produitRepository;



    public Commande creerCommande(Commande commande) {
        // Vérifier la disponibilité des produits dans le panier
        boolean commandeDisponible = true;
        for (ElementCommande elementCommande : Commande.getElementscommande()) {
            Produit produit = produitRepository.findById(elementCommande.getProduit().getId()).orElseThrow(null);
            if (produit == null || elementCommande.getQuantite() > produit.getStock()) {
                commandeDisponible = false;
                break;
            }
        }

        // Si tous les produits sont disponibles, enregistrer la commande et réduire les stocks
        if (commandeDisponible) {
            Commande savedCommande = commandeRepository.save(commande);

            for (ElementCommande elementCommande : Commande.getElementscommande()) {
                Produit produit = produitRepository.findById(elementCommande.getProduit().getId()).orElse(null);
                if (produit != null) {
                    produit.setStock(produit.getStock() - elementCommande.getQuantite());
                    produitRepository.save(produit);
                }
            }

            return savedCommande;
        } else {
            // Si un produit n'est pas disponible, renvoyer un message d'erreur
            throw new IllegalArgumentException("un ou plusieurs produits ne sont pas disponibles en quantité suffisante.");
        }


    }
    public void annulerCommande(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        if (commande != null) {
            //Restaurer les stocks des produits
            for (ElementCommande elementCommande : commande.getElementscommande()) {
                Produit produit = produitRepository.findById(elementCommande.getProduit().getId
                        ()).orElse(null);
                if (produit != null) {
                    produit.setStock(produit.getStock() + elementCommande.getQuantite());
                    produitRepository.save(produit);
                }
            }
            // Supprimer la commande
            commandeRepository.deleteById(commandeId);
        }
    }

    public List<Commande> obtenirToutesLesCommandes() {
        return commandeRepository.findAll();
    }

    public Commande obtenirCommandeParId(Long id) {
        Commande commande = commandeRepository.findById(id).orElse(null);
        return commande;
    }
}









