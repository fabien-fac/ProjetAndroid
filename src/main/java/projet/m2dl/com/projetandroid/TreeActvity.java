package projet.m2dl.com.projetandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.InputStream;
import java.util.ArrayList;

import projet.m2dl.com.projetandroid.classes.Leaf;
import projet.m2dl.com.projetandroid.classes.LeafParser;
import projet.m2dl.com.projetandroid.classes.Picture;


public class TreeActvity extends ActionBarActivity {

    private Picture picture;
    private int currentDepth = 0;
    private Spinner spinnerTree;
    private Leaf tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_actvity);

        spinnerTree = (Spinner) findViewById(R.id.spinnerTree);

        Intent intent = getIntent();

        InputStream inputStream = getResources().openRawResource(R.raw.tree);
        LeafParser parser = new LeafParser();
        tree = parser.parseXmlInputStream(inputStream);

        populateSpinnerTree(0);

       // picture = intent.getExtras().getParcelable("picture");
    }

    public void populateSpinnerTree(int depth){
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
