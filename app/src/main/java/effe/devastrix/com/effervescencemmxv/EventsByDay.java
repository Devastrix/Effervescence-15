package effe.devastrix.com.effervescencemmxv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class EventsByDay extends ActionBarActivity {

    static Toolbar toolbar;

    static ViewPager vp;
    static SlidingTabLayout st;
    private TextView activityName;
    private CollapsingToolbarLayout collapsingToolbar;
  //  private CharSequence EVENT_NAME = "Events By Day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_by_day);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("");
        toolbar.setTitle("");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      // activityName = (TextView) findViewById(R.id.activity_name);

        Typeface tf1 = Typeface.createFromAsset(getAssets(),
                "ankecallig-fg.ttf");

     //   activityName.setTypeface(tf1);
       // activityName.setText("Effervescence MMXV");
    //    toolbar.setTitle("Effervescence MMXV");


        vp = (ViewPager) findViewById(R.id.viewpager);
        st = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        vp.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), EventsByDay.this));
//
//        int h = 0;
//        TypedValue tv = new TypedValue();
//        if(getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//           h = TypedValue.complexToDimensionPixelOffset(tv.data, getResources().getDisplayMetrics());
//        }
//
//        Log.d("padding", ""+(h+150) );

       // vp.setPadding(0, h+40, 0, 0);

        st.setDistributeEvenly(true);
        st.setViewPager(vp);

        st.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabSelect);
            }
        });
    }


    // new addition
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }
    //end


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events_by_day, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
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
        else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 4;
        private String tabTitles[] = new String[] { "Day 1", "Day 2", "Day 3","Day 4" };
        private Context context;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1, context);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
    public static class PageFragment extends Fragment {
        public static final String ARG_PAGE = "ARG_PAGE";

        private int mPage;
        static Context c;

        public static PageFragment newInstance(int page, Context con) {
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE, page);
            PageFragment fragment = new PageFragment();
            fragment.setArguments(args);
            c = con;
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }

        public  void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null)
                return;

            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int totalHeight = 0;
            View view = null;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                view = listAdapter.getView(i, view, listView);
                if (i == 0)
                    view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

                view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int a;
            View view;


            if(mPage == 1) {

                //Day 1 List

//                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();


                a = R.layout.fragment_one;
                 view = inflater.inflate(a, container, false);
                final ListView mList = (ListView)view.findViewById(R.id.list1);
              //  mList.setPadding(0, 100, 0 , 0);

                // on click listener for list item
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView  getIntentName = (TextView) view.findViewById(R.id.intent);
                        String intentToOpen = getIntentName.getText().toString();

                        Intent i = new Intent(intentToOpen);
                        i.putExtra("INTENT", intentToOpen);
                        startActivity(i);

                    }
                });

                // sliding toolbar animation
//
//                mList.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    int mLastFirstVisibleItem = 0;
//
//                    @Override
//                    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                        if(view.getId() == mList.getId()) {
//                            final int currentFirstVisibleItem = mList.getFirstVisiblePosition();
//
//                            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
//                                //getSupportActionBar().hide();
//                                st.animate().translationY(-st.getTop()).setInterpolator(new AccelerateInterpolator()).start();
//                                toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
//
//
//                              // vp.animate().translationY().setInterpolator(new AccelerateInterpolator()).start();
//
//
//                            }
//                            else if(currentFirstVisibleItem < mLastFirstVisibleItem) {
//                                // getSupportActionBar().show();
//                                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//
//                              //  vp.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                            }
//                            mLastFirstVisibleItem = currentFirstVisibleItem;
//                        }
//                    }
//                });
//
                // sliding part over

               Day1 obj1 = new Day1(c);

                mList.setAdapter(obj1.getAdapter());
                setListViewHeightBasedOnChildren(mList);

            }
            else if(mPage == 2) {
                // Day 2 List
//
//                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();


                a = R.layout.fragment_two;
                 view = inflater.inflate(a, container, false);

                final ListView mList = (ListView)view.findViewById(R.id.list2);
              //  mList.setPadding(0, 100, 0 , 0);

                // on click listener for list item
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView  getIntentName = (TextView) view.findViewById(R.id.intent);
                        String intentToOpen = getIntentName.getText().toString();

                        Intent i = new Intent(intentToOpen);
                        i.putExtra("INTENT", intentToOpen);
                        startActivity(i);

                    }
                });

                // sliding toolbar animation

//                mList.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    int mLastFirstVisibleItem = 0;
//
//                    @Override
//                    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                        if(view.getId() == mList.getId()) {
//                            final int currentFirstVisibleItem = mList.getFirstVisiblePosition();
//
//                            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
//                                //getSupportActionBar().hide();
//                                st.animate().translationY(-st.getTop()).setInterpolator(new AccelerateInterpolator()).start();
//                                toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
//
//
//                            }
//                            else if(currentFirstVisibleItem < mLastFirstVisibleItem) {
//                                // getSupportActionBar().show();
//                                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//
//                            }
//                            mLastFirstVisibleItem = currentFirstVisibleItem;
//                        }
//                    }
//                });

                // sliding part over
                Day2 obj2 = new Day2(c);

                mList.setAdapter(obj2.getAdapter());
                setListViewHeightBasedOnChildren(mList);

            }
            else {
                // Day 3 List

//                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();


                a = R.layout.fragment_three;
                 view = inflater.inflate(a, container, false);

                final ListView mList = (ListView)view.findViewById(R.id.list3);
              //  mList.addHeaderView(this.getLayoutInflater(savedInstanceState).inflate(R.layout.listview_header,null));
             //   mList.setPadding(0, 100, 0 , 0);

                // on click listener for list item
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView  getIntentName = (TextView) view.findViewById(R.id.intent);
                        String intentToOpen = getIntentName.getText().toString();

                        Intent i = new Intent(intentToOpen);
                        i.putExtra("INTENT", intentToOpen);
                        startActivity(i);

                    }
                });


                // sliding toolbar animation
//
//                mList.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    int mLastFirstVisibleItem = 0;
//
//                    @Override
//                    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                        if(view.getId() == mList.getId()) {
//                            final int currentFirstVisibleItem = mList.getFirstVisiblePosition();
//
//                            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
//                                //getSupportActionBar().hide();
//                                st.animate().translationY(-st.getTop()).setInterpolator(new AccelerateInterpolator()).start();
//                                toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
//
//
//                            }
//                            else if(currentFirstVisibleItem < mLastFirstVisibleItem) {
//                                // getSupportActionBar().show();
//                                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//
//                            }
//                            mLastFirstVisibleItem = currentFirstVisibleItem;
//                        }
//                    }
//                });
//
                // sliding part over
                Day3 obj3 = new Day3(c);

                mList.setAdapter(obj3.getAdapter());
                setListViewHeightBasedOnChildren(mList);
            }
            if(mPage == 4) {

                //Day 4 List

//                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();


                a = R.layout.fragment_four;
                view = inflater.inflate(a, container, false);
                final ListView mList = (ListView)view.findViewById(R.id.list4);
                //  mList.setPadding(0, 100, 0 , 0);

                // on click listener for list item
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView  getIntentName = (TextView) view.findViewById(R.id.intent);
                        String intentToOpen = getIntentName.getText().toString();

                        Intent i = new Intent(intentToOpen);
                        i.putExtra("INTENT", intentToOpen);
                        startActivity(i);

                    }
                });

                // sliding toolbar animation
//
//                mList.setOnScrollListener(new AbsListView.OnScrollListener() {
//                    int mLastFirstVisibleItem = 0;
//
//                    @Override
//                    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//                    }
//
//                    @Override
//                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//                        if(view.getId() == mList.getId()) {
//                            final int currentFirstVisibleItem = mList.getFirstVisiblePosition();
//
//                            if (currentFirstVisibleItem > mLastFirstVisibleItem) {
//                                //getSupportActionBar().hide();
//                                st.animate().translationY(-st.getTop()).setInterpolator(new AccelerateInterpolator()).start();
//                                toolbar.animate().translationY(-toolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
//
//
//                              // vp.animate().translationY().setInterpolator(new AccelerateInterpolator()).start();
//
//
//                            }
//                            else if(currentFirstVisibleItem < mLastFirstVisibleItem) {
//                                // getSupportActionBar().show();
//                                st.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                                toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//
//                              //  vp.animate().translationY(0).setInterpolator(new DecelerateInterpolator()).start();
//                            }
//                            mLastFirstVisibleItem = currentFirstVisibleItem;
//                        }
//                    }
//                });
//
                // sliding part over

                Day4 obj4 = new Day4(c);

                mList.setAdapter(obj4.getAdapter());
                setListViewHeightBasedOnChildren(mList);

            }


//            TextView textView = (TextView) view;
//            textView.setText("Fragment #" + mPage);

            return view;
        }
    }


}
