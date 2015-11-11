package br.com.lotus_projeto_integrador.lotus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jessica on 11/11/15.
 */
public class DataBase extends SQLiteOpenHelper {


        private final static int VERSAO = 1;
        private final static String NOME = "login.sqlite";
        private static final String CREATE = "CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, usuario VARCHAR( 20 ) NOT NULL, senha VARCHAR( 8 ));";
        protected SQLiteDatabase database;

        public DataBase(Context context) {
            super(context, NOME, null, VERSAO);
        }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDatabase() {
        if (database == null) {
            database = getWritableDatabase();
        }
        return database;
    }
}
