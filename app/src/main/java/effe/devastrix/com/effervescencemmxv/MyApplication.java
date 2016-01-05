package effe.devastrix.com.effervescencemmxv;

import android.app.Application;
import android.widget.Toast;

import Parse.ParseUtils;

/**
 * Created by user on 10/1/2015.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
       // ConnectionDetector connection = new ConnectionDetector();
//        try {
//            if (connection.testURL()) {
        // register with parse
        ParseUtils.registerParse(this);
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Check your Connection!", Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }





        //  Toast.makeText(this, "Turn on your internet!", Toast.LENGTH_SHORT).show();
    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
