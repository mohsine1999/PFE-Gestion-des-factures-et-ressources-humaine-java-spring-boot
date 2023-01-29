package net.facture.ref.ajoutFacture.service;

import lombok.AllArgsConstructor;
import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.model.DataPojo;
import net.facture.ref.ajoutFacture.model.FactureService;
import net.facture.ref.ajoutFacture.repository.ClientRepository;
import net.facture.ref.ajoutFacture.repository.FactureServiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class FactureServiceServiceImpl implements FactureServiceService{

    private final FactureServiceRepository factureServiceRepository;

    private final ClientRepository clientRepository;

    @Override
    public Page<FactureService> getClientFactureFS(Long clientId  , Pageable pageable) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client Not Found"));


        return factureServiceRepository.findByClient(client , pageable);
    }

    @Override
    public void saveFactureService(DataPojo dataPojo) {
//important
        Client client  = clientRepository.findById(dataPojo.getIdC())
                        .orElseThrow(() -> new RuntimeException("Client Not Found"));

        FactureService factureService = new FactureService();

        BeanUtils.copyProperties(dataPojo ,factureService);

        factureService.setClient(client);

        clientRepository.save(client);

        factureServiceRepository.save(factureService);
    }

    @Override
    public float gainFS(){
       return factureServiceRepository.CalculerFS();
    }

    @Override
    public float taxeFS() {
        return factureServiceRepository.CalculerTqxeFS();
    }
}
