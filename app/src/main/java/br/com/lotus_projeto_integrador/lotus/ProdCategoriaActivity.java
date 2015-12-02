package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Double;import java.lang.Exception;import java.lang.Override;import java.lang.String;import java.lang.StringBuilder;import java.lang.Void;import java.net.HttpURLConnection;
import java.net.URL;import br.com.lotus_projeto_integrador.lotus.DetalhesProduto;import br.com.lotus_projeto_integrador.lotus.R;

public class ProdCategoriaActivity extends AppCompatActivity {

    private ViewGroup container;
    public int idCategoria;
    public ImageView produtoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_categoria);

        container = (ViewGroup) findViewById(R.id.container);
        Intent intent  = getIntent();
        idCategoria = intent.getIntExtra("idCategoria", 0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        conexaoWeb.execute(Integer.toString(idCategoria));





    }

    private void addItem(final int id, final String nomeProduto, final double precoProduto, final String descProduto, final double descontoPromocao, final String imagem) {
        CardView cardView = (CardView) LayoutInflater.from(ProdCategoriaActivity.this).inflate(R.layout.cardview_produto_categoria, container, false);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProdCategoriaActivity.this, DetalhesProduto.class);
                intent.putExtra("idProduto", id);
                intent.putExtra("nomeProduto", nomeProduto);
                intent.putExtra("precoProduto", precoProduto);
                intent.putExtra("descProduto", descProduto);
                intent.putExtra("descontoPromocao", descontoPromocao);
                intent.putExtra("imagem", imagem);
                startActivity(intent);
            }


        });


        TextView nome = (TextView) cardView.findViewById(R.id.nomeProduto);
        TextView prec = (TextView) cardView.findViewById(R.id.precProduto);
        TextView categoria = (TextView) cardView.findViewById(R.id.categoriaProduto);
        ImageView prod_img = (ImageView) cardView.findViewById(R.id.imgProduto);


        nome.setText(nomeProduto);

        prec.setText(Double.toString(precoProduto));

        byte [] encodeByte = Base64.decode(imagem, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        prod_img.setImageBitmap(bmp);


        container.addView(cardView);
    }


 

    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/prodcategoria/" + idCategoria);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();


                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                //Lê linha a linha a resposta e armazena no StringBuilder
                while ((inputStr = reader.readLine()) != null) responseStrBuilder.append(inputStr);

                String respostaCompleta = responseStrBuilder.toString();

                Log.v("Json", respostaCompleta);

                return respostaCompleta;

            } catch (Exception e) {
e.printStackTrace();
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
                    Double precoProduto = jsonobject.getDouble("precoProduto");
                    Double descontoPromocao = jsonobject.getDouble("descontoPromocao");
                     String descProduto = jsonobject.getString("descProduto");
                    //int categoriaProduto = jsonobject.getInt("categoriaProduto");

                    String imagem = jsonobject.getString("imagemProduto");


                    addItem(idProduto, nomeProduto, precoProduto, descProduto, descontoPromocao, imagem);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
