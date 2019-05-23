package br.com.fiap;

import br.com.fiap.entity.*;
import br.com.fiap.helper.EventHelper;
import br.com.fiap.helper.Helper;
import br.com.fiap.helper.VendasHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.Date;
import java.util.List;

public class Lab {

    public static void main(String[] args) {
//        runBasicExample();
//        runCompositeKey();
        runManyToMany();
    }

    public static void runManyToMany() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        incluirFuncionario(em);
        listarFuncionarios(em);
        buscarFuncionario(em, "2000");
    }

    public static void runBasicExample() {
        incluirEvento();
        listarEventos();
        listarParticipantes(1);
    }

    public static void runCompositeKey() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();

        VendasHelper dao = new VendasHelper(em);

        try {
            //Definindo o cliente
            Cliente cliente = new Cliente();
            cliente.setId(10);
            cliente.setEmpresa("Fiap");

            //Definindo o Endereco
            Endereco endereco = new Endereco();
            endereco.setRua("Lins de Vasconcelos");
            endereco.setCidade("São Paulo");
            endereco.setCep("01538-001");
            endereco.setCliente(cliente);

            //Defnindo o pedido
            PedidosPK pkpedido = new PedidosPK();
            pkpedido.setCodigo(100);
            pkpedido.setCategoria("Livros");

            Pedido pedido = new Pedido();
            pedido.setDataPedido(new Date());
            pedido.setPedidoPK(pkpedido);
            pedido.setCliente(cliente);

            //Definindo dois itens
            Item item1 = new Item();
            item1.setQuantidade(2);

            Item item2 = new Item();
            item2.setQuantidade(3);

            //Fazendo as associações
            pedido.getItens().add(item1);
            pedido.getItens().add(item2);

            cliente.getEnderecos().add(endereco);
            cliente.getPedidos().add(pedido);

            dao.salvar(cliente);
            JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void incluirFuncionario(EntityManager em){
        Helper dao = new Helper(em);
        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula("2000");
        funcionario.setNome("Alberto Santos");

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Teste Unitário");
        tarefa.setDuracao(100);
        tarefa.getFuncionarios().add(funcionario);

        funcionario.getTarefas().add(tarefa);

        try {
            dao.salvar(funcionario);
            System.out.println("Funcionario OK");
        } catch (Exception e) {
            System.out.println("ERRO ===>> " + e.getMessage());
        }
    }
    private static void listarFuncionarios(EntityManager em){
        Helper dao = new Helper(em);
        List<Funcionario> funcionarios = dao.listarTodos();
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getMatricula() + ": " +
                    funcionario.getNome());
        }
//        em.close();
    }

    private static void incluirEvento() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        EventHelper helper = new EventHelper(em);

        Event evento = new Event();
        evento.setDescricao("Novo curso disponivel");
        evento.setResponsavel("Juvenal Santos");
        evento.setData(new Date());

        Guest p1 = new Guest();
        p1.setNome("Jose Antonio");
        p1.setEmail("jantonio@fap.com.br");
        p1.setEvento(evento);

        Guest p2 = new Guest();
        p2.setNome("Camila");
        p2.setEmail("camila@fap.com.br");
        p2.setEvento(evento);

        Guest p3 = new Guest();
        p3.setNome("Bonifacio");
        p3.setEmail("bonifacio@fap.com.br");
        p3.setEvento(evento);

        evento.getParticipantes().add(p1);
        evento.getParticipantes().add(p2);
        evento.getParticipantes().add(p3);

        System.out.println(helper.salvar(evento));
    }

    private static void buscarFuncionario(EntityManager em, String matricula){
        Helper dao = new Helper(em);
        Funcionario f = dao.buscarFuncionario(matricula);
        System.out.println(f.getMatricula() + ": " + f.getNome());
    }

    private static void listarEventos() {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        EventHelper helper = new EventHelper(em);

        for (Event evento : helper.listarEventos()) {
            System.out.println("Id: " + evento.getId());
            System.out.println("Descrição: " + evento.getDescricao());
            System.out.println("Responsável: " + evento.getResponsavel());
            System.out.println("-------------------------------------");
        }
    }

    private static void listarParticipantes(int idEvento) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaPU");
        EntityManager em = emf.createEntityManager();
        EventHelper helper = new EventHelper(em);

        for (Guest participante : helper.listarParticipantes(idEvento)) {
            System.out.println("Id: " + participante.getId());
            System.out.println("Nome: " + participante.getNome());
            System.out.println("Email: " + participante.getEmail());
            System.out.println("-------------------------------------");
        }
    }
}
