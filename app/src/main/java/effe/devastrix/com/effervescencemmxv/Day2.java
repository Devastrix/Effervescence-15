package effe.devastrix.com.effervescencemmxv;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 7/4/2015.
 */
public class Day2{

    final Context c;

    public Day2(Context con) {
        this.c = con;
    }
    public ListAdapter getAdapter() {
        ArrayList<HashMap<String, String>> candidateList = new ArrayList<HashMap<String, String>>();

        //Add events in this array
        String[] events_1 = {"China Town", "Gully Cricket", "MUN-Security council", "Roadies", "Dumb Charades", "Graffiti", "FIFA", "Split Second"
                ,"Antakshiri", "Bindaas Bol", "Ranneeti", "Street Soccer","Counter Strike","DOTA 2","Dance Charades","Blind Date", "Toon Con","Pillow Fight",
               "Psychedelia","La Frenze","Carpe Diem","Innovation","EDM Night","Perplexus","Konquorer","Photobooth"};

        int[] images = {R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_lit, R.mipmap.c_informal, R.mipmap.c_lit,
                R.mipmap.c_fine, R.mipmap.c_informal, R.mipmap.c_informal,
                R.mipmap.c_music, R.mipmap.c_drama, R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_informal
                , R.mipmap.c_dance,R.mipmap.c_informal, R.mipmap.c_fine, R.mipmap.c_informal, R.mipmap.c_music,
                R.mipmap.c_dance, R.mipmap.c_dance, R.mipmap.c_drama, R.mipmap.c_informal,
                R.mipmap.c_online, R.mipmap.c_online, R.mipmap.c_informal,};

        String[] timing = {"00:00","09:00","09:00","10:00","10:00","11:00","12:00","12:00","12:00","13:00","13:00",
        "15:00","15:00","15:00","15:00","15:00","16:00","16:00","16:30","18:00","20:00","21:00","22:00","00:00",
                "All day","All day","All day"};

        String[] intents = new String[]{"CHI", "GUL", "MUN", "ROA",
                "DUM", "GRA", "GAM", "GAM",
                "ANT", "BIN", "RAN", "STR",
                "GAM", "GAM", "DAN", "BLI",
                "TOO", "PIL", "PSY", "LAF",
                "CAD", "INN", "EDM", "PER",
                "KON", "PHO", "PSY", "PSY",
                "PSY", "PSY", "PSY", "PSY",
                "CHI", "CAD", "FOO", "SHO",
                "DAN", "PSY", "PSY", "PSY",
                "PSY", "PSY", "PSY", "PSY",
                "PSY", "PSY", "PSY", "PSY"};

        for(int i = 0; i < events_1.length; i++) {
            HashMap<String, String> candy = new HashMap<String, String>();
            candy.put("event", events_1[i]);
            candy.put("image", Integer.toString(images[i]));
            candy.put("time", timing[i]);
            candy.put("intent", "effe.devastrix.com.eventlist."+intents[i].trim());
            candidateList.add(candy);
        }

        ListAdapter adapter = new SimpleAdapter(
                c , candidateList,
                R.layout.list_item, new String[] { "event", "image", "time", "intent" }, new int[] { R.id.event_name, R.id.eventImg, R.id.eventTime
                , R.id.intent})
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Typeface tf = Typeface.createFromAsset(c.getAssets(), "MyriadPro-Light.ttf");
                TextView item_name = (TextView)view.findViewById(R.id.event_name);
                item_name.setTypeface(tf);

                return view;
            }
        };

        return adapter;
    }


}
