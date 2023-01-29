package net.facture.ref.gestionEmployee.repository;

import net.facture.ref.ajoutFacture.model.FactureClient;
import net.facture.ref.gestionEmployee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByEmailContains(@Param("email") String email, Pageable pageable);
    Employee findByEmail(String email);
}