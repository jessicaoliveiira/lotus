package br.com.lotus_projeto_integrador.lotus;

import java.lang.Integer;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;import br.com.lotus_projeto_integrador.lotus.CarrinhoProduto;

/**
 * Created by rony.rbarboza on 12/11/2015.
 */
public class CarrinhoLogico {

    private Map<Integer, CarrinhoProduto> carrinho = new HashMap<>();
    private static CarrinhoLogico ourInstance = new CarrinhoLogico();

    public static CarrinhoLogico getInstance() {
        return ourInstance;
    }

    private CarrinhoLogico() {
    }

    public Map<Integer, CarrinhoProduto> getItens() {
        return carrinho;
    }

    public void AddItenCarrinho(CarrinhoProduto produto){
        CarrinhoProduto carrinhoProduto = carrinho.get(produto.getIdProduto());
        if (carrinhoProduto != null) {
            carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + produto.getQuantidade());
        }
        else {
            carrinho.put(produto.getIdProduto(), produto);
        }

    }


    public void RemoveProduto(int  idProduto,int qtd){
        CarrinhoProduto carrinhoProduto = carrinho.get(idProduto);

        if(carrinhoProduto.getIdProduto() == idProduto){
            if(carrinhoProduto.getQuantidade() <= qtd){
                carrinho.remove(idProduto);
            }else{
                carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() - qtd);
            }
        }

    }

    public  void AdicionaProduto(int  idProduto,int qtd){
        CarrinhoProduto carrinhoProduto = carrinho.get(idProduto);
        carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + qtd);
    }

    public void DeleteProduto(int idProduto){
        carrinho.remove(idProduto);
    }


}