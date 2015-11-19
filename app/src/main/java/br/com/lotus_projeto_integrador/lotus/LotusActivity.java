package br.com.lotus_projeto_integrador.lotus;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class LotusActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotus);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        frameLayout = (FrameLayout) findViewById(R.id.container);
        navigationView = (NavigationView)findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);

                }else {
                    menuItem.setChecked(true);
                }

                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();

                //noinspection SimplifiableIfStatement

                if (id == R.id.action_home) {

                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;
                }

                if (id == R.id.action_login) {

                    LoginFragment2 loginFragment2 = new LoginFragment2();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, loginFragment2).commit();
                    return true;
                }



                if (id == R.id.action_cadastro) {

                    CadastroFragment cadastroFragment = new CadastroFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, cadastroFragment).commit();
                    return true;
                }

                if (id == R.id.action_carrinho) {

                    Carrinho carrinhoFragment = new Carrinho();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, carrinhoFragment).commit();
                    return true;
                }

                if (id == R.id.action_categoria) {

                    CategoriaFragment categoriaFragment = new CategoriaFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, categoriaFragment).commit();
                    return true;
                }


                if (id == R.id.action_produto) {

                    ProdutoFragment produtoFragment = new ProdutoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, produtoFragment).commit();
                    return true;
                }

                if (id == R.id.action_sobre) {

                    SobreFragment sobreFragment = new SobreFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, sobreFragment).commit();
                    return true;
                }



                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        actionBarDrawerToggle =
                new ActionBarDrawerToggle(
                        LotusActivity.this,
                        drawerLayout,
                        R.string.abre,
                        R.string.fecha);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lotus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Carrinho carrinho = new Carrinho();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, carrinho).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
