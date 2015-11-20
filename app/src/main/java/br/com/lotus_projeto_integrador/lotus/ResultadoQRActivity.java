package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultadoQRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_qr);

        TextView txtResultado = (TextView) findViewById(R.id.txtResultado);
        Intent intent = getIntent();
        txtResultado.setText(intent.getStringExtra("resultado"));
    }
}
