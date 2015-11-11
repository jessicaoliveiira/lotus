package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class CadastroFragment extends Fragment {

    public EditText campo_cpf;
    public EditText campo_telefone;
    public EditText campo_data_nascimento;
    public EditText campo_telefone_residencial;
    public EditText campo_senha;
    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        //Pass your layout xml to the inflater and assign it to rootView.
        final View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        final EditText campo_nome = (EditText) view.findViewById(R.id.CampoNome);

        final EditText campo_data_nascimento = (EditText) view.findViewById(R.id.CampoDataNascimento);
        campo_data_nascimento.addTextChangedListener(Mask.insert("##/##/####", campo_data_nascimento));

        final EditText campo_senha = (EditText) view.findViewById(R.id.CampoSenha);

        final EditText campo_cpf = (EditText) view.findViewById(R.id.CampoCPF);
        campo_cpf.addTextChangedListener(Mask.insert("###.###.###-##", campo_cpf));

        final EditText campo_telefone = (EditText) view.findViewById(R.id.CampoCelular);
        campo_telefone.addTextChangedListener(Mask.insert("(##)####-#####", campo_telefone));

        final EditText campo_telefone_residencial = (EditText) view.findViewById(R.id.CampoTelResidencial);
        campo_telefone_residencial.addTextChangedListener(Mask.insert("(##)####-####", campo_telefone_residencial));

        final EditText campo_email = (EditText) view.findViewById(R.id.CampoEmail);



        Button BtnSalvar = (Button) view.findViewById(R.id.BtnSalvar);
        BtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(campo_nome.getText().toString())) {


                validator.validateNotNull(campo_nome,"Preencha o campo nome");
                validator.validateNotNull(campo_cpf,"Preencha o campo CPF");
                validator.validateNotNull(campo_senha,"O campo senha deve ter no minímo 6 caracter");
                boolean cpf_valido = validator.validateCPF(campo_cpf.getText().toString());
                if(!cpf_valido){
                    campo_cpf.setError("CPF inválido");
                    campo_cpf.setFocusable(true);
                    campo_cpf.requestFocus();
                }
                boolean email_valido = validator.validateEmail(campo_email.getText().toString());
                if(!email_valido){
                    campo_email.setError("Email inválido");
                    campo_email.setFocusable(true);
                    campo_email.requestFocus();
                }
                    boolean senha_valido = validator.validateEmail(campo_senha.getText().toString());
                    if(!senha_valido){
                        campo_senha.setError("Senha inválida");
                        campo_senha.setFocusable(true);
                        campo_senha.requestFocus();
                    }

                } else {
                    Intent intent = new Intent(getActivity(), EnderecoActivity.class);
                    startActivity(intent);
                }



            }
        });
        Button BtnCancelar = (Button) view.findViewById(R.id.BtnCancelar);

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }


}

