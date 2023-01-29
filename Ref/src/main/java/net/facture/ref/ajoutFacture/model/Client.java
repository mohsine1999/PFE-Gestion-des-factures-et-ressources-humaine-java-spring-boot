package net.facture.ref.ajoutFacture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ice;
    private String nom_prenom;
    private String cin;
    private String adresse;
    @OneToMany(mappedBy = "client",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<FactureService> factureServices;
}
