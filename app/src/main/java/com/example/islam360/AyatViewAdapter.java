package com.example.islam360;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AyatViewAdapter extends RecyclerView.Adapter<AyatViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList ayat_id, surat_id, para_id, ayat_num, ayat_arabic, ayat_translation;
    boolean isbookmark;

    public AyatViewAdapter(Context context, ArrayList ayat_id, ArrayList surat_id, ArrayList para_id, ArrayList ayat_num, ArrayList ayat_arabic, ArrayList ayat_translation) {
        this.context = context;
        this.ayat_id = ayat_id;
        this.surat_id = surat_id;
        this.para_id = para_id;
        this.ayat_num = ayat_num;
        this.ayat_arabic = ayat_arabic;
        this.ayat_translation = ayat_translation;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ayatprototype, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.suratid.setText(String.valueOf(surat_id.get(position)));
        holder.ayatindex.setText(String.valueOf(ayat_num.get(position)));
        holder.arabicayat.setText(String.valueOf(ayat_arabic.get(position)));
        holder.translationayat.setText(String.valueOf(ayat_translation.get(position)));

        int ayatid = Integer.valueOf(ayat_id.get(position).toString());
        int suratid = Integer.valueOf(surat_id.get(position).toString());
        int paraid = Integer.valueOf(para_id.get(position).toString());

        isbookmark = holder.db.isbookmark(ayatid, suratid, paraid);

        if (isbookmark == true){
            holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
            holder.bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean delresult = holder.db.deletebookmark(Integer.valueOf(ayat_id.get(holder.getAdapterPosition()).toString()), Integer.valueOf(surat_id.get(holder.getAdapterPosition()).toString()), Integer.valueOf(para_id.get(holder.getAdapterPosition()).toString()));
                    if (delresult == true) {
                        holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                        notifyItemChanged(holder.getAdapterPosition());
                    } else {

                    }
                }
            });
        }
        else if(isbookmark == false){
            holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
            holder.bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean insresult = holder.db.insertbookmark(Integer.valueOf(ayat_id.get(holder.getAdapterPosition()).toString()), Integer.valueOf(surat_id.get(holder.getAdapterPosition()).toString()), Integer.valueOf(para_id.get(holder.getAdapterPosition()).toString()), Integer.valueOf(ayat_num.get(holder.getAdapterPosition()).toString()), String.valueOf(ayat_translation.get(holder.getAdapterPosition())), String.valueOf(ayat_arabic.get(holder.getAdapterPosition())));
                    if (insresult == true) {
                        holder.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_24);
                        notifyItemChanged(holder.getAdapterPosition());
                    } else {

                    }
                }
            });
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoimage = new Intent(context, ImageActivity.class);
                context.startActivity(gotoimage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ayat_num.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        DatabaseAccess db;
        TextView suratid, ayatindex, arabicayat, translationayat;
        ImageView bookmark, image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            suratid = (TextView) itemView.findViewById(R.id.txt_suratid);
            ayatindex = (TextView) itemView.findViewById(R.id.txt_ayatindex);
            arabicayat = (TextView) itemView.findViewById(R.id.txt_arabicayat);
            translationayat = (TextView) itemView.findViewById(R.id.txt_transaltionayat);

            bookmark = (ImageView) itemView.findViewById(R.id.bookmark);
            image = (ImageView) itemView.findViewById(R.id.image);

            db = new DatabaseAccess(itemView.getContext());
        }
    }
}
