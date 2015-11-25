package br.com.lotus_projeto_integrador.lotus;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);


        final EditText campo_endereço = (EditText)findViewById(R.id.CampoEndereco);
        final EditText campo_logradouro = (EditText)findViewById(R.id.CampoLogradouro);
        final EditText campo_numero = (EditText)findViewById(R.id.CampoNumero);
        final EditText campo_CEP = (EditText)findViewById(R.id.CampoCEP);
        final EditText campo_cidade = (EditText)findViewById(R.id.CampoCidade);

        Button BtnSalvarEnd = (Button) findViewById(R.id.BtnSalvarEnd);
        BtnSalvarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    validator.validateNotNull(campo_endereço, "Preencha o campo endereço");
                    validator.validateNotNull(campo_logradouro, "Preencha o campo logradouro");
                    validator.validateNotNull(campo_numero, "Preencha o campo número");
                    validator.validateNotNull(campo_CEP, "Preencha o campo CEP");
                    validator.validateNotNull(campo_cidade, "Preencha o campo Cidade");

                }


        });


       final Button BtnCancelarEnd = (Button) findViewById(R.id.BtnCancelarEnd);

        BtnCancelarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lotus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
