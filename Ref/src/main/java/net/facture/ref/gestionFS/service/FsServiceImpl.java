package net.facture.ref.gestionFS.service;

import net.facture.ref.Exception.ResourceNotFoundException;
import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.model.FactureService;
import net.facture.ref.ajoutFacture.repository.ClientRepository;
import net.facture.ref.ajoutFacture.repository.FactureServiceRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FsServiceImpl implements FsService{
   public  static final String RESOURCE="static/facture.jrxml";
    @Autowired
    private FactureServiceRepository repo;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<FactureService> getAllFS() {
        return repo.findAll();
    }

    @Override
    public void saveFactureFS(FactureService factureService) {
        repo.save(factureService);
    }

    @Override
    public FactureService getFactureFSById(Long id) {
        Optional<FactureService> opt = repo.findById(id);
        FactureService factureService = null;
        if (opt.isPresent()) {
            factureService = opt.get();
        } else {
            throw new RuntimeException("Facture Client not found for id :: " + id);
        }
        return factureService;
    }

    @Override
    public void deleteFactureFSById(long id) {
        repo.deleteById(id);
    }



    @Override
    public JasperPrint generateReport(Long fsId) throws IOException, JRException {

        InputStream resource=new ClassPathResource(RESOURCE).getInputStream();

        JasperReport jasperReport = JasperCompileManager.compileReport(resource);

        FactureService factureService=repo.findById(fsId)
                .orElseThrow(() -> new ResourceNotFoundException("facture not found"));

        List FS = new ArrayList();

        FS.add(factureService);


        List<Client> client =clientRepository.findByFactureServices(factureService);



        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(FS);

        Map<String,Object> parameters=new HashMap<>();

        parameters.put("dataSource",dataSource);
        parameters.put("idFS",factureService.getNum_factureFS());
        parameters.put("paiement",factureService.getType_de_paiement());
        parameters.put("nom",factureService.getClient().getNom_prenom());
        parameters.put("adresse",factureService.getClient().getAdresse());
        parameters.put("ice",factureService.getClient().getIce());

      parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);


        return JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());
    }
}
