package zangdol.careme.shelter;

import zangdol.careme.model.Shelter;

public class ShelterInfoContract {
    public interface View {
        void setShelter(Shelter shelter);
    }

    public interface Presenter {
        void getShelter();
        void moveAnimalList();
    }
}
