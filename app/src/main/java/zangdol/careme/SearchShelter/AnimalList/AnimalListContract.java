package zangdol.careme.SearchShelter.AnimalList;

public class AnimalListContract {
    public interface Presenter{
        void setAnimalList(String shelterIdx,String state);
    }
    public interface View{
        void setGridAdapter(AnimalListAdapter animalListAdapter);
    }
}
