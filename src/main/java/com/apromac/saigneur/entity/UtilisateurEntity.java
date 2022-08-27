package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "utilisateur")
public class UtilisateurEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur", updatable = false, nullable = false)
    private Long utilisateurID;

    @Column(name = "nom_utilisateur", nullable = false)
    private String nomUtilisateur;

    @Column(name = "prenoms_utilisateur", nullable = false)
    private String prenomsUtilisateur;

}
