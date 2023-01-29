package net.facture.ref.Gestionclient.service;

import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientRepository cl;

    @Override
    public List<Client> getAllClient() {
        return cl.findAll();
    }

    @Override
    public void saveClient(Client client) {
       cl.save(client);
    }

    @Override
    public Client getClientById(long id) {
        Optional<Client> opt =cl.findById(id);
        Client client =null;
        if (opt.isPresent()) {
            client = opt.get();
        }else {
            throw new RuntimeException(" Client not found for id :: " + id);
        }
        return client;
    }

    @Override
    public void deleteClientById(long id) {
       cl.deleteById(id);
    }
}
