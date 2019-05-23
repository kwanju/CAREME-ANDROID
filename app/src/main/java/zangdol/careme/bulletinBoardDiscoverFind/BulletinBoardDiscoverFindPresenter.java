package zangdol.careme.bulletinBoardDiscoverFind;

import java.util.ArrayList;

import zangdol.careme.model.DiscoverFind;
import zangdol.careme.restapi.GetDiscoverFind;

public class BulletinBoardDiscoverFindPresenter implements BulletinBoardDiscoverFindContract.Presenter, GetDiscoverFind.OnGetDiscoverFindListener {

    private BulletinBoardDiscoverFindContract.View view;

    public BulletinBoardDiscoverFindPresenter(BulletinBoardDiscoverFindContract.View view) {
        this.view = view;
    }

    @Override
    public void onGetDiscover(ArrayList<DiscoverFind> discoverFinds) {
        BulletinBoardDiscoverFindAdapter adapter = new BulletinBoardDiscoverFindAdapter(view.getActivity(), discoverFinds);
        view.setAdapter(adapter);
    }

    @Override
    public void getData() {
        new GetDiscoverFind(this);
    }
}
