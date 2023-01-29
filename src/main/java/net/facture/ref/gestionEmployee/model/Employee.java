package net.facture.ref.gestionEmployee.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data

public class Employee {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_utilisateur;
    private String mot_de_passe;
    private String email;
    private String numero_tele;
    private  String roleEmp;

 public Employee() {
 }
}
