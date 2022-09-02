package com.example.islam360;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btmNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btmNav = (BottomNavigationView) findViewById(R.id.btmNav);
        btmNav.setSelectedItemId(R.id.home);
        HomeFragment home = new HomeFragment();
        FragmentTransaction frt3 = getSupportFragmentManager().beginTransaction();
        frt3.replace(R.id.placeholder, home).commit();


        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.quran:
                        QuranFragment quran = new QuranFragment();
                        FragmentTransaction frt1 = getSupportFragmentManager().beginTransaction();
                        frt1.replace(R.id.placeholder, quran).commit();
                        break;

                    case R.id.hadith:
                        HadithFragment hadith = new HadithFragment();
                        FragmentTransaction frt2 = getSupportFragmentManager().beginTransaction();
                        frt2.replace(R.id.placeholder, hadith).commit();
                        break;

                    case R.id.home:
                        HomeFragment home = new HomeFragment();
                        FragmentTransaction frt3 = getSupportFragmentManager().beginTransaction();
                        frt3.replace(R.id.placeholder, home).commit();
                        break;

                    case R.id.ibadaat:
                        IbadaatFragment ibadaat = new IbadaatFragment();
                        FragmentTransaction frt4 = getSupportFragmentManager().beginTransaction();
                        frt4.replace(R.id.placeholder, ibadaat).commit();
                        break;

                    case R.id.more:
                        MoreFragment more = new MoreFragment();
                        FragmentTransaction frt5 = getSupportFragmentManager().beginTransaction();
                        frt5.replace(R.id.placeholder, more).commit();
                        break;
                }
                return true;
            }
        });
    }
}