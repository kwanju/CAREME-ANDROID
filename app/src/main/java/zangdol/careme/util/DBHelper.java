package zangdol.careme.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public final static String DBNAME = "CAREME";
    public final static int DBVERSION = 1;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE like(" +
                "idx INTEGER PRIMARY KEY AUTOINCREMENT," +
                "animal_idx TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS like");
        onCreate(db);
    }

    public void insert(String animal_idx) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO like(animal_idx) VALUES('" + animal_idx + "');");
        db.close();
    }

    public Boolean IsExist(String animal_idx) {
        Boolean exist;
        SQLiteDatabase db = getReadableDatabase();
        // DB에 입력한 값으로 행 추가
        Cursor cursor = db.rawQuery("SELECT animal_idx FROM like WHERE animal_idx='" + animal_idx + "'", null);
        if (cursor.moveToNext())
            exist = true;
        else
            exist = false;

        db.close();
        return exist;
    }

    public ArrayList<String> getAllLike() {
        ArrayList<String> likes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        // DB에 입력한 값으로 행 추가
        Cursor cursor = db.rawQuery("SELECT animal_idx FROM like", null);
        while (cursor.moveToNext())
            likes.add(cursor.getString(0));

        db.close();

        return likes;
    }

    public void delete(String animal_idx) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM like WHERE animal_idx='" + animal_idx + "';");
        db.close();
    }
}
