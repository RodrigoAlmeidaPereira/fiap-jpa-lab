package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name="itens")
@EqualsAndHashCode(exclude = "pedido")
public class Item {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="QUANTIDADE")
    private double quantidade;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns({@JoinColumn(name="NUMPEDIDO",referencedColumnName="NUMPEDIDO"),
            @JoinColumn(name="CATEGORIA",referencedColumnName="CATEGORIA")})
    private Pedido pedido;

}
