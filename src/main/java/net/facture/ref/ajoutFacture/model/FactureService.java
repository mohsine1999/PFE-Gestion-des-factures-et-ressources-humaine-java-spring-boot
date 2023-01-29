package net.facture.ref.ajoutFacture.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Data @ToString
public class FactureService {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String num_factureFS;
    private String designationFS;
    private int quantitee;
    private float prix_ht;
    private float total_ht;
    private float total_ttc;
    private float taxe;
    private String type_de_paiement;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="client_id", nullable = true)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Client client;



}
