package zangdol.careme.restapi;

import java.util.HashMap;

/*
 * RestAPI 결과값을 받았을 때 실행시키기 위해 사용.
 * */
public interface RestAPIListener {

    void onResponse(HashMap<String, String> results);
}
