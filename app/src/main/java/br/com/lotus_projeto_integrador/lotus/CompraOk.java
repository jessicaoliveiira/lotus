package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CompraOk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_ok);

        FloatingActionButton fabFinalizar = (FloatingActionButton)findViewById(R.id.fab);
        fabFinalizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CompraOk.this, LotusActivity.class);
                startActivity(i);
            }
        });



    }

}
