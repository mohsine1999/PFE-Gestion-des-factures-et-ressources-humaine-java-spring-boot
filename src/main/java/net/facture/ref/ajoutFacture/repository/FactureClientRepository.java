package net.facture.ref.ajoutFacture.repository;

import net.facture.ref.ajoutFacture.model.FactureClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FactureClientRepository extends JpaRepository <FactureClient,Long>{

    Page<FactureClient> findByDesignationContains(@Param("designation") String designation, Pageable pageable);

    @Query(value = "select sum(fc.total_ttc ) from facture_client  fc;" ,nativeQuery = true)
    float CalculerFC();

    @Query(value = "select AVG(fc.taxe ) from facture_client  fc;" ,nativeQuery = true)
    float CalculerTaxeFC();


}
