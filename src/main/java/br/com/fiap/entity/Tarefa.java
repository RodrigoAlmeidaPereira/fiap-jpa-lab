package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"funcionarios"})
@Table(name="TAREFA", catalog="fiap")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    private Integer id;

    @Column(name = "DESCRICAO", nullable = false, length=45)
    private String descricao;

    @Column(name = "DURACAO", nullable = false)
    private Integer duracao;

    @ManyToMany(fetch=FetchType.LAZY, mappedBy="tarefas")
    private Set<Funcionario> funcionarios = new HashSet<>();

}
