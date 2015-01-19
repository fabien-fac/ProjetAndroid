package projet.m2dl.com.projetandroid.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import projet.m2dl.com.projetandroid.R;
import projet.m2dl.com.projetandroid.classes.Picture;

public class SendActivity extends ActionBarActivity {

    private EditText emailText;
    private EditText commentaireText;
    private Picture picture;
    private RadioButton radioButtonEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        emailText = (EditText) findViewById(R.id.et_email);
        commentaireText = (EditText) findViewById(R.id.et_commentaire);
        radioButtonEmail = (RadioButton) findViewById(R.id.radioButtonEmail);
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

        // TODO : récuperer la picture de l'activity précédente
        picture = new Picture();

        String emailDestinaire = emailText.getText().toString();
        String commentaire = commentaireText.getText().toString();

        picture.setDestinataire(emailDestinaire);
        picture.setCommentaire(commentaire);

        if(radioButtonEmail.isChecked()){
            sendPictureByEmail(picture);
        }
    }

    public void sendPictureByEmail(Picture _picture){

        String subject = "Picture";
        String message = String.valueOf(picture.getLatitude());
        //String toCc = "email de destinataire en CC";
        //String toCci = "email de destinataire en CCi";
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ picture.getDestinataire()});
        //email.putExtra(Intent.EXTRA_CC, new String[]{ toCc});
        //email.putExtra(Intent.EXTRA_BCC, new String[]{toCci});
        //email.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/file.pdf");
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choisissez un client de messagerie:"));
    }
}
