package br.edu.ifsp.orderflow.service;

public class PedidoService {

    private IEstoqueService estoqueService;
    private final IPedidoRepository pedidoRepository;

    public PedidoService(
        IEstoqueService estoqueService,
        IPedidoRepository pedidoRepository
    ){
        this.estoqueService = estoqueService;
        this.pedidoRepository = pedidoRepository;
    }

    puvblic Pedido processar(Pedido pedido){

        boolean foiReservado = this.estoqueService.reservar(pedido);

        if(!foiReservado){
            pedido.cancelar
        }
    }
}
