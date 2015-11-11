package br.com.lotus_projeto_integrador.lotus;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v4.app.Fragment;



public class LoginFragment2 extends Fragment {


    public LoginFragment2() {
        // Required empty public constructor
    }

    private EditText editUsuario, editSenha;
    private LoginFragment2 context;
    private UsuarioController usuarioController;
    private AlertDialog.Builder alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login_fragment2, container, false);
        super.onCreate(savedInstanceState);
        context = this;
        usuarioController = UsuarioController.getInstance(getContext());
        editUsuario = (EditText) view.findViewById(R.id.editSenha);
        editSenha = (EditText) view.findViewById(R.id.editUsuario);

        try {
            testaInicializacao();
        } catch (Exception e) {
            exibeDialogo("Erro inicializando banco de dados");
            e.printStackTrace();
        }

        return view;

    }

    /**
     * @throws Exception
     */
    public void testaInicializacao() throws Exception {
        if (usuarioController.findAll().isEmpty()) {
            UsuarioLogin usuario = new UsuarioLogin(null, "jessica", "123");
            usuarioController.insert(usuario);
        }
    }

    /**
     *
     */
    public void exibeDialogo(String mensagem) {
        alert = new AlertDialog.Builder(getContext());
        alert.setPositiveButton("OK", null);
        alert.setMessage(mensagem);
        alert.create().show();
    }

    public void btnValidar (View view) {
        String usuario = editUsuario.getText().toString();
        String senha = editSenha.getText().toString();

        try {
            boolean isValid = usuarioController.validaLogin(usuario, senha);
            if (isValid) {
                exibeDialogo("Usuario e senha validados com sucesso!");
            } else {
                exibeDialogo("Verifique usuario e senha!");
            }
        } catch (Exception e) {
            exibeDialogo("Erro validando usuario e senha");
            e.printStackTrace();
        }
    }


}
