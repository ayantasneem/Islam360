package com.example.islam360;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    public DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db != null){
            this.db.close();
        }
    }

    public Cursor getAllAyat(int suratid){
        db = this.openHelper.getWritableDatabase();
        c = db.rawQuery("Select id, arabic, translation_urdu, ayat_number, surat_id, para_id from tbl_QuranComplete WHERE surat_id="+suratid+"", null);
        return c;
    }

    public boolean insertbookmark(int ayat_id, int surat_id, int para_id, int ayat_num, String ayat_translation, String ayat_arabic){
        db = this.openHelper.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("ayat_id", ayat_id);
        data.put("surat_id", surat_id);
        data.put("para_id", para_id);
        data.put("ayat_number", ayat_num);
        data.put("translation_urdu", ayat_translation);
        data.put("arabic", ayat_arabic);
        long result = db.insert("tbl_Bookmark", null, data);

        if (result > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean deletebookmark(int ayat_id, int surat_id, int para_id){
        db = this.openHelper.getWritableDatabase();
        long delresult = db.delete("tbl_Bookmark", "ayat_id = ? and surat_id = ? and para_id = ?", new String[]{String.valueOf(ayat_id), String.valueOf(surat_id), String.valueOf(para_id)});
        if(delresult > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isbookmark(int ayat_id, int surat_id, int para_id){
        db = this.openHelper.getWritableDatabase();
        Cursor find = db.rawQuery("Select * from tbl_Bookmark where ayat_id = ? and surat_id = ? and para_id = ?", new String[]{String.valueOf(ayat_id), String.valueOf(surat_id), String.valueOf(para_id)});
        if(find.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
