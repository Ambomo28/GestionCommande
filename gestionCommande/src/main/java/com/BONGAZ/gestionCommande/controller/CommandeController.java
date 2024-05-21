package com.BONGAZ.gestionCommande.controller;

import com.BONGAZ.gestionCommande.Models.Commande;
import com.BONGAZ.gestionCommande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommandeController {
    @Autowired
    private CommandeService commandeService;


    @PostMapping("/creerCommande")
    public ResponseEntity<Commande> creerCommande(@RequestBody Commande commande) {
        try {
            Commande nouvelleCommande = commandeService.creerCommande(commande);
            return  ResponseEntity.status(HttpStatus.CREATED).body(nouvelleCommande);
        } catch (Exception e) {
            // Gérez l'exception appropriée ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/obtenirToutesLesCommandes")
    public ResponseEntity<List<Commande>> obtenirToutesLesCommandes() {
        try {
            List<Commande> commandes = commandeService.obtenirToutesLesCommandes();
            return ResponseEntity.ok(commandes);
        } catch (Exception e) {
            // Gérez l'exception appropriée ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/obtenirCommandeParId/{id}")
    public ResponseEntity<Commande> obtenirCommandeParId(@PathVariable Long id) {
       try {
           Commande commande = commandeService.obtenirCommandeParId(id);
           if (commande != null) {
               return ResponseEntity.ok(commande);
           } else {
               return ResponseEntity.notFound().build();
           }
       } catch (Exception e) {
           // Gérez l'exception appropriée ici
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @DeleteMapping("/annulerCommande/{commandeId}")
    public ResponseEntity<Void> annulerCommande(@PathVariable Long commandeId) {
        try {
            commandeService.annulerCommande(commandeId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Gérez l'exception appropriée ici
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
}
