package zangdol.careme.restapi;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import zangdol.careme.model.Shelter;

import static org.junit.Assert.*;

public class SearchShelterCategoryTest implements SearchShelterCategory.OnResponseListener{
    private SearchShelterCategory ssc;

    private String name;
    CountDownLatch signal;
    @Before
    public void setup(){
        signal = new CountDownLatch(1);
        ssc = new SearchShelterCategory();
        ssc.request("경기","종로구",this);
    }

    @Test
    public void requestTest() {
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("테스트보호소", name);
    }

    @Override
    public void onResponse(ArrayList<Shelter> shelterList) {
            Shelter shelter = shelterList.get(0);
            this.name = shelter.getName();
            signal.countDown();
        }

}