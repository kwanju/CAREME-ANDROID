package zangdol.careme.restapi.naverMap;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zangdol.careme.restapi.core.Headers;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetAddress implements RestUtil.OnRestApiListener {

    private OnGetAddressListener listener;

    public interface OnGetAddressListener {
        void onGetAddress(String addr);
    }

    public GetAddress(final String latitude, final String longitude, OnGetAddressListener listener) {
        this.listener = listener;

        RestFactory.getInstance().get("https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc",
                new Headers() {
                    @Override
                    public void setHeaders() {
                        addHeader("X-NCP-APIGW-API-KEY-ID", "3055hkch3h");
                        addHeader("X-NCP-APIGW-API-KEY", "7yjvlQQitsGrqq7rVyarBr3U79hpBgPFTd3tmMA1");
                    }
                },
                new Parameters() {
                    @Override
                    public int getNumParams() {
                        return 3;
                    }

                    @Override
                    public void setParams() {
                        addParam("coords", longitude + "," + latitude);
                        addParam("output", "json");
                        addParam("orders", "roadaddr");
                    }
                },
                this);
    }

    @Override
    public void OnResult(JSONObject result) {
        //{ "status": { "code": 0, "name": "ok", "message": "done" }, "results": [{ "name": "roadaddr", "code": { "id": "1114010300", "type": "L", "mappingId": "09140103" }, "region": { "area0": { "name": "kr", "coords": { "center": { "crs": "", "x": 0, "y": 0 } } }, "area1": { "name": "서울특별시", "coords": { "center": { "crs": "EPSG:4326", "x": 126.978388, "y": 37.56661 } }, "alias": "서울" }, "area2": { "name": "중구", "coords": { "center": { "crs": "EPSG:4326", "x": 126.997602, "y": 37.563843 } } }, "area3": { "name": "태평로1가", "coords": { "center": { "crs": "EPSG:4326", "x": 126.977248, "y": 37.567685 } } }, "area4": { "name": "", "coords": { "center": { "crs": "", "x": 0, "y": 0 } } } }, "land": { "type": "", "number1": "110", "number2": "", "addition0": { "type": "building", "value": "서울특별시 청사" }, "addition1": { "type": "zipcode", "value": "04524" }, "addition2": { "type": "roadGroupCode", "value": "111402005001" }, "addition3": { "type": "", "value": "" }, "addition4": { "type": "", "value": "" }, "name": "세종대로", "coords": { "center": { "crs": "", "x": 0, "y": 0 } } } }] }
        Log.d("TEST", result.toString());
        Log.d("TEST",json2roadAddress(result));
        listener.onGetAddress(json2roadAddress(result));
    }

    private String json2roadAddress(JSONObject result) {
        StringBuilder roadaddr = new StringBuilder();

        try {
            JSONArray results = result.getJSONArray("results");
            JSONObject first = results.getJSONObject(0);

            JSONObject region = first.getJSONObject("region");

            JSONObject area1 = region.getJSONObject("area1");
            roadaddr.append(area1.getString("alias")); // ex) 경기, 서울

            JSONObject area2 = region.getJSONObject("area2");
            roadaddr.append(" "+area2.get("name")); // ex) 안산시 상록구, 중구(서울),

            JSONObject land = first.getJSONObject("land");
            roadaddr.append(" "+land.getString("name"));

            if (!land.getString("number1").equals(""))
                roadaddr.append(" "+land.getString("number1"));
            if (!land.getString("number2").equals(""))
                roadaddr.append("-"+land.getString("number2"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return roadaddr.toString();
    }
}
