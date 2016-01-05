package effe.devastrix.com.effervescencemmxv;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Contact extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView mList;
    private View shad;
    private EditText searchContent;

    private ImageView searchIcon, searchClose;

    String[] events_1;
    int[] images;
    String[] timing;
    String[] intents;

    private TextView activityName, intentName;
    String[] numbers ;

    int opened = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature((Window.FEATURE_ACTION_BAR_OVERLAY));
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //dont forget to change the list ID
        mList = (ListView)findViewById(R.id.listCont);
        shad = (View) findViewById(R.id.shadow);


        mList.addHeaderView(Contact.this.getLayoutInflater().inflate(R.layout.listview_header,null));

        //Add events in this array
        events_1 = new String[] {"Shobhit Sharma","Mayank Vijay","Rishabh Malhotra","Arushi Goel","Abhishek Menon","Saptak Sengupta",
                "Sanjeev S. Nair","Arpit Goel","Himadri Shah","Gautham Anand","Aayush Garg","Vartul Sharma","Utkarsh Agarwal","Mohit Garg",
                "Ekansh Katiyar","Anirudh Gupta","Aman Mahajan","Parth Parakh","Pranav Pandey",
                "Nishit Gupta","Sahil Hindwani"};

        images = new int[] {R.mipmap.amitabh, R.mipmap.mogambo, R.mipmap.ajay, R.mipmap.karishma, R.mipmap.tech, R.mipmap.shatru,
                R.mipmap.rishi, R.mipmap.pran,R.mipmap.dharam, R.mipmap.maskman, R.mipmap.mithun, R.mipmap.circuit,
                R.mipmap.salman, R.mipmap.raval, R.mipmap.murugan, R.mipmap.paresh,
                R.mipmap.random, R.mipmap.aamir, R.mipmap.gabbar, R.mipmap.anil, R.mipmap.baby
               };

        timing = new String[] {"Overall Coordinator","Overall Coordinator","Head, Events and Management","Head, Finance","Technical Head","Technical Head",
                "Head, Sponsorship","Head, Creatives","Head, Security","Head, Publicity and Relationship","Coordinator, Dance Society",
                "Coordinator, Music Society","Coordinator, Literary Society","Coordinator, Drama Society","Coordinator, Finearts Society",
                "Audio, Lights and Media Committee","Registration Committee",
                "Accomodation and Hospitality Committee","Prize Distribution Committee","T-Shirts Order and Quality Control Committee",
                "Transport Committee"};

        numbers  = new String[]{"9125929064","8081787611","8052942423","9627153198","7752965396","8953346034",
                "8870666551","7379953342","8423180934","8953323016","8176027480","8953822938","8172888611",
                "8419889711","8303789301","9453131334","8953322165","9873445175","8604241015","9887950733",
                "9412262064"

        } ;



        initList(events_1, images, timing, numbers);


        //search Bar code
        searchIcon = (ImageView) findViewById(R.id.searchIcon);
        searchClose = (ImageView) findViewById(R.id.searchClose);
        searchContent = (EditText) findViewById(R.id.searchContent);
        activityName = (TextView) findViewById(R.id.activity_name);


//        searchContent.getBackground().setColorFilter(getResources().getColor(R.color.whitE), PorterDuff.Mode.SRC_ATOP);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");

        activityName.setTypeface(tf1);
        activityName.setText("Contact Us");

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

                initList(events_1, images, timing, numbers);


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
                candy.put("intent", intentsList[i].trim());
                eventList.add(candy);
            }

            ListAdapter adapter = new SimpleAdapter(
                    Contact.this ,
                    eventList,
                    R.layout.contact_list,
                    new String[] { "event", "image", "time", "intent" },
                    new int[] { R.id.event_name, R.id.eventImg, R.id.eventTime, R.id.intent }) {
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    final View view = super.getView(position, convertView, parent);
                    Typeface tf = Typeface.createFromAsset(getAssets(), "MyriadPro-Light.ttf");
                    TextView item_name = (TextView)view.findViewById(R.id.event_name);

                    ImageView call = (ImageView) view.findViewById(R.id.callme);
                    ImageView msg = (ImageView)view.findViewById(R.id.message);
                    ImageView email = (ImageView) view.findViewById(R.id.email);
                    final TextView num = (TextView) view.findViewById(R.id.intent);

                    // calling click listener

                    call.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // call code
                            String number23 = num.getText().toString().trim();
                            Intent callIntent23 = new Intent(Intent.ACTION_CALL);
                            callIntent23.setData(Uri.parse("tel:+91" + number23));
                            //else {
                            startActivity(callIntent23);


                        }
                    });

                    msg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // msg code
                            String nm = num.getText().toString().trim();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                                    + "+91"+nm)));

                        }
                    });

                    email.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // email code
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"effervescence@iiita.ac.in"});
                            try {
                                startActivity(Intent.createChooser(i, "Send mail..."));
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(Contact.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // end listener


//                    ImageView conOptions = (ImageView) view.findViewById(R.id.contactOption);
//                    conOptions.setVisibility(View.VISIBLE);

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
        getMenuInflater().inflate(R.menu.menu_music, menu);
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
                intents2[index] = numbers[i];
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
//        LinearLayout callStuff = (LinearLayout)view.findViewById(R.id.callId);
//        if(callStuff.getVisibility() == View.GONE)
//        callStuff.setVisibility(View.VISIBLE);
//        else {
//            callStuff.setVisibility(View.GONE);
//        }


//        Intent i = new Intent(intentToOpen);
//        startActivity(i);


                LinearLayout callStuff = (LinearLayout)view.findViewById(R.id.callId);
                if(callStuff.getVisibility() == View.GONE) {
                    callStuff.setVisibility(View.VISIBLE);
                }
                else {
                    callStuff.setVisibility(View.GONE);


                }

        Log.d("position clicked = " , ""+position);

    }
}
