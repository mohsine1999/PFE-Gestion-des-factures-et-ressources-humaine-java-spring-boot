package net.facture.ref.ajoutFacture.controller;

import net.facture.ref.ajoutFacture.model.DataPojo;
import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.ajoutFacture.model.FactureService;
import net.facture.ref.ajoutFacture.repository.FactureServiceRepository;
import net.facture.ref.ajoutFacture.service.FactureClientService;
import net.facture.ref.ajoutFacture.service.FactureServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class factureController {


    @Autowired
    private FactureServiceService factureServiceService;

    @Autowired
    private FactureClientService factureClientService;

    @GetMapping("/ajouterFC")
    public String viewFC(Model model){
        model.addAttribute("listFC", factureClientService.getAllFC());
        return"gestionEmployee/facture/index_factureClient";
    }


    @GetMapping("/ajoutFactureClient")
    public String showFactureClient(Model model){
        FactureClient factureClient = new FactureClient();
        model.addAttribute("factureClient", factureClient);
        return "gestionEmployee/facture/new_factureClient";
    }

    @PostMapping("saveFactureClient")
    public String saveFactureClient(@ModelAttribute("factureClient") FactureClient fc){
        factureClientService.saveFactureClient(fc);
        return "redirect:/ajouterFC";
    }

    @GetMapping("ajouterFS/{clientId}")
    public String viewFS(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page ,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                         @PathVariable Long clientId
                         ){

        Pageable pageable = PageRequest.of(page , size);
        Page<FactureService> pageFS =  factureServiceService.getClientFactureFS(clientId , pageable);
        model.addAttribute("factureFS", pageFS.getContent());
        model.addAttribute("pages", new int[pageFS.getTotalPages()]);
        model.addAttribute("currentpage", page);
        return"gestionEmployee/gestionFS/gestionFactureFS/index_factureFS";
    }


    @GetMapping("NewFactureFS/{id}")
    public String newFactureFS(Model model, @PathVariable(value="id") Long id){
        FactureService factureService = new FactureService();
        model.addAttribute("factureService", factureService);

        DataPojo dataPojo = new DataPojo();
        dataPojo.setIdC(id);

        model.addAttribute("dataPojo" , dataPojo);

        return "gestionEmployee/gestionFS/gestionFactureFS/new_factureService";
    }

    @PostMapping("saveFactureService")
    public String saveFactureService(HttpServletRequest request,
                                     Model model , @ModelAttribute("dataPojo") DataPojo dataPojo){

        factureServiceService.saveFactureService(dataPojo);

        return "redirect:/ajouterFS/"+dataPojo.getIdC();
    }

    @GetMapping("/gain")
    public String gain(Model model){

       float cc=  factureServiceService.gainFS();
        model.addAttribute("sommeFS",cc);

        float bb= factureClientService.gainFC();
        model.addAttribute("sommeFC",bb);

        float taxeFS=  factureServiceService.taxeFS();
        model.addAttribute("taxeFS",taxeFS);

        float taxeFC= factureClientService.taxeFC();
        model.addAttribute("taxeFC",taxeFC);

        return "/gestionEmployee/les gains/gain";
    }


}
