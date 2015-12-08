package br.com.lotus_projeto_integrador.lotus;

import android.app.Application;

/**
 * Created by jessica on 11/11/15.
 */
public class Sessao extends Application {
    private static Sessao ourInstance = new Sessao();

    private String nome;


    public static Sessao getInstance() {
        return ourInstance;
    }

    private Sessao() {
    }

    public void setnome(String nome) {
        nome = nome;
    }



    }
