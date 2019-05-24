package zangdol.careme.bulletinBoardDiscoverFind.discoverFind;

import android.content.Intent;

import zangdol.careme.model.Discover;
import zangdol.careme.model.Find;

public class DiscoverFindContract {

    public interface View{
        Intent getIntent();
        void setDiscoverData(Discover discover);
        void setFindData(Find find);
    }
    public interface Presenter{
        void getData();
    }
}
