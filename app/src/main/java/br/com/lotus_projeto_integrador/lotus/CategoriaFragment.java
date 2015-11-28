package br.com.lotus_projeto_integrador.lotus;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;


public class CategoriaFragment extends Fragment {

    private ViewGroup container;



    public CategoriaFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categoria, container, false);


        this.container = (ViewGroup) view.findViewById(R.id.container);

        ConexaoWeb conexaoWeb = new ConexaoWeb();
        conexaoWeb.execute();



        return view;

    }

    private void addItem(final int idCategoria, String nomeCategoria) {
        CardView cardView = (CardView) LayoutInflater.from(getActivity()).inflate(R.layout.cardviewcategoria, container, false);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProdCategoriaActivity.class);
                intent.putExtra("idCategoria", idCategoria);
                startActivity(intent);

            }
        });


        TextView nome = (TextView) cardView.findViewById(R.id.nomeCategoria);
        nome.setText(nomeCategoria);
        container.addView(cardView);

    }

    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL("http://tsitomcat.azurewebsites.net/julietg1/rest/categoria");
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

                    int idCategoria = jsonobject.getInt("idCategoria");
                    String nomeCategoria = jsonobject.getString("nomeCategoria");


                    addItem(idCategoria, nomeCategoria);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
