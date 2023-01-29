package net.facture.ref.Gestionclient.controller;

import net.facture.ref.Gestionclient.service.ClientService;
import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.ajoutFacture.repository.ClientRepository;
import net.facture.ref.ajoutFacture.repository.FactureClientRepository;
import net.facture.ref.gestionEmployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository repoCl;

    @Autowired
    private ClientService clientService;

    @GetMapping("/gererClient")
    public String viewHomePageClient(Model model,
                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                     @RequestParam(name = "size",defaultValue = "15") int size,
                                     @RequestParam(name = "ice",defaultValue = "") String ice) {
        Page<Client> pageCl = repoCl.findByIceContains(ice , PageRequest.of(page, size));
        model.addAttribute("list_CL", pageCl.getContent());
        model.addAttribute("pages", new int[pageCl.getTotalPages()]);
        model.addAttribute("currentPage",page);

        return "gestionEmployee/gestionFS/gestionClient/indexClient";
    }

    @PostMapping("/saveClientFS")
    public String saveClientFS(@ModelAttribute("clientUpdate") Client client) {
        clientService.saveClient(client);
        return "redirect:/gererClient";
    }

    @GetMapping("/showNewClientForm")
    public String showNewClientPage(Model model) {
        Client client = new Client();
        model.addAttribute("ClientN", client);
        return "gestionEmployee/gestionFS/gestionClient/new_client";
    }


    @GetMapping("/showFormForUpdateClient/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "gestionEmployee/gestionFS/gestionClient/update_client";
    }

    @GetMapping("/deleteClient/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        clientService.deleteClientById(id);
        return "redirect:/gererClient";
    }
}