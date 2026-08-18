package br.edu.ifsp.orderflow.infra;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.service.IEstoqueService;

public class EstoqueService implements IEstoqueService {


    @Override
    public void adicionarEsqoque(Produto produto, int quantidade) {

    }

    @Override
    public int quantidadeDisponivel(Produto produto) {
        return 0;
    }

    @Override
    public boolean reservar(Pedido pedido) {
        return false;
    }

    @Override
    public void liberar(Pedido pedido) {

    }
}
