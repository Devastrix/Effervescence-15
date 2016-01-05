package effe.devastrix.com.effervescencemmxv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
/**
 * Created by user on 02-06-2015.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    Context context;
    List<Informtion> data = Collections.emptyList();

    public MenuAdapter(Context context, List<Informtion> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Informtion current = data.get(i);
        myViewHolder.item.setText(current.Title);
        myViewHolder.icon.setImageResource(current.itemId);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item;
        ImageView icon;

        public MyViewHolder(View itemView, Context c) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.list_msg);
            icon = (ImageView) itemView.findViewById(R.id.lis_icon);
            Typeface tf = Typeface.createFromAsset(c.getAssets(), "MyriadPro-Light.ttf");
            item.setTypeface(tf);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPos = getPosition();
            switch (itemPos) {
//                case 0:
//                    // ABout Us
//                    Intent i0 = new Intent("effe.devastrix.com.effervescencemmxv.ABT");
//                    i0.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(i0);
//
//                    break;
                case 0:
                    //events by category
                    Intent i = new Intent("effe.devastrix.com.effervescencemmxv.EVECAT");
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                    break;
                case 1:
                    // Events By Day
                    Intent i1 = new Intent("effe.devastrix.com.effervescencemmxv.EBD");
                    i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i1);
                    break;
                case 2:
                    Intent i6 = new Intent("effe.devastrix.com.effervescencemmxv.PROSHOW");
                    i6.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i6);

                    break;
                case 3:
                    // Favorites
                    Intent i2 = new Intent("effe.devastrix.com.effervescencemmxv.FAV");
                    i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i2);

                    break;
                case 5:
                    //Contact Us

//                    UserDetails ud = new UserDetails(context);
//                    ud.logout();
                    Intent in = new Intent("effe.devastrix.com.effervescencemmxv.CON");
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    context.startActivity(in);

                    // Log.d("LOGOUT ", "log out ho gya be!");

                    break;
                case 4:
                    //Silver Screen

//                    UserDetails ud = new UserDetails(context);
//                    ud.logout();
                    Intent i4 = new Intent("effe.devastrix.com.effervescencemmxv.SIL");
                    i4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    context.startActivity(i4);
                    //      Toast.makeText(context, "Not decided yet!", Toast.LENGTH_SHORT).show();

                    // Log.d("LOGOUT ", "log out ho gya be!");

                    break;
                case 6:
                    String url = "https://effervescence.iiita.ac.in/sponsors.html";
                    Intent ir = new Intent(Intent.ACTION_VIEW);
                    ir.setData(Uri.parse(url));
                    context.startActivity(ir);
                    break;


            }
        }
    }
}
