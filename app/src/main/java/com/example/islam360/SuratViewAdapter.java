package com.example.islam360;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SuratViewAdapter extends ArrayAdapter<SuratView> {
    public SuratViewAdapter(@NonNull Context context, ArrayList<SuratView> arrayList) {
        super(context,0,  arrayList);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.quran_item, parent, false);
        }

        SuratView suratView = getItem(position);

        TextView id = view.findViewById(R.id.txtid);
        id.setText(String.valueOf(suratView.getId()));

        TextView surat = view.findViewById(R.id.txtsurat);
        surat.setText(suratView.getSurat());

        TextView noayat = view.findViewById(R.id.txtnoayat);
        noayat.setText(suratView.getNumayat());

        LinearLayout liitem = (LinearLayout) view.findViewById(R.id.li_item);
        liitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoread = new Intent(getContext(), QuranReadingActivity.class);
                gotoread.putExtra("suratid", suratView.getId());
                gotoread.putExtra("suratname", suratView.getSurat());
                getContext().startActivity(gotoread);
            }
        });

        return view;
    }
}
