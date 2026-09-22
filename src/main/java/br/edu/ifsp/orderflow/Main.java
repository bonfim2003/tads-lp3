package br.edu.ifsp.orderflow;
import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.events.IEventBus;
import br.edu.ifsp.orderflow.events.PagamentoAprovado;
import br.edu.ifsp.orderflow.events.SimpleEventBus;
import br.edu.ifsp.orderflow.events.handlers.PagamentoAprovadoNotificacaoHandlers;
import br.edu.ifsp.orderflow.infra.ConsoleNotificacaoService;
import br.edu.ifsp.orderflow.infra.FakePagamentoGateway;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.infra.InMemoryPedidoRepository;
import br.edu.ifsp.orderflow.service.IEstoqueService;
import br.edu.ifsp.orderflow.service.INotificacaoService;
import br.edu.ifsp.orderflow.service.IPagamentoGateway;
import br.edu.ifsp.orderflow.service.IPedidoRepository;

import javax.xml.stream.FactoryConfigurationError;
import java.math.BigDecimal;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {


        IEstoqueService estoqueService = new InMemoryEstoqueService();
        IPedidoRepository pedidoRepository = new InMemoryPedidoRepository();
        INotificacaoService notificacaoService = new ConsoleNotificacaoService();
        IPagamentoGateway pagamentoGateway = new FakePagamentoGateway();
        IEventBus eventBus = new SimpleEventBus();



        PagamentoAprovadoNotificacaoHandlers evento1Handler = new PagamentoAprovadoNotificacaoHandlers(
                pedidoRepository,
                notificacaoService
        );

        PagamentoAprovado evento1 = new PagamentoAprovado(
                "pedido-'",
                "transacao-1",
                Instant.now()
        );
        eventBus.publish(evento1);
//
//        Produto mouse = new Produto(
//                "SKU-1",
//                "Mouse sem fio",
//                new BigDecimal("120.00")
//        );
//
//        Produto teclado = new Produto(
//                "SKU-2",
//                "Teclado Mecânico",
//                new BigDecimal("350.00")
//        );
//
//        Produto monitor = new Produto(
//                "SKU-3",
//                "Monitor 27 pol",
//                new BigDecimal("1800.00")
//        );
//
//        Cliente ana = new Cliente("Ana","ana@email.com");
//        Cliente bruno = new Cliente("Bruno","bruno@gmail.com");
//
//        Pedido pedido1 = new Pedido(ana);
//        pedido1.adiconarItens(new ItemPedido(mouse,2));
//        pedido1.adiconarItens(new ItemPedido(teclado,1));
//
//        Pedido pedido2 = new Pedido(bruno);
//        pedido2.adiconarItens(new ItemPedido(monitor,2));
//        pedido2.adiconarItens(new ItemPedido(teclado,5));
//
//        InMemoryEstoqueService estoque = new InMemoryEstoqueService();
//        estoque.adicionarEsqoque(mouse,1);
//        System.out.println(pedido2);
    }
}
