package zangdol.careme.bulletinBoardDiscoverFind;

import android.app.Activity;

import java.util.ArrayList;

import zangdol.careme.model.DiscoverFind;

public class BulletinBoardDiscoverFindContract {
    public interface View{
        void setAdapter(BulletinBoardDiscoverFindAdapter adapter);
        Activity getActivity();
    }
    public interface Presenter{
        void getData();
    }
}
