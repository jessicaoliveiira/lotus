package br.com.lotus_projeto_integrador.lotus;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Toast;

public class LotusActivity extends ActionBarActivity {
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotus);

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        String[] categoriaArray = {
                "Home",
                "Login",
                "Cadastro",
                "Câmera",
                "Carrinho",
                "Categorias",
                "Pedido",
                "Produto",
                "Sobre",

        };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoriaArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(LotusActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
                onNavigationDrawerItemSelected(position);

            }
        });

}
    public  void onNavigationDrawerItemSelected(int categoriaArray) {
        Log.d("tag", "Mudando fragmento position" + categoriaArray);
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (categoriaArray) {
            case 0:
                fragment = new HomeFragment();
                mActivityTitle = getString(R.string.title_section1);
                break;
            case 1:
                fragment = new LoginFragment2();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 2:
                fragment = new CadastroFragment();
                mActivityTitle = getString(R.string.title_section1);
                break;
            case 3:
                fragment = new CameraFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 4:
                fragment = new CarrinhoFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 5:
                fragment = new CategoriaFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 6:
                fragment = new PedidoFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 7:
                fragment = new ProdutoFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;
            case 8:
                fragment = new SobreFragment();
                mActivityTitle = getString(R.string.title_section2);
                break;


        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
        mDrawerLayout.closeDrawer(mDrawerList);

    }


    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Categorias");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
