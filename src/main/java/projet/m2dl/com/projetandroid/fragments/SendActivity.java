package projet.m2dl.com.projetandroid.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.File;

import projet.m2dl.com.projetandroid.MainActivity;
import projet.m2dl.com.projetandroid.R;
import projet.m2dl.com.projetandroid.classes.Picture;

public class SendActivity extends ActionBarActivity {

    private EditText emailText;
    private EditText commentaireText;
    private Picture picture;
    private RadioButton radioButtonEmail;

    private static final int SEND_EMAIL_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        emailText = (EditText) findViewById(R.id.et_email);
        commentaireText = (EditText) findViewById(R.id.et_commentaire);
        radioButtonEmail = (RadioButton) findViewById(R.id.radioButtonEmail);

        Intent intent = getIntent();
        picture = intent.getExtras().getParcelable("picture");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send, menu);
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

    public void send(View view){

        String emailDestinaire = emailText.getText().toString();
        String commentaire = commentaireText.getText().toString();

        picture.setDestinataire(emailDestinaire);
        picture.setCommentaire(commentaire);

        if(radioButtonEmail.isChecked()){
            sendPictureByEmail();
        }
    }

    public void sendPictureByEmail(){
        String subject = "Picture BioPic";
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ picture.getDestinataire()});
        email.setType("application/image");
        email.putExtra(Intent.EXTRA_STREAM, picture.getPictureUri());
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, getEmailContentFromPicture(picture));

        startActivityForResult(Intent.createChooser(email, "Choisissez un client de messagerie:"), SEND_EMAIL_ACTIVITY_REQUEST_CODE);
    }

    private String getEmailContentFromPicture(Picture picture){
        StringBuilder sb = new StringBuilder();

        sb.append("Auteur : ");
        sb.append(picture.getUser());

        sb.append("\n");
        sb.append("\n");

        sb.append("Localisation : \n");

        sb.append("latitude : ");
        sb.append(picture.getLatitude());
        sb.append("\n");

        sb.append("longitude : ");
        sb.append(picture.getLongitude());
        sb.append("\n");

        sb.append("altitude : ");
        sb.append(picture.getAltitude());

        sb.append("\n");
        sb.append("\n");

        sb.append("Date : ");
        sb.append(picture.getDate());

        sb.append("\n");
        sb.append("\n");

        sb.append("Point d'interet : \n");

        sb.append("x : ");
        sb.append(picture.getPointInteret_x());
        sb.append("\n");

        sb.append("y : ");
        sb.append(picture.getPointInteret_y());
        sb.append("\n");

        sb.append("\n");
        sb.append("\n");

        sb.append("Commentaire :\n");
        sb.append(picture.getCommentaire());

        return sb.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SEND_EMAIL_ACTIVITY_REQUEST_CODE) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            this.finish();
        }
    }
}
