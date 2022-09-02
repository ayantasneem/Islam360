package com.example.islam360;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity extends AppCompatActivity {
    RecyclerView ry_image;
    ImageView imImgToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imImgToolbar = (ImageView) findViewById(R.id.imgimage_toolbar);
        imImgToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ry_image = (RecyclerView) findViewById(R.id.ry_image);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        ry_image.setLayoutManager(linearLayoutManager);
        imagedata();
    }

    private void imagedata() {
        ImageApiInterface apiInterface = Retrofit.getRetrofit().create(ImageApiInterface.class);
        Call<ImageApiModel> imagedata = apiInterface.getData();
        imagedata.enqueue(new Callback<ImageApiModel>() {
            @Override
            public void onResponse(Call<ImageApiModel> call, Response<ImageApiModel> response) {
                if (response.isSuccessful()){
                    ImageViewAdapter adapter = new ImageViewAdapter(response.body().getData().getPosts());
                    ry_image.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ImageApiModel> call, Throwable t) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ImageActivity.this);
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
            }
        });
    }
}