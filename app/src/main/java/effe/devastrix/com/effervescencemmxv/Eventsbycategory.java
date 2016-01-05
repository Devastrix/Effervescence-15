package effe.devastrix.com.effervescencemmxv;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import Category.CustomAdapter;


public class Eventsbycategory extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private LinearLayout dance, music, ams, management, literary, informal, online, drama;
    private TextView activityName;
    private RecyclerView rv;
    private CustomAdapter adapter;
    private TextView tda,tmu,tdr,tam,tli,tin,ton,tfi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventsbycategory);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityName = (TextView) findViewById(R.id.activity_name);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "ankecallig-fg.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "timeburner_regular.ttf");

        activityName.setTypeface(tf2);
        activityName.setText("Effervescence'15");

//        rv = (RecyclerView) findViewById(R.id.categoryList);
//        adapter = new CustomAdapter(this,getData());
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new GridLayoutManager(this, 2));
     //   adapter.notifyDataSetChanged();



        dance = (LinearLayout) findViewById(R.id.dance);
        music = (LinearLayout)findViewById(R.id.music);
        ams = (LinearLayout)findViewById(R.id.ams);
        management = (LinearLayout) findViewById(R.id.finearts);
        literary = (LinearLayout) findViewById(R.id.lit);
        informal = (LinearLayout) findViewById(R.id.informals);
        online = (LinearLayout) findViewById(R.id.online);
        drama = (LinearLayout) findViewById(R.id.drama);

        tda = (TextView)findViewById(R.id.tvdance);
        tmu = (TextView)findViewById(R.id.tvmusic);
        tdr = (TextView)findViewById(R.id.tvdrama);
        tam = (TextView)findViewById(R.id.tvphoto);
        tli = (TextView)findViewById(R.id.tvlit);
        tin = (TextView)findViewById(R.id.tvinf);
        ton = (TextView)findViewById(R.id.tvonline);
        tfi = (TextView)findViewById(R.id.tvfine);

        tda.setTypeface(tf3);
        tdr.setTypeface(tf3);
        tmu.setTypeface(tf3);
        tam.setTypeface(tf3);
        tli.setTypeface(tf3);
        tin.setTypeface(tf3);
        ton.setTypeface(tf3);
        tfi.setTypeface(tf3);

        dance.setOnClickListener(this);
        music.setOnClickListener(this);
        ams.setOnClickListener(this);
        management.setOnClickListener(this);
        literary.setOnClickListener(this);
        informal.setOnClickListener(this);
        online.setOnClickListener(this);
        drama.setOnClickListener(this);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation anim3 = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation anim4 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation anim5 = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation anim6 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation anim7 = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation anim8 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        dance.setAnimation(anim);
        music.setAnimation(anim2);
        drama.setAnimation(anim3);
        ams.setAnimation(anim4);
        literary.setAnimation(anim5);
        informal.setAnimation(anim6);
        online.setAnimation(anim7);
        management.setAnimation(anim8);
       // dance.setAnimation(anim);
//        dance.animate().translationX(dance.getBottom()).setInterpolator(new DecelerateInterpolator()).start();
    }


//    public static List<Informtion> getData() {
//        List<Informtion> data = new ArrayList<>();
//        int[] id = {R.mipmap.ic_stars_grey600_48dp, R.mipmap.ic_view_quilt_grey600_48dp,
//                R.mipmap.ic_event_grey600_48dp, R.mipmap.ic_favorite_grey600_48dp, R.mipmap.ic_call_grey600_48dp,
//                R.mipmap.ic_stars_grey600_48dp, R.mipmap.ic_view_quilt_grey600_48dp,
//                R.mipmap.ic_event_grey600_48dp, R.mipmap.ic_favorite_grey600_48dp, R.mipmap.ic_call_grey600_48dp,
//                R.mipmap.ic_stars_grey600_48dp, R.mipmap.ic_view_quilt_grey600_48dp,
//                R.mipmap.ic_event_grey600_48dp, R.mipmap.ic_favorite_grey600_48dp, R.mipmap.ic_call_grey600_48dp,
//                R.mipmap.ic_stars_grey600_48dp, R.mipmap.ic_view_quilt_grey600_48dp,
//                R.mipmap.ic_event_grey600_48dp, R.mipmap.ic_favorite_grey600_48dp, R.mipmap.ic_call_grey600_48dp};
//
//        for(int i = 0; i < id.length; i++) {
//            Informtion current  = new Informtion();
//            current.itemId = id[i];
//
//
//            data.add(current);
//        }
//        return data;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eventsbycategory, menu);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.music:
                Intent i = new Intent("effe.devastrix.com.effervescencemmxv.MUS");
                startActivity(i);

                break;

            case R.id.dance:

                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.DAN"));

                break;

            case R.id.ams:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.AMS"));

                break;

            case R.id.lit:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.LIT"));

                break;
            case R.id.finearts:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.FIN"));

                break;

            case R.id.informals:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.INF"));

                break;

            case R.id.online:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.ONL"));


                break;

            case R.id.drama:
                startActivity( new Intent("effe.devastrix.com.effervescencemmxv.DRA"));

                break;
        }
    }
}
