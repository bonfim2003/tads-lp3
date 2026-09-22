package br.edu.ifsp.orderflow.events;

import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEventBus implements IEventBus{

    private Map<
            /* O Class para aceitar qualquer tipo de generico, desde que estaja no domain (visto que cada etapa tem um tipo)*/
            /* O List para aceitar qualquer subtipo do Domain*/
            Class<? extends IDomainEvent>,
            List<IEventHandler<? extends IDomainEvent>>
            > handlers = new HashMap<>();

    @Override
    public <E extends IDomainEvent> void publish(E event) {

        /*Aqui publicamos um evento que não sabemos qual a classe, o getclass traz isso */
        List<IEventHandler<? extends IDomainEvent>
                > listhandlers = this.handlers.get(event.getClass()
        );

        if(listhandlers == null){
            return;
        }
        /*Valida se o tipo é igual ao passado no metodo (e será como foi configurado no register), então para cada elemento crie uma varivel do tipo handler */
        for(IEventHandler<? extends IDomainEvent> registered : listhandlers){
            IEventHandler<E> handler = (IEventHandler<E>) registered;
            handler.handle(event);
        }

    }

    @Override
    public <E extends IDomainEvent> void register(IEventHandler<E> handler) {

        List<IEventHandler<? extends IDomainEvent>> listHandlers = this.handlers.get(handler.eventType());

        if(listHandlers == null){
            listHandlers = new ArrayList<>();
            this.handlers.put(handler.eventType(),listHandlers);
        }

        listHandlers.add(handler);
    }
}
