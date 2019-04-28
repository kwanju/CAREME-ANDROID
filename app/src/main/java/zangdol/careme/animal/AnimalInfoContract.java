package zangdol.careme.animal;

import zangdol.careme.model.Animal;

public class AnimalInfoContract {

    public interface Presenter{
        void getAnimalInfo(int idx);

    }
    public interface View{
        void setAnimalInfo(Animal animal);
    }
}
