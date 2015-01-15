package projet.m2dl.com.projetandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;


public class PictureActivity extends ActionBarActivity {

    private ImageView imageView;
    private Uri uriImage;
    private String uersername;
    private Double latitude;
    private Double longitude;
    private Double altitude;

    private int ETAT = 0;
    private final int INIT = 0;
    private final int DESC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        initComponents();
        getData();
        setPicture();
    }

    private void initComponents() {
        imageView = (ImageView)findViewById(R.id.picture_imageview);
    }

    private void getData() {
        Intent intent = getIntent();

        uriImage = Uri.parse(intent.getStringExtra("URI"));
        uersername = intent.getStringExtra("Username");
        latitude = intent.getDoubleExtra("latitude", 0.0);
        longitude = intent.getDoubleExtra("longitude", 0.0);
        altitude = intent.getDoubleExtra("altitude", 0.0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_picture, menu);
        return super.onCreateOptionsMenu(menu);
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

    private void setPicture() {
        ContentResolver cr = getContentResolver();
        try {
            Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, uriImage);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void help(View v){
        switch (ETAT){
            case INIT:
                displayDialog("Selectionner le point d'interet avec votre doigt.");

        }
    }

    private void displayDialog(String text){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Aide");
        alertDialog.setMessage(text);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
// here you can add functions
            }
        });
        alertDialog.setIcon(android.R.drawable.ic_menu_help);
        alertDialog.show();
    }
}
