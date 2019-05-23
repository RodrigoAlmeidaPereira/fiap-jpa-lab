package br.com.fiap.helper;

import br.com.fiap.entity.Event;
import br.com.fiap.entity.Guest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventHelper {

    private EntityManager em;

    public EventHelper(EntityManager em) {
        this.em = em;
    }

    public String salvar(Event evento) {
        try {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            return "Evento incluído com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String adicionarParticipante(int idEvento, Guest participante) {
        try {
            Event evento = em.find(Event.class, idEvento);
            participante.setEvento(evento);
            evento.getParticipantes().add(participante);
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
            return "Evento atualizado com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<Event> listarEventos(){
        TypedQuery<Event> query = em.createQuery("Select e from Event e",
                Event.class);
        return query.getResultList();
    }
    public List<Guest> listarParticipantes(int idEvento){
        TypedQuery<Guest> query = em.createQuery(
                "Select g from Guest g Where g.evento.id = :idevento", Guest.class);
        query.setParameter("idevento", idEvento);
        return query.getResultList();
    }

}
