package effe.devastrix.com.effervescencemmxv;

import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class About extends ActionBarActivity {

    private Toolbar toolbar;
    private TextView activityName, aboutus, abtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        activityName = (TextView) findViewById(R.id.activity_name);
        aboutus = (TextView)findViewById(R.id.content);
        abtTitle = (TextView) findViewById(R.id.about);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "ankecallig-fg.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "MyriadPro-Light.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "AirstreamNF.ttf");
        activityName.setTypeface(tf1);
        activityName.setText("Effervescence'15");
        aboutus.setTypeface(tf);
        abtTitle.setTypeface(tf2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
        else if(id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
