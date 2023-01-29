package net.facture.ref.gestionFS.service;

import net.facture.ref.ajoutFacture.model.FactureService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.util.List;

public interface FsService {
    List<FactureService> getAllFS();


    void saveFactureFS(FactureService factureService);
    FactureService getFactureFSById(Long id);

    void deleteFactureFSById(long id);

    JasperPrint generateReport(Long fsId) throws IOException, JRException;

}
