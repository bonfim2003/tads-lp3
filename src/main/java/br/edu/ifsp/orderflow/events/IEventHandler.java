package br.edu.ifsp.orderflow.events;

/**
 Algo que irá consumir o tipo especifico de evento
 (uma class que implementa IDomainEvent)
 O parametro de tipo E garante, em TEMPO DE COMPILAÇÃO, que um handler
 de pagamento aprovado nunca receba um PagamentoRecusado**/
public interface IEventHandler <E extends IDomainEvent>{

    void handle(E event);

    /**
     Qual tipo de evento este handler trata?
     Necesário por causa do pagamento de tipo do Java(type erasure)
     */
    Class<E> eventType();
}
