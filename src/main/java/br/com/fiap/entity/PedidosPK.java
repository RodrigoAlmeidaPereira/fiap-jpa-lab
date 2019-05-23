package br.com.fiap.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PedidosPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="NUMPEDIDO")
    private int codigo;

    @Column(name="CATEGORIA")
    private String categoria;

    @Override
    public boolean equals(Object o){
        if(o instanceof PedidosPK){
            PedidosPK pk = (PedidosPK)o;
            if(this.getCodigo() != pk.getCodigo()){
                return false;
            }
            if(!this.getCategoria().equals(pk.getCategoria())){
                return false;
            }
            return true;
        }
        return false;
    }
}
