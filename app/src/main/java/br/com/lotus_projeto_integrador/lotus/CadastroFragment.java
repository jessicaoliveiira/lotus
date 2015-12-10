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

    public EditText campo_nome;
    public EditText campo_email;
    public EditText campo_senha;
    public EditText campo_cpf;
    public EditText campo_celular;
    public EditText campo_telefone_comercial;
    public EditText campo_telefone_residencial;
    public EditText campo_data_nascimento;

    public String strNomeCliente;
    public String strEmailCliente;
    public String strSenhaCliente;
    public String strCpfCliente;
    public String strDtNasc;
    public String strCelCliente;
    public String strTelRes;
    public String strTelCom;

    public Button BtnSalvar;

    public View view;

    public CadastroFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        campo_nome = (EditText) view.findViewById(R.id.nomeCliente);

        campo_email = (EditText) view.findViewById(R.id.emailCliente);

        campo_senha = (EditText) view.findViewById(R.id.senhaCliente);

        campo_cpf = (EditText) view.findViewById(R.id.cpfCliente);
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        campo_celular = (EditText) view.findViewById(R.id.celCliente);
         campo_celular.addTextChangedListener(Mask.insert("(##)####-#####", campo_celular));

        campo_telefone_residencial = (EditText) view.findViewById(R.id.telRelCliente);
        campo_telefone_residencial.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone_residencial));

        campo_telefone_comercial = (EditText) view.findViewById(R.id.telComCliente);
        campo_telefone_comercial.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone_comercial));

        campo_data_nascimento = (EditText) view.findViewById(R.id.dtNascCliente);
        campo_data_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_data_nascimento));



        BtnSalvar = (Button) view.findViewById(R.id.BtnSalvar);
        BtnSalvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                strNomeCliente = campo_nome.getText().toString();
                strEmailCliente = campo_email.getText().toString();
                strSenhaCliente = campo_senha.getText().toString();
                strCpfCliente = campo_cpf.getText().toString();
                strDtNasc = campo_data_nascimento.getText().toString();
                strCelCliente = campo_celular.getText().toString();
                strTelRes = campo_telefone_residencial.getText().toString();
                strTelCom = campo_telefone_comercial.getText().toString();


                if(strNomeCliente.isEmpty() || strNomeCliente.equals("") || strNomeCliente.equals(" ")){
                    strNomeCliente = null;
                }

                if(strEmailCliente.isEmpty() || strEmailCliente.equals("") || strEmailCliente.equals(" ")){
                    strEmailCliente = null;
                }

                if(strSenhaCliente.isEmpty() || strSenhaCliente.equals("") || strSenhaCliente.equals(" ")){
                    strSenhaCliente = null;
                }

                if(strCpfCliente.isEmpty() || strCpfCliente.equals("") || strCpfCliente.equals(" ")){
                    strCpfCliente = null;
                }

                if(strDtNasc.isEmpty() || strDtNasc.equals("") || strDtNasc.equals(" ")){
                    strDtNasc = null;
                }

                if(strCelCliente.isEmpty() || strCelCliente.equals("") || strCelCliente.equals(" ")){
                    strCelCliente = null;
                }

                if(strTelRes.isEmpty() || strTelRes.equals("") || strTelRes.equals(" ")){
                    strTelRes = null;
                }

                if(strTelCom.isEmpty() || strTelCom.equals("") || strTelCom.equals(" ")){
                    strTelCom = null;
                }


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

                    ConexaoWeb conexaoWeb = new ConexaoWeb();
                    conexaoWeb.execute(strNomeCliente, strEmailCliente, strSenhaCliente, strCpfCliente, strCelCliente, strTelCom, strTelRes);

                    Intent intent = new Intent(getActivity(), EnderecoActivity.class);
                    startActivity(intent);
                }

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
        protected String doInBackground(String... strings) {
            try {

                String url = "http://tsitomcat.azurewebsites.net/lotus/rest/cliente/" + strNomeCliente + "/" + strEmailCliente + "/" + strSenhaCliente + "/" + strCpfCliente + "/" + strCelCliente + "/" + strTelCom + "/" + strTelRes + "/1993-10-01/1";
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
                JSONObject jsonobject = new JSONObject(s);
                //JSONObject json = new JSONObject(s);

                String idCliente = jsonobject.getString("idCliente");

                Log.v("teste id",idCliente);

                Intent intent = new Intent(getActivity(),EnderecoActivity.class);
                intent.putExtra("a",idCliente);

                Log.v("teste id 1",idCliente);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

