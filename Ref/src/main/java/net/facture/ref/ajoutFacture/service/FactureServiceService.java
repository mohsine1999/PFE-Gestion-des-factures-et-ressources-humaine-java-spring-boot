package net.facture.ref.ajoutFacture.service;

import net.facture.ref.ajoutFacture.model.DataPojo;
import net.facture.ref.ajoutFacture.model.FactureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FactureServiceService {

    Page<FactureService> getClientFactureFS(Long clientId  , Pageable pageable);

    public void saveFactureService(DataPojo dataPojo);

    float gainFS();

    float taxeFS();

}
