package com.example.islam360;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuranReadingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> ayatid, suratid, paraid, ayatnum, arabicayat, translationayat;
    DatabaseAccess DB;
    AyatViewAdapter ayatViewAdapter;
    ImageView qureImgToolbar;
    TextView qureTxtToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_reading);

        String sname = getIntent().getExtras().getString("suratname");
        qureTxtToolbar = (TextView) findViewById(R.id.txtquranread_toolbar);
        qureTxtToolbar.setText(sname);
        qureImgToolbar = (ImageView) findViewById(R.id.imgquranread_toolbar);
        qureImgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int sid = getIntent().getExtras().getInt("suratid");
        DB = DatabaseAccess.getInstance(this);
        ayatid = new ArrayList<>();
        suratid = new ArrayList<>();
        paraid = new ArrayList<>();
        ayatnum = new ArrayList<>();
        arabicayat = new ArrayList<>();
        translationayat = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.ry_ayat);
        ayatViewAdapter = new AyatViewAdapter(this, ayatid, suratid, paraid, ayatnum, arabicayat, translationayat);
        recyclerView.setAdapter(ayatViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata(sid);
    }

    private void displaydata(int id) {
        Cursor cursor = DB.getAllAyat(id);
        if (cursor.getCount() == 0){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("No data available");
            dialogBuilder.setCancelable(false);

            dialogBuilder.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            onBackPressed();
                        }
                    });

            AlertDialog alert = dialogBuilder.create();
            alert.show();
            return;
        }
        else{
            while (cursor.moveToNext()){
                ayatid.add(cursor.getString(0));
                arabicayat.add(cursor.getString(1));
                translationayat.add(cursor.getString(2));
                ayatnum.add(cursor.getString(3));
                suratid.add(cursor.getString(4));
                paraid.add(cursor.getString(5));
            }
        }
    }
}