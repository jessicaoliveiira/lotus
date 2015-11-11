package br.com.lotus_projeto_integrador.lotus;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Created by jessica on 11/11/15.
 */
public class UsuarioDAO extends DataBase {
    private final String TABLE = "usuario";

    public UsuarioDAO(Context context) {
        super(context);
    }

    public void insert(UsuarioLogin usuario) throws Exception {
        ContentValues values = new ContentValues();

        values.put("usuario", usuario.getUsuario());
        values.put("senha", usuario.getSenha());

        getDatabase().insert(TABLE, null, values);
    }

    public void update(UsuarioLogin usuario) throws Exception {
        ContentValues values = new ContentValues();

        values.put("usuario", usuario.getUsuario());
        values.put("senha", usuario.getSenha());

        getDatabase().update(TABLE, values, "id = ?", new String[] { "" + usuario.getId() });
    }

    public UsuarioLogin findById(Integer id) {

        String sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        String[] selectionArgs = new String[] { "" + id };
        Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        cursor.moveToFirst();

        return montaUsuario(cursor);
    }

    public List<UsuarioLogin> findAll() throws Exception {
        List<UsuarioLogin> retorno = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE;
        Cursor cursor = getDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            retorno.add(montaUsuario(cursor));
            cursor.moveToNext();
        }
        return retorno;
    }

    public UsuarioLogin montaUsuario(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
        String usuario = cursor.getString(cursor.getColumnIndex("usuario"));
        String senha = cursor.getString(cursor.getColumnIndex("senha"));

        return new UsuarioLogin(id, usuario, senha);

    }

    /**
     * @param usuario
     * @param senha
     * @return
     */
    public UsuarioLogin findByLogin(String usuario, String senha) {
        String sql = "SELECT * FROM " + TABLE + " WHERE usuario = ? AND senha = ?";
        String[] selectionArgs = new String[] { usuario, senha };
        Cursor cursor = getDatabase().rawQuery(sql, selectionArgs);
        cursor.moveToFirst();

        return montaUsuario(cursor);
    }
}
