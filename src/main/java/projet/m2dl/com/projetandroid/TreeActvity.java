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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import projet.m2dl.com.projetandroid.classes.Leaf;
import projet.m2dl.com.projetandroid.classes.LeafParser;
import projet.m2dl.com.projetandroid.classes.Picture;


public class TreeActvity extends ActionBarActivity {

    private Picture picture;
    private Leaf currentLeaf;
    private Leaf tree;
    private TextView txtKey;
    private ListView listKey;
    private Button btnValidate, btnPrec;
    private int depth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_actvity);
        txtKey = (TextView) findViewById(R.id.txtCurrentKey);

        btnValidate = (Button) findViewById(R.id.btnValider);
        btnValidate.setOnClickListener(nextStep);

        btnPrec = (Button) findViewById(R.id.btnPrec);
        btnPrec.setOnClickListener(prec);

        InputStream inputStream = getResources().openRawResource(R.raw.tree);
        LeafParser parser = new LeafParser();
        tree = parser.parseXmlInputStream(inputStream);
        currentLeaf = tree;

        listKey = (ListView) findViewById(R.id.listKey);

        populateListView();

        listKey.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (depth > 0) {
                    txtKey.append("/");
                }
                txtKey.append(listKey.getItemAtPosition(position).toString());
                depth++;
                System.out.println("Depth :" + depth);
                currentLeaf = (Leaf) listKey.getItemAtPosition(position);
                populateListView();
            }
        });

        Intent intent = getIntent();
        picture = intent.getExtras().getParcelable("picture");
    }

    public View.OnClickListener nextStep = new View.OnClickListener() {
        public void onClick(View arg0) {

        }
    };

    public View.OnClickListener prec = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (depth>0){
                if (depth==1){
                    txtKey.setText("");
                }else{
                    txtKey.setText(txtKey.getText().subSequence(0, txtKey.getText().length() - (currentLeaf.getName().length()+1) ));
                }
                System.out.println("Pere de "+ currentLeaf + " : " + currentLeaf.getFather());
                currentLeaf = currentLeaf.getFather();
                depth--;
                populateListView();
            }
            System.out.println("depth : " + depth);
        }
    };

    public void populateListView(){
        ArrayList<Leaf> listArray = new ArrayList<Leaf>();
        Leaf tmpLeaf = currentLeaf;
        for (Leaf curChild : currentLeaf.getChildren()) {
            listArray.add(curChild);
        }
        ArrayAdapter<Leaf> spinnerArrayAdapter = new ArrayAdapter<Leaf>(this, android.R.layout.simple_spinner_dropdown_item, listArray);
        listKey.setAdapter(spinnerArrayAdapter);
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
