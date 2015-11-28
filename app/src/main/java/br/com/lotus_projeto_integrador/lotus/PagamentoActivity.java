package br.com.lotus_projeto_integrador.lotus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class PagamentoActivity extends AppCompatActivity {

    //private Spinner spn1;


    //String cartao[] = {"Opção1", "Opção2", "Opção3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        //spn1 = (Spinner) findViewById(R.id.spinner);

        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cartao);
        //spn1.setAdapter(arrayAdapter);

        CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();


        double precofinal = 0;
        for(CarrinhoProduto iten : carrinhoLogico.getItens().values()){

            double id = iten.getIdProduto();
            int qtd = iten.getQuantidade();
            precofinal = precofinal + (qtd * iten.getValor());

            Log.v("produto",id + ":"  + qtd);

        }

        Log.v("preco final", String.valueOf(precofinal));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lotus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pagamento) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
