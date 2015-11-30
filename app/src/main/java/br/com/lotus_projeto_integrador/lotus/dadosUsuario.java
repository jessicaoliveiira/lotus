package br.com.lotus_projeto_integrador.lotus;

/**
 * Created by jessica on 30/11/15.
 */
public class dadosUsuario {
    private static dadosUsuario ourInstance = new dadosUsuario();

    private String nomeUsuario;
    private String senhaUsuario;

    public static dadosUsuario getInstance() {
        return ourInstance;
    }

    private dadosUsuario() {
    }
    public void setNomeUsuario(String nomeUsuario) {
        nomeUsuario = nomeUsuario;
    }

    public void setSenhaUsuario(String nomeUsuario) {
        senhaUsuario = senhaUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }


}
