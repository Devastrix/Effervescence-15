package effe.devastrix.com.effervescencemmxv;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Parse.ParseUtils;
import VolleyFiles.CustomJSONObjectRequest;
import VolleyFiles.CustomVolleyRequestQueue;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener, Response.ErrorListener {


    int pressed = 0;
    private Toolbar toolbarMain;
    private TextView activityName;
    ImageView abtus;
    private FloatingActionButton Fab;
    private ScrollView abtsc;
    int abtOpened = 0;
    LinearLayout mRevealView, mRevealView1, facebook, youtube, effex;
    private boolean hidden, hidden1;
    SupportAnimator animator, anim1;
    SupportAnimator animator_reverse, anim_rev1;
    RelativeLayout mBack;
    ImageView effelogo;
    private Button register;
    private TextView username, email, contact, pass, repass;
    private ProgressDialog progress;
    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarMain = (Toolbar) findViewById(R.id.app_barMain);
        activityName = (TextView) findViewById(R.id.activity_name);
      //  Fab = (FloatingActionButton) findViewById(R.id.fab);
        abtsc = (ScrollView) findViewById(R.id.abtscroll);
        abtus = (ImageView) findViewById(R.id.abtclick);
        TextView textview1 = (TextView) findViewById(R.id.effeText);
        mBack = (RelativeLayout) findViewById(R.id.mainBack);
        mBack.setOnClickListener(this);
        effelogo = (ImageView) findViewById(R.id.effelogo);
        register = (Button) findViewById(R.id.register);
        //  email = (TextView) findViewById(R.id.email);
        // username = (TextView) findViewById(R.id.username);
        // contact = (TextView) findViewById(R.id.contact);
        // pass = (TextView) findViewById(R.id.password);
        //repass = (TextView) findViewById(R.id.repassword);
        facebook = (LinearLayout) findViewById(R.id.facebook);
        youtube = (LinearLayout) findViewById(R.id.youtube);
        effex = (LinearLayout) findViewById(R.id.effe);
        ImageButton fbbt = (ImageButton) findViewById(R.id.fbbt);
        ImageButton ubt = (ImageButton) findViewById(R.id.youtubebt);
        ImageButton effebt = (ImageButton) findViewById(R.id.effebt);

        fbbt.setOnClickListener(this);
        ubt.setOnClickListener(this);
        effebt.setOnClickListener(this);

        facebook.setOnClickListener(this);
        youtube.setOnClickListener(this);
        effex.setOnClickListener(this);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "OneDirection.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Bold.otf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "Arsenal-Regular.otf");
        textview1.setTypeface(tf3);

       // TextView regHead = (TextView) findViewById(R.id.regHeader);
       // regHead.setTypeface(tf2);
        activityName.setTypeface(tf2);
        activityName.setText("Effervescence'15");
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // toolbarMain.getBackground().setAlpha(0);
        //toolbarMain.setBackgroundColor();

        //reveal effect
        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        mRevealView.setVisibility(View.INVISIBLE);
        hidden = true;

//        mRevealView1 = (LinearLayout) findViewById(R.id.reveal_items2);
//        mRevealView1.setVisibility(View.INVISIBLE);
//        hidden1 = true;


        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setup(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbarMain);

      //  Fab.setOnClickListener(this);
        abtus.setOnClickListener(this);
     //   register.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "effe.devastrix.com.effervescencemmxv")));
            return true;
        } else if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_developers) {

            Intent i = new Intent("effe.devastrix.com.effervescencemmxv.DEV");
            startActivity(i);
//            AlertDialog.Builder builder =
//                    new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
//            LayoutInflater inflater = LayoutInflater.from(this);
//            View view = inflater.inflate(R.layout.credits, null);
//            builder.setView(view);
//
//            builder.setPositiveButton("CLOSE", null);
//
//            builder.show();
            return true;
        } else if (id == R.id.action_clip) {
            int cx = (mRevealView.getLeft() + mRevealView.getRight());
            int cy = mRevealView.getTop();
            int radius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());
            animator =
                    ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, 0, radius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(200);
            animator_reverse = animator.reverse();
            if (hidden) {
                mRevealView.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            } else {
                animator_reverse.addListener(new SupportAnimator.AnimatorListener() {

                    @Override
                    public void onAnimationStart() {

                    }

                    @Override
                    public void onAnimationEnd() {
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;
                    }

                    @Override
                    public void onAnimationCancel() {

                    }

                    @Override
                    public void onAnimationRepeat() {

                    }
                    // ...
                });
                animator_reverse.start();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!hidden) {


            animator_reverse.addListener(new SupportAnimator.AnimatorListener() {

                @Override
                public void onAnimationStart() {

                }

                @Override
                public void onAnimationEnd() {
                    mRevealView.setVisibility(View.INVISIBLE);
                    hidden = true;
                }

                @Override
                public void onAnimationCancel() {

                }

                @Override
                public void onAnimationRepeat() {

                }
                // ...
            });
            animator_reverse.start();
            pressed = 0;

        } else if (pressed == 0) {
            Toast.makeText(MainActivity.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            pressed = 1;
        } else if (pressed == 1) {
            pressed = 0;
            super.onBackPressed();
        }


    }


    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbbt:
            case R.id.facebook:
                //  Toast.makeText(this, "yayyy", Toast.LENGTH_SHORT).show();

                String url = "https://www.facebook.com/effervescence.iiita?fref=ts";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.youtubebt:
            case R.id.youtube:
                // Toast.makeText(this, "yayyy", Toast.LENGTH_SHORT).show();

                String url1 = "https://www.youtube.com/user/effervescenceMM13";
                Intent i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url1));
                startActivity(i1);
                break;
            case R.id.effebt:
            case R.id.effe:
                // Toast.makeText(this, "yayyy", Toast.LENGTH_SHORT).show();
                String url2 = "https://effervescence.iiita.ac.in/";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2));
                startActivity(i2);
                break;
            case R.id.register:
                // register
             /*   String un = username.getText().toString().trim();
                String em = email.getText().toString().trim();
                String con = contact.getText().toString().trim();
                String ps = pass.getText().toString().trim();
                String rps = repass.getText().toString().trim();

                if ((!un.equals("") && !em.equals("") && !con.equals("") && !ps.equals("") && !rps.equals(""))) {
                    // all fields are filled
                    if (ps.equals(rps)) {

                        if (isEmailValid(em)) {
                            //register
                            registerMe(un, em, con, ps,rps);
                            Log.d("register", un);
                        } else {
                            Toast.makeText(this, "Email id invalid!", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "All fields are compulsory", Toast.LENGTH_SHORT).show();
                }
*/
                break;
            case R.id.mainBack:
                //close  popups
                if (!hidden) {
                    animator_reverse.addListener(new SupportAnimator.AnimatorListener() {

                        @Override
                        public void onAnimationStart() {

                        }

                        @Override
                        public void onAnimationEnd() {
                            mRevealView.setVisibility(View.INVISIBLE);
                            hidden = true;
                        }

                        @Override
                        public void onAnimationCancel() {

                        }

                        @Override
                        public void onAnimationRepeat() {

                        }
                        // ...
                    });
                    animator_reverse.start();
                }
                break;
            case R.id.abtclick:
                if (abtOpened == 0) {
                    //open abt us
                    abtOpened = 1;
                    Animation bottomUp = AnimationUtils.loadAnimation(this,
                            R.anim.bottom_up);

                    abtsc.startAnimation(bottomUp);
                    abtsc.setVisibility(View.VISIBLE);
                    abtus.setImageResource(R.mipmap.closecross);
                    effelogo.setVisibility(View.GONE);
                } else {
                    // close abt us

                    abtOpened = 0;
                    Animation bottomUp = AnimationUtils.loadAnimation(this,
                            R.anim.bottom_down);

                    abtsc.startAnimation(bottomUp);
                    abtsc.setVisibility(View.GONE);
                    abtus.setImageResource(R.mipmap.about);
                    effelogo.setVisibility(View.VISIBLE);
                }

                break;

        }


    }

    private void registerMe(String un, String em, String con, String ps, String cps) {

        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();

        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        String url = "https://effervescence.iiita.ac.in/register1/models/register.php";
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                new JSONObject(), this, this);
        jsonRequest.setPar(un, em, con, ps, cps);

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
        Toast.makeText(MainActivity.this, "Check your connection", Toast.LENGTH_SHORT).show();
        progress.dismiss();
    }


    @Override
    public void onResponse(Object response) {
        //  mTextView.setText("Response is: " + response);
        try {
            int success;
            success = Integer.parseInt(((JSONObject) response).getJSONObject("success").toString());
            String msg = null;
            msg = ((JSONObject) response).getJSONObject("message").toString();


            if (success == 1) {
                // success
                Toast.makeText(MainActivity.this, "Check your email for confirmation.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            progress.dismiss();
            e.printStackTrace();
        }
    }


}
