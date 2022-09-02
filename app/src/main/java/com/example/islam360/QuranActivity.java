package com.example.islam360;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class QuranActivity extends AppCompatActivity {
    ListView liSurat;
    ImageView quImgToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        quImgToolbar = (ImageView) findViewById(R.id.imgquran_toolbar);
        quImgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        liSurat = (ListView) findViewById(R.id.li_surat);
        final ArrayList<SuratView> arrayList = new ArrayList<SuratView>();
        arrayList.add(new SuratView(1, "Al-Fatihah", "7 Ayat"));
        arrayList.add(new SuratView(2, "Al-Baqarah", "286 Ayat"));
        arrayList.add(new SuratView(3, "Aali 'Imran", "200 Ayat"));
        arrayList.add(new SuratView(4, "An-Nisa", "92 Ayat"));
        arrayList.add(new SuratView(5, "Al-Ma'idah", "112 Ayat"));
        arrayList.add(new SuratView(6, "Al-An'am", "55 Ayat"));

        SuratViewAdapter suratViewAdapter = new SuratViewAdapter(this, arrayList);
        liSurat.setAdapter(suratViewAdapter);
    }
}