package net.facture.ref.gestionFC.controller;

import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.ajoutFacture.repository.FactureClientRepository;
import net.facture.ref.gestionFC.service.FcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FcController {

    @Autowired
    private FactureClientRepository repo;

    @Autowired
    private FcService fcService;

    @GetMapping("/")
    public String home(){
        return "/gestionEmployee/HomePage/home" ;
    }

    @GetMapping("/RespoHome")
    public String employeHome(){
        return "/gestionEmployee/HomePage/RespoHome" ;
    }


    @GetMapping("/gererFC")
    public String viewHomePageFC(Model model,
                                 @RequestParam(name = "page",defaultValue = "0") int page,
                                 @RequestParam(name = "size",defaultValue = "15") int size,
                                 @RequestParam(name = "designation",defaultValue = "") String designation) {
        Page<FactureClient> pageFC = repo.findByDesignationContains(designation ,PageRequest.of(page, size));
        model.addAttribute("list_FC", pageFC.getContent());
        model.addAttribute("pages", new int[pageFC.getTotalPages()]);
        model.addAttribute("currentPage",page);

        return "gestionEmployee/gestionFC/indexFC";

    }

    @PostMapping("/saveFactureClientFC")
    public String saveFactureClientFC(@ModelAttribute("fcUpdate") FactureClient factureClientFC) {
        fcService.saveFactureClient(factureClientFC);
        return "redirect:/gererFC";
    }

    @GetMapping("/showFormForUpdateFC/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        FactureClient factureClient = fcService.getFactureFCById(id);
        model.addAttribute("fcUpdate", factureClient);
        return "/gestionEmployee/gestionFC/update_factureFC";
    }

    @GetMapping("/deleteFC/{id}")
    public String deleteFactureClient(@PathVariable(value="id") long id) {
        fcService.deleteFactureFCById(id);
        return "redirect:/gererFC";
    }

}