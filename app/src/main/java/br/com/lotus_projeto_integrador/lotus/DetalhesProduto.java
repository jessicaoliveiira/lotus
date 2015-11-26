package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    public ImageView produtoView;
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
        final int idProduto = intent.getIntExtra("idProduto", 0);


        final String txtNomeProduto = intent.getStringExtra("nomeProduto");
        TextView TextView= (TextView) findViewById(R.id.txtNomeProduto);
        TextView.setText("" + txtNomeProduto);

        String txtCategProduto = intent.getStringExtra("nomeCategoria");
        TextView  = (TextView) findViewById(R.id.txtCategProduto);
        TextView.setText(""  + txtCategProduto);

        final double precProduto = intent.getDoubleExtra("precProduto",0);
        TextView  = (TextView) findViewById(R.id.txtValorPrecoDe);
        TextView.setText("" + precProduto);


        produtoView = (ImageView) findViewById(R.id.prod_img);

        Button button = (Button) findViewById(R.id.BtnComprar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrinhoLogico carrinhoLogico = CarrinhoLogico.getInstance();
                carrinhoLogico.AddItenCarrinho(new CarrinhoProduto(idProduto, txtNomeProduto,1,precProduto));

                Intent intent = new Intent(DetalhesProduto.this, CarrinhoActivity.class);
                startActivity(intent);

            }
        });


    }

    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {


                URL url = new URL("http://tsitomcat.azurewebsites.net/julietg1/rest/produtoid/" + idProduto);
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
                    Double precProduto = jsonobject.getDouble("precoProduto");
                    Double descontoPromocao = jsonobject.getDouble("descontoPromocao");

                    TextView Nome = (TextView)findViewById(R.id.txtNomeProduto);
                    Nome.setText(nomeProduto);

                    TextView Preco = (TextView)findViewById(R.id.txtPrecoDe);
                    Preco.setText(Double.toString(precProduto));

                    TextView Desconto = (TextView)findViewById(R.id.txtValorPrecoDe);
                    Desconto.setText(Double.toString(descontoPromocao));

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
