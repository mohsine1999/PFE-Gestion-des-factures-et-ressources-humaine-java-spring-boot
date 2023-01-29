package net.facture.ref.ajoutFacture.repository;

import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.model.FactureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Page<Client> findByIceContains(@Param("ice") String ice, Pageable pageable);

    List<Client> findByFactureServices(FactureService factureService);




}
