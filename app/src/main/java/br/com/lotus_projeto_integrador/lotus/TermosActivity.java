package br.com.lotus_projeto_integrador.lotus;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class TermosActivity extends AppCompatActivity {

    public TextView btnSair;
    public Button btnAceito;
    public CheckBox checkBox;
    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termos);

        prefs = getSharedPreferences("termosConfig", MODE_PRIVATE);

        if (prefs.getBoolean("termosConfig", true)) {
            btnSair = (TextView) findViewById(R.id.btnSair);
            btnAceito = (Button) findViewById(R.id.btnAceito);
            checkBox = (CheckBox) findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        btnAceito.setBackgroundColor(Color.parseColor("#2196f3"));

                    }else {
                        btnAceito.setBackgroundColor(Color.parseColor("#9c9c9c"));
                    }
                }
            });

            btnAceito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()) {
                        btnAceito.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences.Editor prefEditor = prefs.edit();
                                prefEditor.putBoolean("termosConfig", false);
                                prefEditor.apply();

                                Intent intent = new Intent(TermosActivity.this, EnderecoActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        Snackbar.make(view, "Aceite os termos para continuar", Snackbar.LENGTH_LONG).show();
                    }
                }
            });

            btnSair.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {
                finish();
              }
        });
                    } else {
                        Intent intent = new Intent(TermosActivity.this, LotusActivity.class);
                        startActivity(intent);
                        finish();
                    }


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
