package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class EnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);


        final EditText campo_endereço = (EditText)findViewById(R.id.CampoEndereco);
        final EditText campo_logradouro = (EditText)findViewById(R.id.CampoLogradouro);
        final EditText campo_numero = (EditText)findViewById(R.id.CampoNumero);
        final EditText campo_CEP = (EditText)findViewById(R.id.CampoCEP);
        final EditText campo_cidade = (EditText)findViewById(R.id.CampoCidade);

        Button BtnSalvarEnd = (Button) findViewById(R.id.BtnSalvarEnd);
        BtnSalvarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    validator.validateNotNull(campo_endereço, "Preencha o campo endereço");
                    validator.validateNotNull(campo_logradouro, "Preencha o campo logradouro");
                    validator.validateNotNull(campo_numero, "Preencha o campo número");
                    validator.validateNotNull(campo_CEP, "Preencha o campo CEP");
                    validator.validateNotNull(campo_cidade, "Preencha o campo Cidade");

                }


        });


       final Button BtnCancelarEnd = (Button) findViewById(R.id.BtnCancelarEnd);

        BtnCancelarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
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
    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://tsitomcat.azurewebsites.net/julietg1/rest/produtoid/2");
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
                String CampoEndereco = json.getString("CampoEndereco");
                String CampoLogradouro = json.getString("CampoLogradouro");
                String CampoNumero = json.getString("CampoNumero");
                String CampoCompl = json.getString("CampoCompl");
                String CampoCEP = json.getString("CampoCEP");
                String CampoCidade = json.getString("CampoCidade");
                String CampoPais = json.getString("CampoPais");



                Intent intent = new Intent(EnderecoActivity.this, LotusActivity.class);

                intent.putExtra("CampoEndereco", CampoEndereco);
                intent.putExtra("CampoLogradouro", CampoLogradouro);
                intent.putExtra("CampoNumero", CampoNumero);
                intent.putExtra("CampoCompl", CampoCompl);
                intent.putExtra("CampoCEP", CampoCEP);
                intent.putExtra("CampoCidade", CampoCidade);
                intent.putExtra("CampoPais", CampoPais);
                

                startActivity(intent);



            } catch (Exception e) {

            }
        }
    }
}
