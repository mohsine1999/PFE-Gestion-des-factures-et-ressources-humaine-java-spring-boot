package net.facture.ref.gestionEmployee.service;


import net.facture.ref.gestionEmployee.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

}