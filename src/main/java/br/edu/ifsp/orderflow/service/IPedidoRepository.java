package br.edu.ifsp.orderflow.service;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface InMemoryPedidoRepository {

    void (Pedido pedido);

    Optional<Pedido> (String id);

    List<Pedido> ();
}