package zangdol.careme.animal;

import android.content.Intent;

import zangdol.careme.animal.applyVolunteer.ApplyVolunteerActivity;
import zangdol.careme.model.Animal;
import zangdol.careme.restapi.GetAnimalInfo;

public class AnimalInfoPresenter implements AnimalInfoContract.Presenter,GetAnimalInfo.OnAnimalInfoListener {

    private AnimalInfoActivity view;
    private GetAnimalInfo getAnimalInfo;
    private Animal animal;

    public AnimalInfoPresenter(AnimalInfoActivity animalInfoActivity) {
        this.view = animalInfoActivity;
    }

    @Override
    public void getAnimalInfo(int idx) {
        getAnimalInfo = new GetAnimalInfo(""+idx,this); // 동물 정보 가져오기
    }

    @Override
    public void moveApplyVolunteer() {
        Intent intent = new Intent(view,ApplyVolunteerActivity.class);
        intent.putExtra("animal_idx",animal.getIdx());
        view.startActivity(intent);
    }

    @Override
    public void OnAnimalInfo(Animal animal) {
        this.animal = animal;
        view.setAnimalInfo(animal);
    }
}
