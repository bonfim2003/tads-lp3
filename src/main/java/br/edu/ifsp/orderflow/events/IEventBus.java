package br.edu.ifsp.orderflow.events;

/**
 * Em IEventBus (ou barramento de evento) funciona como um quadro de avisos,
 * onde serviçoes publicam eventos e os handlres registrados são avisados.
 */
public interface IEventBus {

    <E extends IDomainEvent> void publish(E event);

     <E extends IDomainEvent> void register(IEventHandler<E> handler);
}

