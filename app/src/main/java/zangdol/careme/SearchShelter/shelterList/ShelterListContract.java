package zangdol.careme.SearchShelter.shelterList;

import java.util.ArrayList;

import zangdol.careme.model.Shelter;

public class ShelterListContract {

    public interface View{
        void addList(Shelter shelter);
    }
    public interface Presenter{
        void addShelters();
        void moveShelter(int position);
    }
}
