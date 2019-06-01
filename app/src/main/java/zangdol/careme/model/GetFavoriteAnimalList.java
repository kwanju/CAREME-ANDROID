package zangdol.careme.model;

import android.content.Context;

import java.util.ArrayList;

import zangdol.careme.util.DBHelper;

public class GetFavoriteAnimalList {
    private DBHelper dbHelper;

    public GetFavoriteAnimalList(Context context) {
        dbHelper = new DBHelper(context, DBHelper.DBNAME, null, DBHelper.DBVERSION);
    }

    public ArrayList<String> getFavoriteAnimals(){
        return dbHelper.getAllLike();
    }
}
