package net.facture.ref.ajoutFacture.service;


import net.facture.ref.ajoutFacture.model.FactureClient;

import java.util.List;

public interface FactureClientService {

    List<FactureClient> getAllFC();

    public void saveFactureClient(FactureClient factureClient);

    float gainFC();

    float taxeFC();

}
