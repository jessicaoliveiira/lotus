package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Exception;import java.lang.Override;import java.lang.String;
import java.net.HttpURLConnection;
import java.net.URL;import br.com.lotus_projeto_integrador.lotus.R;

public class DetalhesProduto extends AppCompatActivity {




    public String idProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        Intent intent = getIntent();
        idProduto = intent.getStringExtra("resultado");

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        conexaoWeb.execute(idProduto);

        Intent intent1 = getIntent();
        final int idProduto = intent1.getIntExtra("idProduto", 0);

        final String nomeProduto = intent1.getStringExtra("nomeProduto");
        TextView TextView= (TextView) findViewById(R.id.txtNomeProduto);
        TextView.setText("" + nomeProduto);

        final double precoProduto = intent1.getDoubleExtra("precoProduto", 0);
        TextView  = (TextView) findViewById(R.id.total);
        TextView.setText("" + precoProduto);

       final String descProduto = intent1.getStringExtra("descProduto");
        TextView  = (TextView) findViewById(R.id.txtDescProduto);
        TextView.setText(""  + descProduto);

        final double descontoPromocao = intent1.getDoubleExtra("descontoPromocao", 0);
        TextView  = (TextView) findViewById(R.id.desconto);
        TextView.setText("" + descontoPromocao);



        final String imagem = intent1.getStringExtra("imagem");

         byte [] encodeByte = Base64.decode(imagem, Base64.DEFAULT);
        final Bitmap bmp = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        ImageView img = (ImageView) findViewById(R.id.prod_img);
        img.setImageBitmap(bmp);



        Button comprar = (Button) findViewById(R.id.BtnComprar);
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
                carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(idProduto, nomeProduto,1,precoProduto  ));

                 Intent intent = new Intent(DetalhesProduto.this, CarrinhoActivity.class);
                 startActivity(intent);

            }
        });
        final Button BtnVoltar = (Button) findViewById(R.id.BtnVoltar);

        BtnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {


                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/produto/" + idProduto);
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

                    int idProduto = jsonobject.getInt("idProduto");
                    String nomeProduto = jsonobject.getString("nomeProduto");
                    Double precoProduto  = jsonobject.getDouble("precoProduto");
                    Double descontoPromocao = jsonobject.getDouble("descontoPromocao");
                    String descProduto = jsonobject.getString("descProduto");
                    String imagem = jsonobject.getString("imagemProduto");

                    byte [] encodeByte = Base64.decode(imagem, Base64.DEFAULT);
                    Bitmap bmp = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                    ImageView prod_img = (ImageView) findViewById(R.id.prod_img);
                    prod_img.setImageBitmap(bmp);

                    TextView Nome = (TextView)findViewById(R.id.txtNomeProduto);
                    Nome.setText(nomeProduto);

                    TextView Preco = (TextView)findViewById(R.id.total);
                    Preco.setText(Double.toString(precoProduto));

                    TextView Desconto = (TextView)findViewById(R.id.desconto);
                    Desconto.setText(Double.toString(descontoPromocao));

                    TextView Descricao = (TextView)findViewById(R.id.txtDescProduto);
                    Descricao.setText(descProduto);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


        }
