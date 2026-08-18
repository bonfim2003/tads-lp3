package br.edu.ifsp.orderflow.infra;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEstoqueService implements IEstoqueService {

    private final Map<String, Integer> estoque = new HashMap<>(); //Estrutura de dados que armazena por meio de uma chave e valor - otimiza pois pela chave identifica mais rapido

    @Override
    public void adicionarEsqoque(Produto produto, int quantidade) {
        int qtdAtual = this.estoque.getOrDefault(produto.getId(), 0); //se não recuperar o anterior ele ira sobrescerver se incluir mais produtos - verifica se no map contem a chave, se sim pega o valor
        this.estoque.put(produto.getId(), quantidade + qtdAtual); //incluimos o valor anterior ao atual
    }

    @Override
    public int quantidadeDisponivel(Produto produto) {
        return this.estoque.getOrDefault(produto.getId(),0);
    }

    @Override
    public boolean reservar(Pedido pedido) {
        return false;
    }

    @Override
    public void liberar(Pedido pedido, Produto produto, int quantidade) {

        if (reservar(pedido)) {
           return 0;
        }else {
            int qtdAtual = this.estoque.getOrDefault(produto.getId(), 0);
            this.estoque.put(produto.getId(), quantidade + qtdAtual);
        }

    }
}
