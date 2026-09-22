package br.edu.ifsp.orderflow.events.handlers;

import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.events.IEventHandler;
import br.edu.ifsp.orderflow.events.PagamentoAprovado;
import br.edu.ifsp.orderflow.service.INotificacaoService;
import br.edu.ifsp.orderflow.service.IPedidoRepository;

import java.util.Optional;

/**
 * Precisa de um handler para cada evento pois ele implementa um tipo especifico
 */
public class PagamentoAprovadoNotificacaoHandlers implements IEventHandler <PagamentoAprovado> {

    private final IPedidoRepository pedidoRepository;
    private final INotificacaoService notificacaoService;

    public PagamentoAprovadoNotificacaoHandlers(
        IPedidoRepository pedidoRepository,
        INotificacaoService notificacaoService
    ){
        this.notificacaoService = notificacaoService;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void handle(PagamentoAprovado event) {
        /*
        O Optional verifica se está vazio
         */
        Optional<Pedido> pedidoEncontrado = this.pedidoRepository.findById(event.pedidoId());

        if (pedidoEncontrado.isPresent()){
            Pedido pedido = pedidoEncontrado.get();
            this.notificacaoService.notificar(
                    pedido.getCliente(),
                    "Pagamento aprovado! Pedido " + pedido.getIdCurto()
                    + " confirmado (transação "+ event.transacaoId()+ ")"
            );
        }

    }

    @Override
    public Class<PagamentoAprovado> eventType() {
        /*
        o .class retorna o tipo do evento do handler 'pagamentoAprovado'
         */
        return PagamentoAprovado.class;
    }
}
