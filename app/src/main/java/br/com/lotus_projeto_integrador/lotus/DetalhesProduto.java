package br.com.lotus_projeto_integrador.lotus;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DetalhesProduto extends AppCompatActivity {

    public ImageView produtoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        produtoView = (ImageView)findViewById(R.id.prod_img);

        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.execute("https://entreespelhosecabides.files.wordpress.com/2012/11/desenho_risco_beleza1-g.gif");
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


    public class ImageDownloader extends AsyncTask<String, String, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                InputStream in = new URL("https://entreespelhosecabides.files.wordpress.com/2012/11/desenho_risco_beleza1-g.gif").openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);

                return bitmap;

            } catch (Exception e){

                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            produtoView.setImageBitmap(bitmap);
        }

    }

}
