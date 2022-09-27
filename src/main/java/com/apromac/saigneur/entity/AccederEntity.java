package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "acceder")
public class AccederEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acceder", updatable = false, nullable = false)
    private Long accederID;

    @ManyToOne
    @JoinColumn(name = "code_menu", nullable = false)
    private MenuEntity menu;

    @ManyToOne
    @JoinColumn(name = "code_profil", nullable = false)
    private ProfilEntity profil;

}
