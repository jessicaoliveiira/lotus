package br.com.lotus_projeto_integrador.lotus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginFragment2 extends Fragment {


    public LoginFragment2() {
        // Required empty public constructor
    }

    private EditText editUsuario;
    private EditText editSenha;
    private Button btnValidar;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_fragment2, container, false);
        super.onCreate(savedInstanceState);

        final ConexaoWeb conexaoWeb = new ConexaoWeb();
        conexaoWeb.execute();

        editUsuario = (EditText) view.findViewById(R.id.editUsuario);

        editSenha = (EditText) view.findViewById(R.id.editSenha);

        btnValidar = (Button) view.findViewById(R.id.btnValidar);


        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PagamentoActivity.class);
                startActivity(intent);

            }
        });
        return view;

    }


    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {

                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/login/");
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

                    String editUsuario = jsonobject.getString("nomeUsuario");
                    String editSenha = jsonobject.getString("senhaUsuario");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
