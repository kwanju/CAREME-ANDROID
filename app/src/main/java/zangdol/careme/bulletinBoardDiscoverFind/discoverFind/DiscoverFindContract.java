package zangdol.careme.bulletinBoardDiscoverFind.discoverFind;

import android.app.Activity;
import android.content.Intent;

import java.lang.reflect.AccessibleObject;

import zangdol.careme.model.Discover;
import zangdol.careme.model.Find;

public class DiscoverFindContract {

    public interface View{
        Intent getIntent();
        void setDiscoverData(Discover discover);
        void setFindData(Find find);
        Activity getActivity();
    }
    public interface Presenter{
        void getData();
    }
}
