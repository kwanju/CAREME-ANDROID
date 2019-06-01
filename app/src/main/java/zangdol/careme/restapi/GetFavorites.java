package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetFavorites implements RestUtil.OnRestApiListener {

    private OnGetFavoritesListener listener;
    private String url = Config.SERVERIP + "android/user/json/getFavoriteInfoList";

    public interface OnGetFavoritesListener {
        void onGetFavorites(ArrayList<HashMap<String, String>> favorites);
    }

    public GetFavorites(OnGetFavoritesListener listener, final ArrayList<String> favorites) {
        this.listener = listener;

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return favorites.size();
            }

            @Override
            public void setParams() {
                for (int i = 0; i < favorites.size(); i++)
                    addParam("animal_idx", favorites.get(i));
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetFavorites(json2favorite(result));
    }

    private ArrayList<HashMap<String, String>> json2favorite(JSONObject result) {
        ArrayList<HashMap<String, String>> favorites = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                HashMap<String, String> favorite = new HashMap<>();
                JSONObject favoriteJson = list.getJSONObject(i);

                favorite.put("name", favoriteJson.getString("name"));
                favorite.put("species_code", favoriteJson.getString("species_code"));
                favorite.put("shelterName", favoriteJson.getString("shelterName"));
                favorite.put("idx", favoriteJson.getString("idx"));
                favorite.put("url_picture", favoriteJson.getString("url_picture"));

                favorites.add(favorite);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return favorites;
    }
}
