package br.com.lotus_projeto_integrador.lotus;


        import android.content.Context;

        import java.util.List;

/**
 * Created by jessica on 11/11/15.
 */
public class UsuarioController {

    private static UsuarioDAO usuarioDAO;
    private static UsuarioController instance;

    public static UsuarioController getInstance(Context context) {
        if (instance == null) {
            instance = new UsuarioController();
            usuarioDAO = new UsuarioDAO(context);
        }
        return instance;
    }

    public void insert(UsuarioLogin usuario) throws Exception {
        usuarioDAO.insert(usuario);
    }

    public void update(UsuarioLogin usuario) throws Exception {
        usuarioDAO.update(usuario);
    }

    public List<UsuarioLogin> findAll() throws Exception {
        return usuarioDAO.findAll();
    }

    public boolean validaLogin(String usuario, String senha) throws Exception {
        UsuarioLogin user = usuarioDAO.findByLogin(usuario, senha);
        if (user == null || user.getUsuario() == null || user.getSenha() == null) {
            return false;
        }
        String informado = usuario + senha;
        String esperado = user.getUsuario() + user.getSenha();
        if (informado.equals(esperado)) {
            return true;
        }
        return false;

    }
}
