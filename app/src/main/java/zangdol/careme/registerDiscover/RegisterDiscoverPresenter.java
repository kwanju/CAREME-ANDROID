package zangdol.careme.registerDiscover;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.util.HashMap;

import zangdol.careme.main.MainActivity;
import zangdol.careme.restapi.RegisterDiscover;
import zangdol.careme.util.SaveSharedPreference;

public class RegisterDiscoverPresenter implements RegisterDiscoverContract.Presenter, RegisterDiscover.OnRegisterDiscoverListener {
    private RegisterDiscoverContract.View view;
    private HashMap<String, String> data;
    private Uri imgUri = null;

    public RegisterDiscoverPresenter(RegisterDiscoverContract.View view) {
        this.view = view;
        data = new HashMap<>();
    }


    @Override
    public void setData(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void register() {
        if (!SaveSharedPreference.isLogin()) {
            Toast.makeText(view.getActivity(),"로그인이 필요합니다.",Toast.LENGTH_SHORT);
            return;
        }

        data.put("user_idx", SaveSharedPreference.getIdx());
        new RegisterDiscover(this, data, imgUri, view.getActivity());
    }

    @Override
    public void setImageUri(Uri uri) {
        this.imgUri = uri;
    }

    @Override
    public void OnRegisterDiscover() {
        view.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(view.getActivity(), "등록 성공", Toast.LENGTH_SHORT);

                Intent intent = new Intent(view.getActivity(), MainActivity.class);
                view.getActivity().startActivity(intent);
            }
        });
    }
}
