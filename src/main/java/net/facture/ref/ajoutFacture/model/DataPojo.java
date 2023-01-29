package net.facture.ref.ajoutFacture.model;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DataPojo {
    private Long idC;
    private String num_factureFS;
    private String designationFS;
    private int quantitee;
    private float prix_ht;
    private  float total_ht;
    private  float total_ttc;
    private float taxe;
    private  String type_de_paiement;

    
}
