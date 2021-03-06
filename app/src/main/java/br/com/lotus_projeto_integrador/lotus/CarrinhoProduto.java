package br.com.lotus_projeto_integrador.lotus;

import android.media.Image;
import android.widget.TextView;

import java.lang.Integer;import java.lang.String;
/**
 * Created by rony.rbarboza on 12/11/2015.
 */
public class CarrinhoProduto {
    private int IdProduto;
    private String Nome;
    private int quantidade;
    private String Img;
    private double valor;

    public Integer getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(Integer idProduto) {
        IdProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }



    public CarrinhoProduto(int idProduto,String Nome,int quantidade, Double valor) {
        this.Nome = Nome;
        this.IdProduto = idProduto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
