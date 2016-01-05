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
public class Day1 {

    final Context c;

    public Day1(Context con) {
        this.c = con;
    }
    public ListAdapter getAdapter() {
        ArrayList<HashMap<String, String>> candidateList = new ArrayList<HashMap<String, String>>();

        //Add events in this array
        String[] events_1 = {"Prelims-Anime Quiz","Prelims-TV Series Quiz","Prelims-Cricket Quiz","Prelims-Gully Cricket","Prelims-Roadies",
                "Silverscreen","MUN-Secretary Council","Treasure Hunt"};

        int[] images = {R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_informal, R.mipmap.c_informal,
                R.mipmap.c_ams, R.mipmap.c_lit, R.mipmap.c_informal,
                };

        String[] timing = {"18:00","18:00","18:00","18:00","18:00","19:30","20:00","21:00"};
        String[] intents = new String[]{"ANQ", "TVQ", "CRQ", "GUL",
                "ROA","SVR", "MUN", "TRE", "PSY",
                };

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
