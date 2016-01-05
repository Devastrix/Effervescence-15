package effe.devastrix.com.effervescencemmxv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

public class Proshows extends AppCompatActivity  {


    int mResources[] = {
            R.mipmap.edm,
            R.mipmap.alamode,
            R.mipmap.incendiary,
            R.mipmap.neha
    };
    String[] inte =  {
            "effe.devastrix.com.eventlist.EDM",
            "effe.devastrix.com.eventlist.ALA",
            "effe.devastrix.com.eventlist.INC",
            "effe.devastrix.com.eventlist.CEL",
    };
    String[] titleArr = {
            "EDM night",
            "A'la Mode",
            "Incendiary",
            "Celebrity Night"
    };
    private ViewPager mViewPager;
    LinearLayout instruct;
    private Toolbar toolbar;
    private TextView activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proshows);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        activityName = (TextView) findViewById(R.id.activity_name);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");

        activityName.setTypeface(tf2);
        activityName.setText("Pro Shows");


        CustomPagerAdapter mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        //instruct = (LinearLayout) findViewById(R.id.instruct);
        //TextView swipe = (TextView) findViewById(R.id.swipe);
       // Button close = (Button)findViewById(R.id.close);

        if(isFirstTime()) {
            // display instructions
     //       instruct.setVisibility(View.VISIBLE);
                        AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.credits, null);
            builder.setView(view);

            builder.setPositiveButton("OK", null);

//            builder.show();

        }
   //     close.setOnClickListener(this);


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }


    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proshows, menu);
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



    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);
            TextView in = (TextView) itemView.findViewById(R.id.proIntent);
            TextView title = (TextView) itemView.findViewById(R.id.proTitle);
            Typeface tf2 = Typeface.createFromAsset(getAssets(),
                    "Arsenal-Bold.otf");
            title.setTypeface(tf2);
            in.setText(inte[position].trim());
            title.setText(titleArr[position]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(inte[position]);
                    startActivity(in);

                }
            });

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
