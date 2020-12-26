package com.example.example8_2;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase myDb;
    private Uri uri= Uri.parse("content://com.example.example8_2.MYPROVIDER");

    public MyDAO(Context context) {
        dbHelper = new DbHelper(context,"test.db",null,1);
        myDb = dbHelper.getWritableDatabase();
    }

    public Uri insertInfo(ContentValues values){
        long rowid=myDb.insert(DbHelper.TB_NAME, null, values);
        if(rowid==-1) {
            Log.i("myDbDemo", "数据插入失败！");
            return null;
        } else{
            Log.i("myDbDemo", "数据插入成功！"+rowid);
            Uri insertUri = ContentUris.withAppendedId(uri,rowid);
            return insertUri;
        }
    }
    public int deleteInfo(Uri uri, String selection, String[] selectionArgs){  //删除记录
        return myDb.delete(DbHelper.TB_NAME,selection, selectionArgs);
    }
    public int updateInfo(Uri uri, ContentValues values, String selection, String[] selectionArgs){  //修改记录
        return myDb.update(DbHelper.TB_NAME, values, selection,selectionArgs);
    }
    public Cursor allQuery(){
        myDb = dbHelper.getReadableDatabase();
        return myDb.rawQuery("select * from friends",null);
    }
}