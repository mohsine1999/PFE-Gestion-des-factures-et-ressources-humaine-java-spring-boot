package net.facture.ref.ajoutFacture.service;

import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.ajoutFacture.repository.FactureClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureClientServiceImpl implements FactureClientService{

    @Autowired
    private FactureClientRepository repo;

    @Override
    public List<FactureClient> getAllFC() {
        return repo.findAll();
    }

    @Override
    public void saveFactureClient(FactureClient factureClient) {
        repo.save(factureClient);
    }

    @Override
    public float gainFC() {
        return repo.CalculerFC();
    }

    @Override
    public float taxeFC() {
        return repo.CalculerTaxeFC();
    }
}
