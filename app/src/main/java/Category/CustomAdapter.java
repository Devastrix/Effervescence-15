package Category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import effe.devastrix.com.effervescencemmxv.Eventsbycategory;
import effe.devastrix.com.effervescencemmxv.Informtion;
import effe.devastrix.com.effervescencemmxv.R;

/**
 * Created by user on 7/26/2015.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    private final LayoutInflater inflater;
    private Context context;

    // The items to display in your RecyclerView
    private List<Informtion> items= Collections.emptyList();
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView text;
        // You need to retrieve the container (ie the root ViewGroup from your custom_item_layout)
        // It's the view that will be animated
        FrameLayout container;

        public ViewHolder(View itemView)
        {
            super(itemView);
            container = (FrameLayout) itemView.findViewById(R.id.item_layout_container);
            text = (ImageView) itemView.findViewById(R.id.item_layout_image);
        }
    }

    public CustomAdapter(Context context, List<Informtion> item)
    {
        inflater = LayoutInflater.from(context);
        this.items = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Informtion current = items.get(position);
        holder.text.setImageResource(current.itemId);

        // Here you apply the animation when the view is bound
        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated

            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;

    }
}