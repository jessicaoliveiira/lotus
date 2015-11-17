package br.com.lotus_projeto_integrador.lotus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProdCategoriaActivity extends AppCompatActivity {

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_categoria);

        container = (ViewGroup) findViewById(R.id.container);
        for (int i = 0; i < 10; i++){

            addItem("nomeProduto " + i, "categoriaProduto" +i, "precProduto" +i);
        }

    }

    private void addItem(String nomeProduto, String categoriaProduto, String precProduto){
        CardView cardView = (CardView) LayoutInflater.from(ProdCategoriaActivity.this).inflate(R.layout.cardviewcategoria,
                container, false);

        TextView nome = (TextView) cardView.findViewById(R.id.nomeProduto);
        TextView categoria = (TextView) cardView.findViewById(R.id.categoriaProduto);
        TextView prec = (TextView) cardView.findViewById(R.id.precProduto);

        nome.setText(nomeProduto);
        categoria.setText(categoriaProduto);
        prec.setText(precProduto);

        container.addView(cardView);
    }

}
