package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name="clientes")
@EqualsAndHashCode(exclude = {"pedidos", "enderecos"})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="ID")
    private int id;

    @Column(name="EMPRESA", length=45)
    private String empresa;
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,
            mappedBy="cliente")
    private Set<Pedido> pedidos = new LinkedHashSet<Pedido>();

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY,
            mappedBy="cliente")
    private Set<Endereco> enderecos = new LinkedHashSet<>();
}
