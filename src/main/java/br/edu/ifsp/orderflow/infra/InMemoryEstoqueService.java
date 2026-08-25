package br.edu.ifsp.orderflow.infra;
import br.edu.ifsp.orderflow.domain.ItemPedido;
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

        //List<ItemPedido> itens = pedido.getItens();

        //Conferir se todos os itens tem estoque, se 1 não esiver retorna false

        //1 FORMA

        //for(ItemPedido item : itens){
        //Itempedido item= itens.get(i);

        //If(item.getQuantidade() > this.quantidadeDisponivel(item.getProduto())){
        //    return false;
        //}
        //}


        //2 FORMA + intuitiva
        for(ItemPedido item : pedido.getItens()){

            int disponivel =this.quantidadeDisponivel(item.getProduto());

            if(item.getQuantidade() > disponivel){
                return false;
            }
        }

        for(ItemPedido item : pedido.getItens()){

            Produto produto = item.getProduto();
            String produtoId = produto.getId(); // é igual a: item.getProduto().getId(); - esse é mais otimizado
            int quantidadeAtual = this.estoque.getOrDefault(produtoId,0);

            this.estoque.put(produtoId, quantidadeAtual - item.getQuantidade());
        }
        return true;
    }

    @Override
    public void liberar(Pedido pedido) {

        //1 FORMA
//        for(ItemPedido item : pedido.getItens()){
//
//            Produto produto = item.getProduto();
//            String produtoId = produto.getId();
//            int quantidadeAtual = this.estoque.getOrDefault(produtoId,0);
//
//            this.estoque.put(produtoId, quantidadeAtual + item.getQuantidade());
//        }

        for(ItemPedido item: pedido.getItens()){
            this.adicionarEsqoque(item.getProduto(), item.getQuantidade());
        }

    }
}
