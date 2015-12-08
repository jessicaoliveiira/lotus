package br.com.lotus_projeto_integrador.lotus;

/**
 * Created by jessica on 30/11/15.
 */
public class dadosUsuario {
    private static dadosUsuario ourInstance = new dadosUsuario();
    private String Logradouro;
    private String Bairro;
    private String Localidade;
    private String UF;

    public static dadosUsuario getInstance() {
        return ourInstance;
    }

    private dadosUsuario() {
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public void setLocalidade(String localidade) {
        Localidade = localidade;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public String getUF() {
        return UF;
    }


}
