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

    @Column(name = "prenoms_utilisateur", nullable = true)
    private String prenomsUtilisateur;

    @Column(name = "username_utilisateur", nullable = false)
    private String username;

    @Column(name = "password_utilisateur", nullable = false)
    private String password;

    @Column(name = "photo_utilisateur", nullable = true)
    private String photoUtilisateur;

    @ManyToOne
    @JoinColumn(name = "code_profil", nullable = false)
    private ProfilEntity profil;

}
