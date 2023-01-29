package net.facture.ref.Gestionclient.service;

import net.facture.ref.ajoutFacture.model.Client;



import java.util.List;

public interface ClientService {
    List<Client> getAllClient();

    void saveClient(Client client);

    Client getClientById(long id);

    void deleteClientById(long id);

}
