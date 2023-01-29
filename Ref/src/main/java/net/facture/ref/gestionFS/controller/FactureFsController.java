package net.facture.ref.gestionFS.controller;


import net.facture.ref.ajoutFacture.model.FactureService;
import net.facture.ref.ajoutFacture.repository.FactureServiceRepository;
import net.facture.ref.gestionFS.service.FsServiceImpl;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;


@Controller
public class FactureFsController {

    @Autowired
    private FactureServiceRepository factureServiceRepository;

    @Autowired
    private FsServiceImpl fsService;

    @GetMapping("/gererFS")
    public String viewHomePageFS(Model model,
                                 @RequestParam(name = "page",defaultValue = "0") int page,
                                 @RequestParam(name = "size",defaultValue = "15") int size,
                                 @RequestParam(name = "designationFS",defaultValue = "") String designationFS) {
        Page<FactureService> pageFS = factureServiceRepository.findByDesignationFSContains(designationFS , PageRequest.of(page, size));
        model.addAttribute("liste_FS", pageFS.getContent());
        model.addAttribute("pages", new int[pageFS.getTotalPages()]);
        model.addAttribute("currentPage",page);

        return "gestionEmployee/gestionFS/gestionFactureFS/FsResponsable/indexFSRespo";
    }

    @GetMapping("/showFormForUpdateFS/{id}")
    public String showFormForUpdateFS(@PathVariable(value = "id") long id, Model model) {
        FactureService factureService = fsService.getFactureFSById(id);
        model.addAttribute("FSUpdate", factureService);
        return "/gestionEmployee/gestionFS/gestionFactureFS/FsResponsable/update_FS";
    }

    @PostMapping("/saveFactureFS")
    public String saveFactureClientFS(@ModelAttribute("FSUpdate") FactureService factureServiceFS) {
        fsService.saveFactureFS(factureServiceFS);
        return "redirect:/gererFS";
    }

    @GetMapping("/deleteFS/{id}")
    public String deleteFactureFS(@PathVariable(value="id") long id) {
        fsService.deleteFactureFSById(id);
        return "redirect:/gererFS";
    }

    //----------------------------------------
    @GetMapping(value = "/generateReport/{facId}")
    public void generateReport(@PathVariable(value = "facId") Long facId, HttpServletResponse response)
            throws IOException, JRException {

        if (facId == null) {
            return;
        }

        JasperPrint jasperPrint = fsService.generateReport(facId);

        if (jasperPrint == null) {
            return;
        }
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename =facture"+facId+".pdf");

        OutputStream outputStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}
