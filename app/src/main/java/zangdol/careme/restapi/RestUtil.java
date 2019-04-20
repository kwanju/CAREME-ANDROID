package zangdol.careme.restapi;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RestUtil {
    private static RestUtil instance;

    private RestUtil() {
    }

    public static RestUtil getInstance() {
        if (instance == null)
            instance = new RestUtil();
        return instance;
    }

    public interface OnRestApiListener {
        void OnResult(JSONObject result);
    }
    /*
    * 서버에게 request를 보내는 함수.
    *
    * URL : postURL
    * param : 파라메터
    * listener : 끝나고 처리할 리스너.
    *
    * */
    public void request(final String URL, final List<NameValuePair> params, final OnRestApiListener listener) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(URL);
                httpPost.setHeader(HTTP.CONTENT_TYPE,
                        "application/x-www-form-urlencoded;charset=UTF-8");

                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
                    HttpResponse response = httpClient.execute(httpPost);

                    JSONObject result = new JSONObject(EntityUtils.toString(response.getEntity())); // JSON 형태의 결과값.
                    listener.OnResult(result);

                } catch (Exception e) {
                    Log.d("LoginError", "LoginError");
                    e.printStackTrace();
                }
            }
        });
    }

    // JSON object를 MAP 클래스로 변환시켜주는 클래스
    public HashMap<String, String> json2map(JSONObject res) {
        try {
            HashMap<String, String> map = new HashMap<String, String>();

            Iterator<String> iterator = res.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = res.getString(key);
                map.put(key, value);

            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
