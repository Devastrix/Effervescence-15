package effe.devastrix.com.effervescencemmxv;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02-06-2015.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    public static final String PREF_FILE_NAME = "test_pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private View containerview;
    private MenuAdapter adapter;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.getBoolean(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,"true"));
        if(savedInstanceState != null) {
            mUserLearnedDrawer = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawerList);
        adapter = new MenuAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Informtion> getData() {
        List<Informtion> data = new ArrayList<>();
        int[] id = { R.mipmap.ic_view_quilt_grey600_48dp, R.mipmap.ic_event_grey600_48dp,R.mipmap.ic_stars_grey600_48dp, R.mipmap.ic_favorite_grey600_48dp, R.mipmap.ic_movie_grey600_48dp,  R.mipmap.ic_call_grey600_48dp, R.mipmap.ic_group_grey600_48dp};
        String[] title = { "Events by category", "Events by day", "Proshows", "Favorites","Silver Screen", "Contact Us", "Sponsors"};
        for(int i = 0; i < title.length && i < id.length; i++) {
            Informtion current  = new Informtion();
            current.itemId = id[i];
            current.Title = title[i];

            data.add(current);
        }
        return data;
    }

    public void setup(int fragmentId,DrawerLayout drawerLayout, Toolbar toolbar) {
        containerview = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Log.d("userlearned ", "" + mUserLearnedDrawer);
                Log.d("fromsaved ", ""+mFromSavedInstanceState);
                // if(!mUserLearnedDrawer) {
                //   mUserLearnedDrawer = true;
                saveToPreferences(getActivity(),KEY_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                //}
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        //  if(!mUserLearnedDrawer && mFromSavedInstanceState) {


        //  mDrawerLayout.openDrawer(containerview);
        // }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }
    public void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName,preferenceValue);
        editor.commit();

    }
    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }
}
