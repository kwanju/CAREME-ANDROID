package zangdol.careme.restapi;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Headers;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetSpeciesCode implements RestUtil.OnRestApiListener {

    private OnGetSpeciesCodeListener listener;
    private final String URL = Config.SERVERIP + "data/species_code.json";

    public interface OnGetSpeciesCodeListener {
        void onGetSpeciesCode(HashMap<String, String> speciesCodes);
    }

    public GetSpeciesCode(OnGetSpeciesCodeListener listener) {
        this.listener = listener;
        RestFactory.getInstance().get(URL, new Headers() {
            @Override
            public void setHeaders() {

            }
        }, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("hello", "hello");
            }
        }, this);
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetSpeciesCode(json2SpeciesCode(result));
    }

    private HashMap<String, String> json2SpeciesCode(JSONObject result) {
        HashMap<String, String> speciesCode = new HashMap<>();
        try {
            speciesCode.put("0", result.getString("0"));
            speciesCode.put("1", result.getString("1"));
            speciesCode.put("2", result.getString("2"));
            speciesCode.put("3", result.getString("3"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("HASHMAPTEST", speciesCode.toString());
        return speciesCode;
    }
}
