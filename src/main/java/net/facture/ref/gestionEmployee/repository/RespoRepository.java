package net.facture.ref.gestionEmployee.repository;


import net.facture.ref.gestionEmployee.model.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespoRepository extends JpaRepository<Responsable, Long> {
    Responsable findByUsername(String username);
}
