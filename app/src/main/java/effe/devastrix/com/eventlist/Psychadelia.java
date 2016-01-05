package effe.devastrix.com.eventlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import DBManager.DBFavs;
import effe.devastrix.com.effervescencemmxv.MenuAdapter;
import effe.devastrix.com.effervescencemmxv.R;


public class Psychadelia extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv;
    private MenuAdapter adapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int mutedColor;
    private ImageView favButton;
    Animation an;
    private SharedPreferences sharedpreferences;
    private String PREF_FILE_NAME = "EFFE";
    private String FAVSTATUS = "psy";
    private String FAVOFF = "0";
    private String FAVON = "1";
    private String intentNaam;
    final static private String EVENT_NAME = "Psychadelia";
    //for organisers
    private TextView num1;
    private LinearLayout org1;
    private TextView num2;
    private LinearLayout org2;
    private TextView num3;
    private LinearLayout org3;
    //   private ScrollView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_psychadelia);
//        rv = (RecyclerView) findViewById(R.id.scrollableview);
//        adapter = new MenuAdapter(this,getData());
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(this));

        // sv = (ScrollView) findViewById(R.id.scrollableview);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(EVENT_NAME);
        ImageView maps = (ImageView) findViewById(R.id.maps);
        ImageView alarm = (ImageView) findViewById(R.id.alarm);
        ImageView header = (ImageView) findViewById(R.id.header);

        favButton = (ImageView) findViewById(R.id.favButton);
        favButton.setOnClickListener(this);
        maps.setOnClickListener(this);
        alarm.setOnClickListener(this);
        an = AnimationUtils.loadAnimation(this, R.anim.interpolate);

        Intent intent = getIntent();
        intentNaam = intent.getStringExtra("INTENT");
        // String name = intent.getStringExtra("name");

        if (getFav().equals("1")) {
            //fav is set

            favButton.setImageResource(R.mipmap.ic_favorite_grey600_36dp);

        } else {
            //fav is not set

            favButton.setImageResource(R.mipmap.ic_favorite_outline_grey600_36dp);

        }

        // organisers area
        num1 = (TextView) findViewById(R.id.number1psy);
        org1 = (LinearLayout) findViewById(R.id.organiser1psy);
        org1.setOnClickListener(this);
        num2 = (TextView) findViewById(R.id.number2psy);
        org2 = (LinearLayout) findViewById(R.id.organiser2psy);
        org2.setOnClickListener(this);
        num3 = (TextView) findViewById(R.id.number3psy);
        org3 = (LinearLayout) findViewById(R.id.organiser3psy);
        org3.setOnClickListener(this);
        // end organisers area

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.navbackg);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                mutedColor = palette.getMutedColor(R.attr.colorPrimary);
//                collapsingToolbar.setContentScrimColor(mutedColor);
//            }
//        });

    }



    public void setFav(String fav) {
        sharedpreferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(FAVSTATUS, fav);
        editor.commit();
    }

    public String getFav() {
        sharedpreferences = getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(FAVSTATUS)) {

            return sharedpreferences.getString(FAVSTATUS, "");

        }
        return "";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_psychadelia, menu);
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
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.rules, null);
            TextView matter = (TextView) view.findViewById(R.id.matter);
            matter.setText(getString(R.string.rules_psychadelia));
            builder.setView(view);

            builder.setPositiveButton("CLOSE", null);

            builder.show();
            return true;
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // organisers click
            case R.id.organiser1psy:
                String number = num1.getText().toString().trim();
                Intent callIntent2 = new Intent(Intent.ACTION_CALL);
                callIntent2.setData(Uri.parse("tel:+91" + number));
                if (!hasPermission("android.permission.CALL_PHONE")) {
                    Toast.makeText(this, "Grant permission for Calling", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    startActivity(callIntent2);
                }
                break;
            case R.id.organiser2psy:
                String number2 = num2.getText().toString().trim();
                Intent callIntent22 = new Intent(Intent.ACTION_CALL);
                callIntent22.setData(Uri.parse("tel:+91" + number2));
                if (!hasPermission("android.permission.CALL_PHONE")) {
                    Toast.makeText(this, "Grant permission for Calling", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    startActivity(callIntent22);
                }
                break;
            case R.id.organiser3psy:
                String number3 = num3.getText().toString().trim();
                Intent callIntent23 = new Intent(Intent.ACTION_CALL);
                callIntent23.setData(Uri.parse("tel:+91" + number3));
                if (!hasPermission("android.permission.CALL_PHONE")) {
                    Toast.makeText(this, "Grant permission for Calling", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    startActivity(callIntent23);
                }
                break;
            // end organisers click

            case R.id.maps:
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                // Creating a criteria object to retrieve provider
                Criteria criteria = new Criteria();

                // Getting the name of the best provider
                String provider = locationManager.getBestProvider(criteria, true);

                // Getting Current Location
                Location location = locationManager.getLastKnownLocation(provider);

                String s = "25.429269, 81.773089";
                if(location!=null){
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    s = "";
                    s = String.format("%f,%f", lat, lon);
                }
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + s + "&daddr=25.429050, 81.771413"));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                break;

            case R.id.alarm:
                String str = "Carpe Diem";
                Intent it = new Intent("effe.devastrix.com.effervescencemmxv.SN");
                it.putExtra("event", EVENT_NAME);
                it.putExtra("address", intentNaam);
                startActivity(it);
                break;
            case R.id.favButton:

                if (getFav().equals("1")) {
                    //fav is set
                    setFav("0");
                    DBFavs entryDel = new DBFavs(this);
                    entryDel.openandwrite();
                    entryDel.deleteTitleGivenName(EVENT_NAME);
                    entryDel.close();
                    favButton.setImageResource(R.mipmap.ic_favorite_outline_grey600_36dp);
                    Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                    //delete db entry
                } else {
                    //fav is not set
                    setFav("1");
                    favButton.setImageResource(R.mipmap.ic_favorite_grey600_36dp);
                    DBFavs entry = new DBFavs(this);
                    entry.openandwrite();
                    entry.createEntry(EVENT_NAME, intentNaam);
                    entry.close();
                    Toast.makeText(this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                    //create db entry
                }
                break;
        }


    }
    public boolean hasPermission(String permission) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    if (p.equals(permission)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // new addition
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }
    //end
}
