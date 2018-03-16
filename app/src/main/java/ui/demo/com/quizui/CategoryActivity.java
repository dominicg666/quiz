package ui.demo.com.quizui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ui.demo.com.quizui.Fragment.FragmentCategory;

/**
 * Created by Design-4 on 3/3/2018.
 */

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView = null;
    Toolbar toolbar = null;
    Fragment fragment = null;
    TextView toolbar_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // support.v7
        actionBar.setTitle("");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        initializeFragment(FragmentCategory.class);

        //initialize
       // toolbar_title=(TextView) findViewById(R.id.toolbar_title);
    }
    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //cart topbar


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        } else if (id == R.id.action_cart) {
//            initializeFragment(CartFragment.class);
//            toolbar_title.setText("My Cart");
//            navigationView.getMenu().getItem(2).setChecked(true);
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        if (id == R.id.nav_menu) {
//
//            initializeFragment(MenuFragment.class);
//            toolbar_title.setText("Menu");
//
//        } else if (id == R.id.nav_offer) {
//            initializeFragment(OffersFragment.class);
//            toolbar_title.setText("Offers");
//        } else if (id == R.id.nav_cart) {
//
//            initializeFragment(CartFragment.class);
//            toolbar_title.setText("My Cart");
//
//        } else if (id == R.id.nav_order) {
//            toolbar_title.setText("Orders");
//
//        } else if (id == R.id.nav_settings) {
//            initializeFragment(SettingsFragment.class);
//            toolbar_title.setText("Settings");
//
//        } else if (id == R.id.nav_fav) {
//            initializeFragment(FavoriteFragment.class);
//            toolbar_title.setText("Favorite");
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void initializeFragment(Class fragmentClass){
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
                .replace(R.id.fragment_container, fragment).commit();

    }

}
