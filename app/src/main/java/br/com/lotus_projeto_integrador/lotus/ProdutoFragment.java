package br.com.lotus_projeto_integrador.lotus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProdutoFragment extends Fragment {

    private ViewGroup container;

    public ProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        container = (ViewGroup) view.findViewById(R.id.container);

        for (int i = 0; i < 10; i++){
            addItem("nomeProduto " + i, "categoriaProduto" +i, "precProduto" +i);
        }

            return container;
    }

    private void addItem(String nomeProduto, String categoriaProduto, String precProduto) {
        CardView cardView = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.cardviewproduto,container, false);

        TextView nome = (TextView) cardView.findViewById(R.id.nomeProduto);
        TextView categoria = (TextView) cardView.findViewById(R.id.categoriaProduto);
        TextView prec = (TextView) cardView.findViewById(R.id.precProduto);

        nome.setText(nomeProduto);
        categoria.setText(categoriaProduto);
        prec.setText(precProduto);

        container.addView(cardView);


    }

}
