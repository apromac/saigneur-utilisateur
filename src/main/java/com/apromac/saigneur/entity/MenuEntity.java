package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "menu")
public class MenuEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu", updatable = false, nullable = false)
    private Long menuID;

    @Column(name = "lib_menu", nullable = true)
    private String libelleMenu;

    @Column(name = "has_smenu", nullable = true)
    private Boolean hasSousMenu;

    @Column(name = "url_menu", nullable = true)
    private String urlMenu;

    @Column(name = "parentid", nullable = true)
    private Integer parentIdMenu;

    @Column(name = "icon_menu", nullable = true)
    private String iconMenu;

    @Column(name = "num_ordre", nullable = true)
    private String numeroOrdreMenu;

}
