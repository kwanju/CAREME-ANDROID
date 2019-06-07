package zangdol.careme.bulletinBoardDiscoverFind.discoverFind;

import zangdol.careme.model.Discover;
import zangdol.careme.model.Find;
import zangdol.careme.restapi.GetDiscover;
import zangdol.careme.restapi.GetDiscoverFind;
import zangdol.careme.restapi.GetFind;
import zangdol.careme.restapi.GetSpeciesCode;
import zangdol.careme.util.ConvertManager;

public class DiscoverFindPresenter implements DiscoverFindContract.Presenter, GetDiscover.OnGetDiscoverListener,GetFind.OnGetFindListener {

    private DiscoverFindContract.View view;
    public final static int DISCOVER = 0;

    public DiscoverFindPresenter(DiscoverFindContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        if(!ConvertManager.hasSpecies())
            new GetSpeciesCode(ConvertManager.instance);

        String idx = view.getIntent().getStringExtra("idx");
        int code = view.getIntent().getIntExtra("code", 0);
        if (code == DISCOVER)
            new GetDiscover(this, idx);
        else
            new GetFind(this,idx);
    }

    @Override
    public void onGetDiscover(Discover discover) {
        view.setDiscoverData(discover);
    }

    @Override
    public void onGetFind(Find find) {
        view.setFindData(find);
    }
}
