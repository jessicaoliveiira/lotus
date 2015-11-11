package br.com.lotus_projeto_integrador.lotus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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

        try {
            testaInicializacao();
        } catch (Exception e) {
            exibeDialogo("Erro inicializando banco de dados");
            e.printStackTrace();
        }

    }

    /**
     * @throws Exception
     */
    public void testaInicializacao() throws Exception {
        if (usuarioController.findAll().isEmpty()) {
            UsuarioLogin usuario = new UsuarioLogin(null, "jessica", "12345678");
            usuarioController.insert(usuario);
        }
    }

    /**
     *
     */
    public void exibeDialogo(String mensagem) {
        alert = new AlertDialog.Builder(context);
        alert.setPositiveButton("OK", null);
        alert.setMessage(mensagem);
        alert.create().show();
    }

    public void validar(View view) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lotus, menu);
        return true;
    }
}
