package br.com.fiap.entity;

import br.com.fiap.entity.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(exclude = {"evento"})
public class Guest {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDEVENTO")
    private Event evento;

    @Column(name="NOME")
    private String nome;

    @Column(name="EMAIL")
    private String email;
}
