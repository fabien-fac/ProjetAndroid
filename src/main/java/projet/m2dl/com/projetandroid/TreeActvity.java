package projet.m2dl.com.projetandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import projet.m2dl.com.projetandroid.classes.Leaf;
import projet.m2dl.com.projetandroid.classes.LeafParser;
import projet.m2dl.com.projetandroid.classes.Picture;


public class TreeActvity extends ActionBarActivity {

    private Picture picture;
    private int currentDepth = 0;
    private Leaf currentLeaf;
    private Spinner spinnerTree;
    private Leaf tree;
    private TextView txtKey;
    private Button btnValidate, btnPrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_actvity);

        txtKey = (TextView) findViewById(R.id.txtCurrentKey);

        btnValidate = (Button) findViewById(R.id.btnValider);
        //btnValidate.setOnClickListener();

        btnPrec = (Button) findViewById(R.id.btnPrec);
        //btnPrec.setOnClickListener();

        spinnerTree = (Spinner) findViewById(R.id.spinnerTree);
        spinnerTree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtKey.append(spinnerTree.getSelectedItem().toString());
                currentDepth++;
                //currentLeaf = spinnerTree.getSelectedItem()
                populateSpinnerTree();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();

        InputStream inputStream = getResources().openRawResource(R.raw.tree);
        LeafParser parser = new LeafParser();
        tree = parser.parseXmlInputStream(inputStream);

        //tree.findLeafByName("Chien").getName();

        populateSpinnerTree();

        picture = intent.getExtras().getParcelable("picture");
    }

    public void populateSpinnerTree(){
        ArrayList<String> spinnerArray = new ArrayList<String>();

        Leaf tmpLeaf = tree;
            for (Leaf curChild : tree.getChildren()) {
                spinnerArray.add(curChild.getName());
                System.out.println("leaf : " + curChild.getName());
            }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinnerTree.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tree_actvity, menu);
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
