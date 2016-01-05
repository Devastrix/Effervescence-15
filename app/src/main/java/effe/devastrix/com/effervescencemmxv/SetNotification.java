package effe.devastrix.com.effervescencemmxv;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class SetNotification extends AppCompatActivity {

    TimePicker timePicker;
    DatePicker datePicker;
    Random random = new Random();
    private Toolbar toolbar;
    private TextView activityName;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_notification);
        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityName = (TextView) findViewById(R.id.activity_name);


//        searchContent.getBackground().setColorFilter(getResources().getColor(R.color.whitE), PorterDuff.Mode.SRC_ATOP);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "ankecallig-fg.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "agency.ttf");

        activityName.setTypeface(tf2);
        activityName.setText("Set Reminder");


        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String event = b.getString("event");
        final String address = b.getString("address");
        //---Button view---
        Button btnOpen = (Button) findViewById(R.id.btnSetAlarm);
        btnOpen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                int randomInteger = random.nextInt();
                //randomInteger = randomInteger%100;
                //if(randomInteger < 0)randomInteger = randomInteger + 100;

                //String s = Integer.toString(randomInteger);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                timePicker = (TimePicker) findViewById(R.id.timePicker);
                datePicker = (DatePicker) findViewById(R.id.datePicker);

                //---use the AlarmManager to trigger an alarm---
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                //---get current date and time---
                Calendar calendar = Calendar.getInstance();

                //---sets the time for the alarm to trigger---
                calendar.set(Calendar.YEAR, datePicker.getYear());
                calendar.set(Calendar.MONTH, datePicker.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);

                //---PendingIntent to launch activity when the alarm triggers---
                Intent i = new Intent("effe.devastrix.com.effervescencemmxv.DN");
                i.putExtra("event", event);
                i.putExtra("address", address);
                //---assign an ID of 1---
                i.putExtra("NotifID", randomInteger);

                PendingIntent displayIntent = PendingIntent.getActivity(
                        getBaseContext(), randomInteger, i, PendingIntent.FLAG_ONE_SHOT);

                //---sets the alarm to trigger---
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(), displayIntent);
                Toast.makeText(SetNotification.this, "Reminder set for the event", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_notification, menu);
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
