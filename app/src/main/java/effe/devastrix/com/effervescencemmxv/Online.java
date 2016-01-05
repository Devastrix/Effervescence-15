package effe.devastrix.com.effervescencemmxv;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.SlideInEffect;

import java.util.ArrayList;
import java.util.HashMap;


public class Online extends AppCompatActivity implements  TextWatcher, AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private JazzyListView mList;
    private View shad;
    private EditText  searchContent;

    private ImageView searchIcon, searchClose;

    String[] events_1;
    int[] images;
    String[] timing;
    String[] intents;

    private TextView activityName, intentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature((Window.FEATURE_ACTION_BAR_OVERLAY));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mList = (JazzyListView)findViewById(R.id.listOnl);
        shad = (View) findViewById(R.id.shadow);

        mList.setTransitionEffect(new SlideInEffect());


        mList.addHeaderView(Online.this.getLayoutInflater().inflate(R.layout.listview_header,null));

        //Add events in this array
        events_1 = new String[] {"Perplexus", "Platzen", "Stegolica", "Konqueror"};

        images = new int[] {R.mipmap.c_online,R.mipmap.c_online, R.mipmap.c_online, R.mipmap.c_online, };

        timing = new String[] {"All day","All day","All day","All day"};

        intents = new String[]{"PER", "PLA","STE","KON",
                "PSY","PSY","PSY","PSY",
                "PSY","PSY","PSY","PSY",
                "PSY","PSY","PSY","PSY"};

        initList(events_1, images, timing, intents);


        //search Bar code
        searchIcon = (ImageView) findViewById(R.id.searchIcon);
        searchClose = (ImageView) findViewById(R.id.searchClose);
        searchContent = (EditText) findViewById(R.id.searchContent);
        activityName = (TextView) findViewById(R.id.activity_name);


//        searchContent.getBackground().setColorFilter(getResources().getColor(R.color.whitE), PorterDuff.Mode.SRC_ATOP);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "ankecallig-fg.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");

        activityName.setTypeface(tf2);
        activityName.setText("Online");

        searchIcon.setVisibility(View.VISIBLE);
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search bar becomes visible

                activityName.setVisibility(View.GONE);
                searchContent.setVisibility(View.VISIBLE);
                searchIcon.setVisibility(View.GONE);
                searchClose.setVisibility(View.VISIBLE);

            }
        });

        searchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchContent.setVisibility(View.GONE);
                activityName.setVisibility(View.VISIBLE);
                searchClose.setVisibility(View.GONE);
                searchIcon.setVisibility(View.VISIBLE);
                searchContent.setText("");

                initList(events_1, images, timing, intents);


            }
        });

        // search bar ends





        mList.setOnScrollListener(new AbsListView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if(view.getId() == mList.getId()) {
                    final int currentFirstVisibleItem = mList.getFirstVisiblePosition();

                    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
                        //getSupportActionBar().hide();
                        toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
                        shad.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();

                    }
                    else if(currentFirstVisibleItem < mLastFirstVisibleItem) {
                        // getSupportActionBar().show();
                        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                        shad.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
                    }
                    mLastFirstVisibleItem = currentFirstVisibleItem;
                }
            }
        });

        searchContent.addTextChangedListener(this);
        mList.setOnItemClickListener(this);



    }

    public void initList(String[] eventsArray, int[] imagesList, String[] timingList, String[] intentsList) {
        if(eventsArray.length != 0) {

            ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();

            for(int i = 0; i < eventsArray.length; i++) {
                HashMap<String, String> candy = new HashMap<String, String>();
                candy.put("event", eventsArray[i]);
                candy.put("image", Integer.toString(imagesList[i]));
                candy.put("time", timingList[i]);
                candy.put("intent", "effe.devastrix.com.eventlist."+intentsList[i].trim());
                eventList.add(candy);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Online.this ,
                    eventList,
                    R.layout.list_item,
                    new String[] { "event", "image", "time", "intent" },
                    new int[] { R.id.event_name, R.id.eventImg, R.id.eventTime, R.id.intent }) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    Typeface tf = Typeface.createFromAsset(getAssets(), "MyriadPro-Light.ttf");
                    TextView item_name = (TextView)view.findViewById(R.id.event_name);
                    item_name.setTypeface(tf);

                    return view;
                }
            };

            mList.setAdapter(adapter);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_online, menu);
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
        }else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        String searchItem = s.toString().trim().toLowerCase();
        int index = 0;



        for(int  i = 0; i < events_1.length; i++) {
            if((events_1[i].toLowerCase()).contains(searchItem)) {

                index++;
            }
        }

        String[] events2 = new String[index];
        String[] timing2 =  new String[index];
        String[] intents2  = new String[index];
        int[] images2 =  new int[index];
        index = 0;


        for(int  i = 0; i < events_1.length; i++) {
            if((events_1[i].toLowerCase()).contains(searchItem)) {
                Log.d("search ", events_1[i]);

                events2[index] = events_1[i];
                timing2[index] = timing[i];
                images2[index] = images[i];
                intents2[index] = intents[i];
                index++;
            }
        }
        Log.d("index ", ""+index);
        mList.setAdapter(null);

        initList(events2, images2, timing2, intents2);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView  getIntentName = (TextView) view.findViewById(R.id.intent);
        String intentToOpen = getIntentName.getText().toString();

        Intent i = new Intent(intentToOpen);
        i.putExtra("INTENT", intentToOpen);
        startActivity(i);

    }
}
