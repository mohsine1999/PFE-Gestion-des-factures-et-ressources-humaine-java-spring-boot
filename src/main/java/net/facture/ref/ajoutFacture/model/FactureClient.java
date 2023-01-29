package net.facture.ref.ajoutFacture.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FactureClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String num_factureFC;
    private String designation;
    private int quantitee;
    private float prix_ht;
    private float total_ht;
    private float total_ttc;
    private float taxe;
}
