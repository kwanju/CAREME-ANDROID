package zangdol.careme.registerDiscoverFind;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.bulletinBoardDiscoverFind.discoverFind.DiscoverFindActivity;
import zangdol.careme.main.MainActivity;
import zangdol.careme.restapi.RegisterDiscover;
import zangdol.careme.restapi.RegisterFind;
import zangdol.careme.util.SaveSharedPreference;

public class RegisterDiscoverFindPresenter implements RegisterDiscoverFindContract.Presenter, RegisterDiscover.OnRegisterDiscoverListener, RegisterFind.OnRegisterFindListener {
    private RegisterDiscoverFindContract.View view;
    private HashMap<String, String> data;
    private Uri imgUri = null;

    public RegisterDiscoverFindPresenter(RegisterDiscoverFindContract.View view) {
        this.view = view;
        data = new HashMap<>();
    }


    @Override
    public void setData(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void register(RegisterDiscoverFindActivity.RegisterType registerType) {
        if (!SaveSharedPreference.isLogin()) {
            Toast.makeText(view.getActivity(), "로그인이 필요합니다.", Toast.LENGTH_SHORT);
            return;
        }

        data.put("user_idx", SaveSharedPreference.getIdx());

        if (registerType == RegisterDiscoverFindActivity.RegisterType.DISCOVER)
            new RegisterDiscover(this, data, imgUri, view.getActivity());
        else
            new RegisterFind(this, data, imgUri, view.getActivity());

    }

    @Override
    public void setImageUri(Uri uri) {
        this.imgUri = uri;
    }

    @Override
    public void OnRegisterDiscover(String idx) {
        onResultRegister(idx, Config.Code.DISCOVER);
    }

    @Override
    public void OnRegisterFind(String idx) {
        onResultRegister(idx, Config.Code.FIND);
    }

    private void onResultRegister(final String idx, final int code) {
        view.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(view.getActivity(), "등록 성공", Toast.LENGTH_SHORT);

                Intent intent = new Intent(view.getActivity(), DiscoverFindActivity.class);
                intent.putExtra("idx",idx);
                intent.putExtra("code",code);
                view.getActivity().finish();
                view.getActivity().startActivity(intent);
            }
        });
    }
}
