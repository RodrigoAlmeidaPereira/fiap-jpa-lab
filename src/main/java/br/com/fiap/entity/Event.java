package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"participantes"})
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @Column(name="DESCRICAO", length=45)
    private String descricao;

    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="DATA", length=45)
    private Date data;

    @Column(name="RESPONSAVEL", length=45)
    private String responsavel;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy="evento")
    private Set<Guest> participantes = new HashSet<>();
}
