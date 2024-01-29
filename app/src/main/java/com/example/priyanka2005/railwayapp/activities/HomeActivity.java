package com.example.priyanka2005.railwayapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;




import com.example.priyanka2005.railwayapp.R;
import com.example.priyanka2005.railwayapp.fragments_nav.ConstuctionsFragment;
import com.example.priyanka2005.railwayapp.fragments_nav.DivisionFragment;
import com.example.priyanka2005.railwayapp.fragments_nav.HeadquartersFragment;
import com.example.priyanka2005.railwayapp.fragments_nav.HomeFragment;
import com.example.priyanka2005.railwayapp.fragments_nav.WatcFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{

         super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_live:
                collapsingToolbarLayout.setTitle("Live");
                Intent intent =new Intent(getApplicationContext(),Live.class);
                startActivity(intent);
                finish();
                break;
            case R.id.nav_home:
                collapsingToolbarLayout.setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;

            case R.id.nav_headquarters:
                collapsingToolbarLayout.setTitle("Headquarters");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HeadquartersFragment()).commit();
                break;
            case R.id.nav_division:
                collapsingToolbarLayout.setTitle("Division");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DivisionFragment()).commit();
                break;
            case R.id.nav_watc:
                collapsingToolbarLayout.setTitle("Workshops and Training");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WatcFragment()).commit();
                break;
            case R.id.nav_constructions:
                collapsingToolbarLayout.setTitle("Constructions");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ConstuctionsFragment()).commit();
                break;
            case R.id.nav_about:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Intent shareIntent =new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("*/*");
                shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                Uri uri =Uri.parse("/data/apps/"+getApplicationContext().getPackageName()+".apk");
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(shareIntent,"Share via"));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
