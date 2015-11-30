package br.com.lotus_projeto_integrador.lotus;

/**
 * Created by jessica on 30/11/15.
 */
public class dadosUsuario {
    private static dadosUsuario ourInstance = new dadosUsuario();

    private String loginUsuario;
    private String senhaUsuario;

    public static dadosUsuario getInstance() {
        return ourInstance;
    }

    private dadosUsuario() {
    }
    public void setLoginUsuario(String loginUsuario) {
        loginUsuario = loginUsuario;
    }

    public void setSenhaUsuario(String nomeUsuario) {
        senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return loginUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }


}
