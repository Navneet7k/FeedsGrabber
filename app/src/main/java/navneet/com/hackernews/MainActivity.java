package navneet.com.hackernews;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private String isHckEnabled,isIgnEnabled,isEngEnabled,isTechEnabled,isMashEnabled;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isHckEnabled=PreferencesHelper.getInstance(MainActivity.this).getUnencryptedSetting(Constants.HCK_NEWS);
        isEngEnabled=PreferencesHelper.getInstance(MainActivity.this).getUnencryptedSetting(Constants.ENGADGET);
        isIgnEnabled=PreferencesHelper.getInstance(MainActivity.this).getUnencryptedSetting(Constants.IGN);
        isTechEnabled=PreferencesHelper.getInstance(MainActivity.this).getUnencryptedSetting(Constants.TECHCRUNCH);
        isMashEnabled=PreferencesHelper.getInstance(MainActivity.this).getUnencryptedSetting(Constants.MASHABLE);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (isHckEnabled.equals("true")) {
            tabLayout.addTab(tabLayout.newTab().setText("Hacker News"));
        }
        if (isEngEnabled.equals("true")) {
            tabLayout.addTab(tabLayout.newTab().setText("Engadget"));
        }
        if (isIgnEnabled.equals("true")) {
            tabLayout.addTab(tabLayout.newTab().setText("IGN"));
        }
        if (isTechEnabled.equals("true")) {
            tabLayout.addTab(tabLayout.newTab().setText("Techcrunch"));
        }
        if (isMashEnabled.equals("true")) {
            tabLayout.addTab(tabLayout.newTab().setText("Mashable"));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


    /*    swipeRefreshLayout.setOnRefreshListener(newsapi SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getFragmentManager().beginTransaction().replace(R.id.fragment_cont, newsapi HackFragment()).commit();
            }
        });
        */

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (isHckEnabled.equals("true")){
//            tabLayout.addTab(tabLayout.newTab().setText("Hacker News"));
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.menu_main, menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    /*      int id = item.getItemId();
      if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
*/
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return true;
        } else if (i == R.id.category) {
            startActivity(new Intent(this, SelectorActivity.class));
            finish();
            return true;
        }

        else {
//            return super.onOptionsItemSelected(item);
            return false;
        }
    }


}