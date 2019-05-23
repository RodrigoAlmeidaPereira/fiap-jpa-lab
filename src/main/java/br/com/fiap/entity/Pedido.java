package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name="pedidos")
@EqualsAndHashCode(exclude = {"cliente", "itens"})
public class Pedido {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PedidosPK pedidoPK;
    @Temporal(value=TemporalType.TIMESTAMP)
    @Column(name="DATAPEDIDO")
    private Date dataPedido;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDCLIENTE")
    private Cliente cliente;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="pedido")
    private Set<Item> itens = new LinkedHashSet<>();

}
