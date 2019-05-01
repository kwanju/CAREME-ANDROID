package zangdol.careme.SearchShelter.AnimalList;

import android.content.Intent;
import android.util.Log;

import java.util.List;

import zangdol.careme.animal.AnimalInfoActivity;
import zangdol.careme.model.AnimalSummary;
import zangdol.careme.restapi.GetAnimalListSummaryInShelter;

public class AnimalListPresenter implements AnimalListContract.Presenter, GetAnimalListSummaryInShelter.OnResponseListener, AnimalListAdapter.AdapterClickListener {

    private AnimalListActivity animalListActivity;
    private GetAnimalListSummaryInShelter getAnimalListSummaryInShelter;

    private String shelterIdx;

    public AnimalListPresenter(AnimalListActivity animalListActivity) {
        this.animalListActivity = animalListActivity;
        shelterIdx = animalListActivity.getIntent().getStringExtra("idx");
    }

    @Override
    public void setAnimalList(String state) {
        // https://mailmail.tistory.com/6
        // gridView
        getAnimalListSummaryInShelter = new GetAnimalListSummaryInShelter();
        getAnimalListSummaryInShelter.request(shelterIdx,state,this);

    }

    @Override
    public void onResponse(List<AnimalSummary> list) {
        for(int i=0;i<list.size();i++){
            Log.d("onresponse",list.get(i).getIdx());
        }
        animalListActivity.setGridAdapter(new AnimalListAdapter(list,this));
    }

    @Override
    public void onAdapterClick(int idx) {
        Intent intent = new Intent(animalListActivity,AnimalInfoActivity.class);
        intent.putExtra("idx",idx);
        animalListActivity.startActivity(intent);
    }
}
