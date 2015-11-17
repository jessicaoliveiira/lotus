package br.com.lotus_projeto_integrador.lotus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;import java.lang.Integer;import java.lang.Override;import java.lang.String;import br.com.lotus_projeto_integrador.lotus.CarrinhoLogico;import br.com.lotus_projeto_integrador.lotus.CarrinhoProduto;import br.com.lotus_projeto_integrador.lotus.R;



/**
 * A simple {@link Fragment} subclass.
 */
public class Carrinho extends Fragment {
    private ViewGroup container2;
    String Nome;

    public Carrinho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);

/*
adicinar itens no carrinho
* */

        CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
        carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(1,"Macarrão",2,60));
        carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(1,"Macarrão Elio",2,60));
        carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(2,"Macarrão",2,60));
        carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(6,"Macarrão pato ganço do tico e teco", 2, 60));

        container2 = (ViewGroup) view.findViewById(R.id.container2);


        for(CarrinhoProduto iten : carrinhoLogico.getItens().values()){
            if(iten.getNome().length() > 20){
                Nome = iten.getNome().substring(0,20) + "...";
            }else{
                Nome = iten.getNome();
            }

            addIten(Nome,String.valueOf(iten.getValor()),String.valueOf(iten.getQuantidade()),iten.getIdProduto());
        }

        return view;
    }

    private  void addIten(String titulo,String valor,String qtd,final int IdProduto){

        final LinearLayout linha = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.activity_linha_carrinho,container2,false);
        TextView TituloView = (TextView) linha.findViewById(R.id.Nome);
        TextView MensagemView = (TextView) linha.findViewById(R.id.Preco);
        final TextView QtdView = (TextView) linha.findViewById(R.id.Qtd);
        ImageView imageView = (ImageView) linha.findViewById(R.id.image);


        TituloView.setText(titulo);
        MensagemView.setText(valor);
        QtdView.setText(qtd);

        Button exclui = (Button) linha.findViewById( R.id.RemoveItem);
        exclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
                carrinhoLogico.DeleteProduto(IdProduto);
                container2.removeView(linha);
            }
        });

        Button remove = (Button) linha.findViewById(R.id.subtrai);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
                carrinhoLogico.RemoveProduto(IdProduto,1);

                if(Integer.parseInt((String) QtdView.getText()) > 1) {
                    QtdView.setText(String.valueOf(Integer.parseInt((String) QtdView.getText()) - 1));
                }else{
                    container2.removeView(linha);
                }
            }
        });


        Button soma = (Button) linha.findViewById(R.id.soma);
        soma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
                carrinhoLogico.AdicionaProduto(IdProduto,1);
                QtdView.setText(String.valueOf(Integer.parseInt((String) QtdView.getText()) + 1));

            }
        });


        container2.addView(linha);

    }
}