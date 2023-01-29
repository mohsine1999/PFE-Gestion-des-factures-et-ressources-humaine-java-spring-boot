package net.facture.ref.gestionEmployee.controller;

import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.gestionEmployee.model.Employee;
import net.facture.ref.gestionEmployee.repository.EmployeeRepository;
import net.facture.ref.gestionEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repoEmp;

    @Autowired
    private EmployeeService empService;

    @GetMapping("/EmployeHome")
    public String employeHome(){
        return "/gestionEmployee/HomePage/EmployeHome" ;
    }



    @GetMapping("/ajouterEmp")
    public String viewHomePage(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "15") int size,
                               @RequestParam(name = "email",defaultValue = "") String email) {
        Page<Employee> pageEmp = repoEmp.findByEmailContains(email , PageRequest.of(page, size));
        model.addAttribute("listEmployees", pageEmp.getContent());
        model.addAttribute("pages", new int[pageEmp.getTotalPages()]);
        model.addAttribute("currentPage",page);

        return "gestionEmployee/indexEmp";

    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeePage(Model model) {
        Employee empl = new Employee();
        model.addAttribute("Employee", empl);
        return "gestionEmployee/new_Employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("empl") Employee employee) {
        empService.saveEmployee(employee);
        return "redirect:/ajouterEmp";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") long id , Model model) {
        Employee employee=empService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "gestionEmployee/update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value="id") long id) {
        empService.deleteEmployeeById(id);
        return "redirect:/ajouterEmp";
    }
}