package effe.devastrix.com.effervescencemmxv;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Effervescence extends Activity {

    int mIndex;
    Runnable characterAdder;
    TextView[] let;
    Handler mHandler;
    String str;
    Animation[] animes;
    Animation myFadeInAnimation1, myFadeInAnimation2, myFadeInAnimation3,myFadeInAnimation4,myFadeInAnimation5,myFadeInAnimation6
            ,myFadeInAnimation7,myFadeInAnimation8,myFadeInAnimation9, myFadeInAnimation10,myFadeInAnimation11,myFadeInAnimation12,myFadeInAnimation13;
    Animation myFadeInAnimation15, myFadeInAnimation16, myFadeInAnimation17;
    ImageView image;
    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

           /* final ImageView splashImageView = (ImageView) findViewById(R.id.SplashImageView);
            splashImageView.setBackgroundResource(R.drawable.flag);
            final AnimationDrawable frameAnimation = (AnimationDrawable)splashImageView.getBackground();
            splashImageView.post(new Runnable(){
                @Override
                public void run() {
                    frameAnimation.start();
                }
            });*/


        // final Effervescence sPlashScreen = this;
        Log.d("start", "done");

        final Typeface tf = Typeface.createFromAsset(getAssets(),
                "Capture_it.ttf");

        // The thread to wait for splash screen events
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        wait(5000);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();
            }
        };

        mSplashThread.start();
        image= (ImageView)findViewById(R.id.imagesplash);
        //image.setBackgroundResource(R.mipmap.icon);
        TextView tf1 = (TextView) findViewById(R.id.magicText1);
        tf1.setTypeface(tf);
        TextView tf2 = (TextView) findViewById(R.id.magicText2);
        tf2.setTypeface(tf);
        TextView tf3 = (TextView) findViewById(R.id.magicText3);
        tf3.setTypeface(tf);
        TextView tf4 = (TextView) findViewById(R.id.magicText4);
        tf4.setTypeface(tf);
        TextView tf5 = (TextView) findViewById(R.id.magicText5);
        tf5.setTypeface(tf);
        TextView tf6 = (TextView) findViewById(R.id.magicText6);
        tf6.setTypeface(tf);
        TextView tf7 = (TextView) findViewById(R.id.magicText7);
        tf7.setTypeface(tf);
        TextView tf8 = (TextView) findViewById(R.id.magicText8);
        tf8.setTypeface(tf);
        TextView tf9 = (TextView) findViewById(R.id.magicText9);
        tf9.setTypeface(tf);
        TextView tf10 = (TextView) findViewById(R.id.magicText10);
        tf10.setTypeface(tf);
        TextView tf11 = (TextView) findViewById(R.id.magicText11);
        tf11.setTypeface(tf);
        TextView tf12 = (TextView) findViewById(R.id.magicText12);
        tf12.setTypeface(tf);
        TextView tf13 = (TextView) findViewById(R.id.magicText13);
        tf3.setTypeface(tf);
        TextView tf14 = (TextView) findViewById(R.id.magicText14);
        tf14.setTypeface(tf);
        TextView tf15 = (TextView) findViewById(R.id.magicText15);
        tf15.setTypeface(tf);
        let = new TextView[15];
        animes = new Animation[15];
        let[0] = tf1;
        let[1] = tf2;
        let[2] = tf3;
        let[3] = tf4;
        let[4] = tf5;
        let[5] = tf6;
        let[6] = tf7;
        let[7] = tf8;
        let[8] = tf9;
        let[9] = tf10;
        let[10] = tf11;
        let[11] = tf12;
        let[12] = tf13;
        let[13] = tf14;
        let[14] = tf15;
        mIndex = 0;

        myFadeInAnimation1 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation2 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation3 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation4 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation5 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation6 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation7 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation8 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation9 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation10 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation11 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation12 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation13 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation16 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myFadeInAnimation17 = AnimationUtils.loadAnimation(this, R.anim.fadein);
        animes[0] = myFadeInAnimation1;
        animes[1] = myFadeInAnimation2;
        animes[2] = myFadeInAnimation3;
        animes[3] = myFadeInAnimation4;
        animes[4] = myFadeInAnimation5;
        animes[5] = myFadeInAnimation6;
        animes[6] = myFadeInAnimation7;
        animes[7] = myFadeInAnimation8;
        animes[8] = myFadeInAnimation9;
        animes[9] = myFadeInAnimation10;
        animes[10] = myFadeInAnimation11;
        animes[11] = myFadeInAnimation12;
        animes[12] = myFadeInAnimation13;
        animes[13] = myFadeInAnimation16;
        animes[14] = myFadeInAnimation17;
        myFadeInAnimation15 = AnimationUtils.loadAnimation(this, R.anim.fadein);



        str = "EFFERVESCENCE15";

        mHandler = new Handler();
        characterAdder = new Runnable() {
            @Override
            public void run() {
                let[mIndex].startAnimation(animes[mIndex]);
                if(mIndex == 12) {
                    let[mIndex].setText(str.charAt(mIndex)+"'");
                }
                else {
                    let[mIndex].setText(str.charAt(mIndex) + "");
                }
                let[mIndex++].setVisibility(View.VISIBLE);
                if(mIndex <= 14) {
                    mHandler.postDelayed(characterAdder, 100);
                }
            }
        };

        //  mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, 100);





//        Animation bottomUp = AnimationUtils.loadAnimation(this,
//                R.anim.slide_in);
//        TypeWriter writer = (TypeWriter)findViewById(R.id.magicText);
//        //Add a character every 150ms
//     //   writer.setAnimation(bottomUp);
//        writer.setCharacterDelay(350);
//        writer.animateText("Effervescence MMXV");

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Effervescence.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);

        // for blinker
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                TextView txt = (TextView) findViewById(R.id.motionP);
                txt.setTypeface(tf);

                txt.startAnimation(myFadeInAnimation15);
                txt.setVisibility(View.VISIBLE);
            }
        }, 1500);

    }

//    private void blink(final Context c){
//        final Handler handler = new Handler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int timeToBlink = 1000;    //in milissegunds
//                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        TextView txt = (TextView) findViewById(R.id.motionP);
//                        Animation myFadeInAnimation15 = AnimationUtils.loadAnimation(c, R.anim.fadein);
//                        txt.startAnimation(myFadeInAnimation15);
//                        if(txt.getVisibility() == View.VISIBLE){
//                            txt.setVisibility(View.INVISIBLE);
//                        }else{
//                            txt.setVisibility(View.VISIBLE);
//                        }
//                        blink(c);
//                    }
//                });
//            }
//        }).start();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
