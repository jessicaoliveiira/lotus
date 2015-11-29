package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class CadastroFragment extends Fragment {

  /*  public EditText campo_nome;
    public EditText campo_email;
    public EditText campo_senha;
    public EditText campo_cpf;
    public EditText campo_celular;
    public EditText campo_telefone_comercial;
    public EditText campo_telefone_residencial;
    public EditText campo_data_nascimento;*/

    public String nomeCliente;
    public String emailCliente;
    public String senhaCliente;
    public String cpfCliente;
    public String dtNasc;
    public String celCliente;
    public String telRes;
    public String telCom;

    public View view;

    public CadastroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //Pass your layout xml to the inflater and assign it to rootView.
        view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        final EditText campo_nome = (EditText) view.findViewById(R.id.nomeCliente);

        final EditText campo_email = (EditText) view.findViewById(R.id.emailCliente);

        final EditText campo_senha = (EditText) view.findViewById(R.id.senhaCliente);

        final EditText campo_cpf = (EditText) view.findViewById(R.id.cpfCliente);
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        final EditText campo_celular = (EditText) view.findViewById(R.id.celCliente);
        campo_celular.addTextChangedListener(Mask.insert("(##)####-#####", campo_celular));

        final EditText campo_telefone_residencial = (EditText) view.findViewById(R.id.telRelCliente);
        campo_telefone_residencial.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone_residencial));

        final EditText campo_telefone_comercial = (EditText) view.findViewById(R.id.telComCliente);
        campo_telefone_comercial.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone_comercial));

        final EditText campo_data_nascimento = (EditText) view.findViewById(R.id.dtNascCliente);
        campo_data_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_data_nascimento));

        nomeCliente = campo_nome.getText().toString();
        emailCliente = campo_email.getText().toString();
        senhaCliente = campo_senha.getText().toString();
        cpfCliente = campo_cpf.getText().toString();
        dtNasc = campo_data_nascimento.getText().toString();
        celCliente = campo_celular.getText().toString();
        telRes = campo_telefone_residencial.getText().toString();
        telCom = campo_telefone_comercial.getText().toString();


        final Button BtnSalvar = (Button) view.findViewById(R.id.BtnSalvar);
        BtnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (campo_nome.getText().length() == 0 || campo_senha.getText().length() <= 3) {
                    validator.validateNotNull(campo_nome, "Preencha o campo nome");
                    validator.validateNotNull(campo_cpf, "Preencha o campo CPF");
                    validator.validateNotNull(campo_senha, "O campo senha deve ter no minímo 6 caracter");
                    boolean cpf_valido = validator.validateCPF(campo_cpf.getText().toString());
                    if (!cpf_valido) {
                        campo_cpf.setError("CPF inválido");
                        campo_cpf.setFocusable(true);
                        campo_cpf.requestFocus();
                    }
                    boolean email_valido = validator.validateEmail(campo_email.getText().toString());
                    if (!email_valido) {
                        campo_email.setError("Email inválido");

                        campo_email.setFocusable(true);
                        campo_email.requestFocus();
                    }

                } else {
                    Intent intent = new Intent(getActivity(), EnderecoActivity.class);
                    startActivity(intent);
                }

                ConexaoWeb conexaoCep = new ConexaoWeb();
                conexaoCep.execute();

            }
        });

        Button BtnCancelar = (Button) view.findViewById(R.id.BtnCancelar);

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LotusActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public class ConexaoWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
Log.v("url","http://tsitomcat.azurewebsites.net/lotus/rest/cadastro/" +nomeCliente+ "/" +emailCliente+ "/" +senhaCliente+ "/" +cpfCliente+ "/" +celCliente+ "/" +telCom+ "/" +telRes+ "/" +dtNasc+ "/1");

                URL url = new URL("http://tsitomcat.azurewebsites.net/lotus/rest/cadastro/" +nomeCliente+ "/" +emailCliente+ "/" +senhaCliente+ "/" +cpfCliente+ "/" +celCliente+ "/" +telCom+ "/" +telRes+ "/" +dtNasc+ "/1");
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

                    String nomeCompletoCliente = jsonobject.getString("nomeCompletoCliente");
                    String emailCliente = jsonobject.getString("emailCliente");
                    String senhaCliente = jsonobject.getString("senhaCliente");
                    String CPFCliente = jsonobject.getString("CPFCliente");
                    String celularCliente = jsonobject.getString("celularCliente");
                    String telComercialCliente = jsonobject.getString("telComercialCliente");
                    String telResidencialCliente = jsonobject.getString("telResidencialCliente");
                    String dtNascCliente = jsonobject.getString("dtNascCliente");


                    TextView NomeCompletoCliente = (TextView) view.findViewById(R.id.nomeCliente);
                    NomeCompletoCliente.setText(nomeCompletoCliente);

                    TextView EmailCliente = (TextView) view.findViewById(R.id.emailCliente);
                    EmailCliente.setText(emailCliente);

                    TextView SenhaCliente = (TextView) view.findViewById(R.id.senhaCliente);
                    SenhaCliente.setText(senhaCliente);

                    TextView CPFcliente = (TextView) view.findViewById(R.id.cpfCliente);
                    CPFcliente.setText(CPFCliente);

                    TextView CelularCliente = (TextView) view.findViewById(R.id.celCliente);
                    CelularCliente.setText(celularCliente);

                    TextView TelResidencialCliente = (TextView) view.findViewById(R.id.telRelCliente);
                    TelResidencialCliente.setText(telResidencialCliente);

                    TextView TelComercialCliente = (TextView) view.findViewById(R.id.telComCliente);
                    TelComercialCliente.setText(telComercialCliente);

                    TextView DtNascCliente = (TextView) view.findViewById(R.id.dtNascCliente);
                    DtNascCliente.setText(dtNascCliente);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

