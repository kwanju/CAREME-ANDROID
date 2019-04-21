package zangdol.careme.SearchShelter.AnimalList;

import java.util.List;

import zangdol.careme.model.AnimalSummary;
import zangdol.careme.restapi.GetAnimalListSummaryInShelter;

public class AnimalListPresenter implements AnimalListContract.Presenter, GetAnimalListSummaryInShelter.OnResponseListener {

    private AnimalListActivity animalListActivity;
    private GetAnimalListSummaryInShelter getAnimalListSummaryInShelter;

    public AnimalListPresenter(AnimalListActivity animalListActivity) {
        this.animalListActivity = animalListActivity;
    }

    @Override
    public void setAnimalList(String shelterIdx,String state) {
        // https://mailmail.tistory.com/6
        // gridView
        getAnimalListSummaryInShelter = new GetAnimalListSummaryInShelter();
        getAnimalListSummaryInShelter.request(shelterIdx,state,this);

    }

    @Override
    public void onResponse(List<AnimalSummary> list) {
        animalListActivity.setGridAdapter(new AnimalListAdapter(list));
    }
}
