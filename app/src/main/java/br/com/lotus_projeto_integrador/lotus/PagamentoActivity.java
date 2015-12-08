package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class PagamentoActivity extends AppCompatActivity {

    //private Spinner spn1;

    public String uurl;
    public int IdUsuario;
    public int IdEndereco;
    public int id;
    public int qtd;
    public int idpedido;
    public double valor;

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

            id = iten.getIdProduto();
            qtd = iten.getQuantidade();
            precofinal = precofinal + (qtd * iten.getValor());

        }

        TextView textView = (TextView) findViewById(R.id.CampoValorTotal);
        textView.setText(String.valueOf(precofinal));


        Button finalizar = (Button) findViewById( R.id.BtnFinalizar);
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();
                conexaoWeb.execute();

                Intent intent = new Intent(PagamentoActivity.this, CompraOk.class);
                startActivity(intent);
            }
        });

        Intent intent  = new Intent(this, Carrinho.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


    }


    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

               URL  url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/pedido/" + IdUsuario  + "/1/1/" + IdEndereco + "/1");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();



                InputStream in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                //Lê linha a linha a resposta e armazena no StringBuilder
                while ((inputStr = reader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                String respostaCompleta = responseStrBuilder.toString();

                Log.v("Json", respostaCompleta);

                return respostaCompleta;

            } catch (Exception e) {

            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray json = new JSONArray(s);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject jsonobject = json.getJSONObject(i);
                    //JSONObject json = new JSONObject(s);

                   idpedido = jsonobject.getInt("idPedido");


                }

                ConexaoWeb conexaoWeb = new ConexaoWeb();
                conexaoWeb.execute();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }}


    public class ConexaoWeb2 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/carrinho/" + id  + idpedido + qtd + valor);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                //Lê linha a linha a resposta e armazena no StringBuilder
                while ((inputStr = reader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                String respostaCompleta = responseStrBuilder.toString();

                Log.v("Json", respostaCompleta);

                return respostaCompleta;

            } catch (Exception e) {

            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray json = new JSONArray(s);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject jsonobject = json.getJSONObject(i);
                    //JSONObject json = new JSONObject(s);


                }


                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();


                double precofinal = 0;
                for(CarrinhoProduto iten : carrinhoLogico.getItens().values()){

                    id = iten.getIdProduto();
                    qtd = iten.getQuantidade();
                    valor = iten.getValor();
                    ConexaoWeb2 conexaoWeb2 = new ConexaoWeb2();
                    conexaoWeb2.execute();


                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }}



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
