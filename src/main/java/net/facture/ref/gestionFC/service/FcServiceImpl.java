package net.facture.ref.gestionFC.service;

import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.ajoutFacture.repository.FactureClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class FcServiceImpl implements FcService{
 private EntityManager entityManager;


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
    public FactureClient getFactureFCById(long id) {
        Optional<FactureClient> opt = repo.findById(id);
        FactureClient factureClient = null;
        if (opt.isPresent()) {
            factureClient = opt.get();
        } else {
            throw new RuntimeException("Facture Client not found for id :: " + id);
        }
        return factureClient;
    }



    @Override
    public void deleteFactureFCById(long id) {
    repo.deleteById(id);
    }


    @Override
    public List<FactureClient> rechercher(String num_facture, String designation) {
        return entityManager.createQuery("select u from FactureClient u where u.num_factureFC = :num_facture or u.designation = :designation", FactureClient.class)
                .setParameter("num_facture", num_facture)
                .setParameter("designation", designation)
                .getResultList();
    }
}

