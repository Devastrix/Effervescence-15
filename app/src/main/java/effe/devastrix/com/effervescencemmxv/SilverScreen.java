package effe.devastrix.com.effervescencemmxv;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twotoasters.jazzylistview.JazzyListView;
import com.twotoasters.jazzylistview.effects.SlideInEffect;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import DBManager.DBFavs;
import DBManager.DBSilver;
import VolleyFiles.CustomJSONObjectRequest;
import VolleyFiles.CustomVolleyRequestQueue;


public class SilverScreen extends AppCompatActivity implements Response.Listener, Response.ErrorListener, AdapterView.OnItemClickListener {

    static Toolbar toolbar;

    static ViewPager vp;
    static SlidingTabLayout st;
    private TextView activityName;
    private CollapsingToolbarLayout collapsingToolbar;

    public static final String REQUEST_TAG = "MainVolleyActivity";
    // private TextView mTextView;
    // private Button mButton;
    private RequestQueue mQueue;
    private JazzyListView mList;
    private View shad;
    ProgressDialog progress;
    Context con;
    // ImageView movImag;
    private ImageView movImage;
    String[] mImage;
    // private AbstractList mQueue;
    //  private CharSequence EVENT_NAME = "Events By Day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silver_screen);
//        getWindow().requestFeature((Window.FEATURE_ACTION_BAR_OVERLAY));
        // overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        con = getBaseContext();
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        activityName = (TextView) findViewById(R.id.activity_name);
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        activityName.setTypeface(tf2);
        activityName.setText("Silver Screen");

        //collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbar.setTitle("");
        // toolbar.setTitle("Silver Screen");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mList = (JazzyListView) findViewById(R.id.listSilver);
        shad = (View) findViewById(R.id.shadow);

        mList.setTransitionEffect(new SlideInEffect());


        //  mList.addHeaderView(SilverScreen.this.getLayoutInflater().inflate(R.layout.listview_header, null));


        // activityName = (TextView) findViewById(R.id.activity_name);


        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);
        // swipeView.setEnabled(false);
        swipeView.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        downloadjson();
                    }
                }, 5000);
            }
        });
//

        // setting list from db
        DBSilver dbs = new DBSilver(this);

        dbs.openandwrite();
        Object[] obj = new Object[2];
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<String> times = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> imdbs = new ArrayList<String>();
        ArrayList<String> syn = new ArrayList<String>();
        obj = dbs.getData(0);
        movies = (ArrayList<String>) obj[0];
        times = (ArrayList<String>) obj[1];
        dates = (ArrayList<String>) obj[2];
        imdbs = (ArrayList<String>) obj[3];
        syn = (ArrayList<String>) obj[4];
        dbs.close();

        ArrayList<HashMap<String, String>> movielisty = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < movies.size(); i++) {
            HashMap<String, String> candy1 = new HashMap<String, String>();
            candy1.put("movie", movies.get(i).trim());
            // candy.put("image", Integer.toString(imagesList[i]));
            candy1.put("time", times.get(i).trim());
            candy1.put("date", dates.get(i).trim());
            candy1.put("imdb", imdbs.get(i).trim());
            candy1.put("synopsis", syn.get(i).trim());

            movielisty.add(candy1);
        }


        ListAdapter adapter1 = new SimpleAdapter(
                SilverScreen.this,
                movielisty,
                R.layout.movie_item,
                new String[]{"movie", "time", "date", "imdb", "synopsis"},
                new int[]{R.id.movieName, R.id.movieTime, R.id.movieDate, R.id.imdb, R.id.syn}) {

        };
        mList.setAdapter(adapter1);


        // downloading movies
        downloadjson();
        mList.setOnItemClickListener(this);

        // mList.setAdapter(null);
    }
    //volley part
    public void downloadjson() {
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        String url = "http://sanyam.host56.com/Effemmxv/Silverscreen.json";
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                .GET, url,
                new JSONObject(), this, this);
        jsonRequest.setTag(REQUEST_TAG);
        int socketTimeout = 5000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonRequest.setRetryPolicy(policy);
        mQueue.add(jsonRequest);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Volley error :", error.getMessage() + "!");
                    AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.credits, null);
            builder.setView(view);

            builder.setPositiveButton("CLOSE", null);

            builder.show();
        progress.dismiss();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onResponse(Object response) {
        //  mTextView.setText("Response is: " + response);
        try {
            JSONObject days = null;
            days = ((JSONObject) response).getJSONObject("Movies");


            String[] movie = days.getString("name").split("#");
            String[] mTime = days.getString("time").split("#");
            String[] mDate = days.getString("date").split("#");
            mImage = days.getString("image").split("#");
            String[] mImdb = days.getString("imdb").split("#");
            String[] synopsis = days.getString("synopsis").split("#");
            DBSilver entry = new DBSilver(this);
            // refresh table
            entry.openandwrite();
            entry.refreshTable();
            entry.close();
            DBSilver obj =  new DBSilver(this);
            // enter new data
            for(int j = 0; j < movie.length; j++) {

                obj.openandwrite();
                obj.createEntry(movie[j], mTime[j], mDate[j], mImdb[j], synopsis[j]);
                obj.close();
            }


            Log.d("imag size = ", String.valueOf(mImage.length));

            // set the adapter
            ArrayList<HashMap<String, String>> movieList = new ArrayList<HashMap<String, String>>();

            for (int i = 0; i < movie.length; i++) {
                HashMap<String, String> candy = new HashMap<String, String>();
                candy.put("movie", movie[i].trim());
                // candy.put("image", Integer.toString(imagesList[i]));
                candy.put("time", mTime[i].trim());
                candy.put("date", mDate[i].trim());
                candy.put("imdb", mImdb[i].trim());
                candy.put("synopsis", synopsis[i].trim());

                movieList.add(candy);
            }


            ListAdapter adapter = new SimpleAdapter(
                    SilverScreen.this,
                    movieList,
                    R.layout.movie_item,
                    new String[]{"movie", "time", "date", "imdb", "synopsis"},
                    new int[]{R.id.movieName, R.id.movieTime, R.id.movieDate, R.id.imdb, R.id.syn}) {

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    ImageView movImage1 = (ImageView) view.findViewById(R.id.movieImg);
                    Log.d("image :" + position + " = ", mImage[position]);

                    String u = "http://sanyam.host56.com/Effemmxv/images/" + mImage[position].trim();
                    Log.d("url = ", u);
                    Picasso.with(con)
                            .load(u)
                            .fit()
                            .into(movImage1);


                    return view;
                }


            };
            mList.setAdapter(adapter);
            progress.dismiss();

        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_silver_screen, menu);
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
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView syn = (TextView)view.findViewById(R.id.syn);
        String synopsis = syn.getText().toString().trim();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyCustomTheme);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.rules, null);
        TextView synop = (TextView)view1.findViewById(R.id.matter);
        TextView head = (TextView)view1.findViewById(R.id.alertTitle);
        synop.setText(synopsis);
        head.setText("Synopsis");

        builder.setView(view1);

        builder.setPositiveButton("CLOSE", null);

        builder.show();

    }
}
