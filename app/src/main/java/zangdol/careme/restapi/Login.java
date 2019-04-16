package zangdol.careme.restapi;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.RestAPIListener;

public class Login {
    private RestAPIListener listener;
    private String URL = Config.SERVERIP + "android/user/action/login"; // 로그인 POST URL

    public Login() {

    }

    public boolean login(String id, String pw, final RestAPIListener listener) {
        this.listener = listener;
        final String input_id = id;
        final String input_pw = pw;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(URL);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

                // Parameter들 첨부
                nameValuePairs.add(new BasicNameValuePair("id", input_id));
                nameValuePairs.add(new BasicNameValuePair("pw", input_pw));
                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);

                    JSONObject result = new JSONObject(EntityUtils.toString(response.getEntity())); // JSON 형태의 결과값.

                    listener.onResponse(json2map(result)); // 결과를 받았으니 subscriber에게 알려줌.

                } catch (Exception e) {
                    Log.d("LoginError", "LoginError");
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    // JSON object를 MAP 클래스로 변환시켜주는 클래스
    public HashMap<String, String> json2map(JSONObject res) throws JSONException {
        HashMap<String, String> map = new HashMap<String, String>();

        Iterator<String> iterator = res.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = res.getString(key);
            map.put(key, value);

        }
        return map;
    }
}
