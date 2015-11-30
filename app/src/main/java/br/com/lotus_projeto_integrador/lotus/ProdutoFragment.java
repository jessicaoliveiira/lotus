package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProdutoFragment extends Fragment {

    private ViewGroup container;

    public ProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        this.container = (ViewGroup) view.findViewById(R.id.container);

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        conexaoWeb.execute();

        return view;
    }

    private void addItem(final int id, final String nomeProduto, final double precoProduto, final String descProduto, final double descontoPromocao, final String imagem) {
        CardView cardView = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.cardviewproduto, container, false);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetalhesProduto.class);
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

                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/produtoAll/");
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



                    addItem(idProduto, nomeProduto, precoProduto, descProduto, descontoPromocao,  imagem);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}