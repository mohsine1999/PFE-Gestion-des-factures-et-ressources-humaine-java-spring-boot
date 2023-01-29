package net.facture.ref.ajoutFacture.repository;


import net.facture.ref.ajoutFacture.model.Client;
import net.facture.ref.ajoutFacture.model.FactureService;
import org.hibernate.annotations.Subselect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface FactureServiceRepository extends JpaRepository<FactureService,Long> {

    @PersistenceContext
    EntityManagerFactory emf = null;

    Page<FactureService> findByClient(Client client, Pageable pageable);

    Page<FactureService> findByDesignationFSContains(@Param("designationFS") String designationFS, Pageable pageable);


    @Query(value = "select sum(fs.total_ttc ) from facture_service  fs;" ,nativeQuery = true)
    float CalculerFS();

    @Query(value = "select AVG(fs.taxe ) from facture_service  fs;" ,nativeQuery = true)
    float CalculerTqxeFS();




}
