package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class EnderecoActivity extends AppCompatActivity {

    String strNomeEndereco;
    String strLogradouro;
    String strNumeroEnd;
    String strComplemento;
    String strCep;
    String strCidade;
    String strUf;
    String strPais;

    public View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);

        final EditText NomeEndereco = (EditText) findViewById(R.id.nomeEndereco);
        final EditText Logradouro = (EditText) findViewById(R.id.logradouro);
        final EditText NumeroEnd = (EditText) findViewById(R.id.numeroEnd);
        final EditText Complemento = (EditText)findViewById(R.id.complemento);
        final EditText CepEndereco = (EditText) findViewById(R.id.cepEndereco);
        final EditText Cidade = (EditText) findViewById(R.id.cidade);
        final EditText UFendereco = (EditText) findViewById(R.id.uf);
        final EditText Pais = (EditText) findViewById(R.id.pais);

        Button BtnSalvarEnd = (Button) findViewById(R.id.BtnSalvarEnd);
        BtnSalvarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strNomeEndereco = NomeEndereco.getText().toString();
                strLogradouro = Logradouro.getText().toString();
                strNumeroEnd = NumeroEnd.getText().toString();
                strComplemento = Complemento.getText().toString();
                strCep = CepEndereco.getText().toString();
                strCidade = Cidade.getText().toString();
                strUf = UFendereco.getText().toString();
                strPais = Pais.getText().toString();

                if (strNomeEndereco.isEmpty() || strNomeEndereco.equals("") || strNomeEndereco.equals(" ")) {
                    strNomeEndereco = null;
                }

                if (strLogradouro.isEmpty() || strLogradouro.equals("") || strLogradouro.equals(" ")) {
                    strLogradouro = null;
                }

                if (strNumeroEnd.isEmpty() || strNumeroEnd.equals("") || strNumeroEnd.equals(" ")) {
                    strNumeroEnd = null;
                }

                if (strComplemento.isEmpty() || strComplemento.equals("") || strComplemento.equals(" ")) {
                    strComplemento = null;
                }

                if (strCep.isEmpty() || strCep.equals("") || strCep.equals(" ")) {
                    strCep = null;
                }

                if (strCidade.isEmpty() || strCidade.equals("") || strCidade.equals(" ")) {
                    strCidade = null;
                }

                if (strUf.isEmpty() || strUf.equals("") || strUf.equals(" ")) {
                    strUf = null;
                }

                if (strPais.isEmpty() || strPais.equals("") || strPais.equals(" ")) {
                    strPais = null;
                }

                if (NomeEndereco.getText().length() == 0 || Logradouro.getText().length() <= 3 ||
                        NumeroEnd.getText().length() <= 1 || CepEndereco.getText().length() <= 8 ||
                        Cidade.getText().length() <= 3 || UFendereco.getText().length() <= 2 ||
                        Pais.getText().length() <= 3) {
                    validator.validateNotNull(NomeEndereco, "Preencha o campo endereço");
                    validator.validateNotNull(Logradouro, "Preencha o campo logradouro");
                    validator.validateNotNull(NumeroEnd, "Preencha o campo número");
                    validator.validateNotNull(CepEndereco, "Preencha o campo CEP");
                    validator.validateNotNull(Cidade, "Preencha o campo Cidade");
                    validator.validateNotNull(UFendereco, "Preencha o campo UF");
                    validator.validateNotNull(Pais, "Preencha o campo País");

                } else {

                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    conexaoWeb.execute(strNomeEndereco, strLogradouro, strNumeroEnd, strComplemento, strCep, strCidade, strUf, strPais);

                    Intent intent = new Intent(EnderecoActivity.this, ProdCategoriaActivity.class);
                    startActivity(intent);

                }
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
        protected String doInBackground(String... strings) {
            try {

                String url = "http://tsitomcat.azurewebsites.net/lotus/rest/cliente/" + "2" + "/" + strNomeEndereco + "/" + strLogradouro + "/" + strNumeroEnd + "/" + strCep + "/" + strComplemento+ "/" + strCidade + "/" + strPais + "/" + strUf;
                url = url.replaceAll(" ", "%20");

                Log.v("url", url);

                URL weburl = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) weburl.openConnection();

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

                    String nomeEndereco = jsonobject.getString("nomeEndereco");
                    String logradouroEndereco = jsonobject.getString("logradouro");
                    String numeroEndereco = jsonobject.getString("numeroEnd");
                    String complementoEndereco = jsonobject.getString("complemento");
                    String cepEndereco = jsonobject.getString("cepEndereco");
                    String cidadeEndereco = jsonobject.getString("cidade");
                    String ufEndereco = jsonobject.getString("uf");
                    String paisEndereco = jsonobject.getString("pais");


                    TextView txtNomeEndereco = (TextView) view.findViewById(R.id.nomeEndereco);
                    txtNomeEndereco.setText(nomeEndereco);

                    TextView txtLogradouro = (TextView) view.findViewById(R.id.logradouro);
                    txtLogradouro.setText(logradouroEndereco);

                    TextView txtNumEndereco = (TextView) view.findViewById(R.id.numeroEnd);
                    txtNumEndereco.setText(numeroEndereco);

                    TextView txtComplementoEnd = (TextView) view.findViewById(R.id.complemento);
                    txtComplementoEnd.setText(complementoEndereco);

                    TextView txtCepEnd = (TextView) view.findViewById(R.id.cepEndereco);
                    txtCepEnd.setText(cepEndereco);

                    TextView txtCidadeEnd = (TextView) view.findViewById(R.id.cidade);
                    txtCidadeEnd.setText(cidadeEndereco);

                    TextView txtUfEnd = (TextView) view.findViewById(R.id.uf);
                    txtUfEnd.setText(ufEndereco);

                    TextView txtPais = (TextView) view.findViewById(R.id.pais);
                    txtPais.setText(paisEndereco);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
