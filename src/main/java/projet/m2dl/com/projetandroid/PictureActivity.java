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
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import projet.m2dl.com.projetandroid.classes.LeafParser;
import projet.m2dl.com.projetandroid.classes.Picture;


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
    private Picture picture;
    private MenuItem btnValidPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        initComponents();
        getData();
        setPicture();

    }

    private void initComponents() {
        imageView = (ImageView) findViewById(R.id.picture_imageview);
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
        getMenuInflater().inflate(R.menu.menu_picture, menu);
        btnValidPosition = menu.findItem(R.id.action_valid);
        btnValidPosition.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_help:
                displayDialog("Selectionner le point d'interet avec votre doigt puis validez votre choix.");
                break;
            case R.id.action_valid:
                processTreeStep();
                break;
        }


        return true;
    }

    private void setPicture() {
        ContentResolver cr = getContentResolver();
        try {
            Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, uriImage);
            imageView.setImageBitmap(bitmap);
            createPicture(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayDialog(String text) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Aide");
        alertDialog.setMessage(text);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setIcon(android.R.drawable.ic_menu_help);
        alertDialog.show();
    }

    private void createPicture(Bitmap bitmap) {
        picture = new Picture(bitmap);

        picture.setAltitude(altitude);
        picture.setLatitude(latitude);
        picture.setLongitude(longitude);
        picture.setDate(new Date());
    }

    public void setCoordinatesInterest(float pos_x, float pos_y){
        picture.setPointInteret_x(pos_x);
        picture.setPointInteret_y(pos_y);
        btnValidPosition.setVisible(true);
    }

    public void processTreeStep(){
        btnValidPosition.setVisible(false);
        InputStream inputStream = getResources().openRawResource(R.raw.tree);

        LeafParser parser = new LeafParser();
        parser.parseXmlInputStream(inputStream);

        //TODO
    }
}
