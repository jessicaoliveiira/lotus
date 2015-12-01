package br.com.lotus_projeto_integrador.lotus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {

    private EditText editUsuario, editSenha;
    private Context context;
    private UsuarioController usuarioController;
    private AlertDialog.Builder alert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        usuarioController = UsuarioController.getInstance(context);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editSenha = (EditText) findViewById(R.id.editSenha);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lotus, menu);
        return true;
    }


    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/login/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                //Cria um leitor para ler a resposta
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;

                //Lê linha a linha a resposta e armazena no StringBuilder
                while ((inputStr = reader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                //Transforma o StringBuilder em String, que contém a resposta final
                String respostaCompleta = responseStrBuilder.toString();

                return respostaCompleta;

            } catch (Exception e) {

            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject json = new JSONObject(s);
                String emailCliente = json.getString("emailCliente");
                String senhaCliente = json.getString("senhaCliente");


                Intent intent = new Intent(LoginActivity.this, LotusActivity.class);

                intent.putExtra("emailCliente", emailCliente);
                intent.putExtra("senhaCliente", senhaCliente);

                startActivity(intent);



            } catch (Exception e) {

            }
        }
    }
}