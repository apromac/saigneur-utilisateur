package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "occuper")
public class OccuperEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_occuper", updatable = false, nullable = false)
    private Long occuperID;

    @Column(name = "lib_occuper", nullable = false)
    private String libelleOccuper;

    @Column(name = "date_occuper", nullable = false)
    private Date dateOccuper;

    @Column(name = "actif_occuper", nullable = false)
    private Boolean isOccuper;

    @ManyToOne
    @JoinColumn(name = "code_poste", nullable = false)
    private PosteEntity poste;

    @ManyToOne
    @JoinColumn(name = "code_utilisateur", nullable = false)
    private UtilisateurEntity utilisateur;

}
