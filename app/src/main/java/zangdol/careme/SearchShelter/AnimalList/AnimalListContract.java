package zangdol.careme.SearchShelter.AnimalList;

public class AnimalListContract {
    public interface Presenter{
        void setAnimalList(String state);
    }
    public interface View{
        void setGridAdapter(AnimalListAdapter animalListAdapter);
    }
}
