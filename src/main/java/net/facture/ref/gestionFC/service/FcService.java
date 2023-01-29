package net.facture.ref.gestionFC.service;
import net.facture.ref.ajoutFacture.model.FactureClient;

import net.facture.ref.gestionEmployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FcService  {
    List<FactureClient> getAllFC();

    void saveFactureClient(FactureClient factureClient);
    FactureClient getFactureFCById(long id);


    void deleteFactureFCById(long id);

    List<FactureClient> rechercher(String num_facture,String designation);
}
