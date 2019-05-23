package br.com.fiap.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"tarefas"})
@NamedQuery(name="Funcionario.findAll", query="select f from Funcionario f")
@Table(name="FUNCIONARIO", catalog="fiap", uniqueConstraints = {@UniqueConstraint(columnNames="CODIGO_FUNCIONARIO")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false)
    private Integer id;

    @Column(name="CODIGO_FUNCIONARIO", unique=true, nullable=false, length=10)
    private String matricula;

    @Column(name="NOME_FUNCIONARIO", unique=true, nullable=false, length=45)
    private String nome;

    @ManyToMany(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinTable(name="FUNCIONARIO_TAREFA", catalog="fiap", joinColumns =
            {@JoinColumn(name="FUNCIONARIO_ID", nullable=false, updatable=false)},
            inverseJoinColumns = {@JoinColumn(name="TAREFA_ID", nullable=false,
                    updatable=false)})
    private Set<Tarefa> tarefas = new HashSet<>();
}
