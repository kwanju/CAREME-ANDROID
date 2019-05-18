package zangdol.careme.restapi;

import android.os.AsyncTask;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import zangdol.careme.model.Discover;

import static org.junit.Assert.*;


public class GetDiscvoerRecordTest implements GetDiscoverRecord.OnGetDiscoverRecordListener{


    private GetDiscoverRecord gdr;
    private GetDiscvoerRecordTest me;
    CountDownLatch signal;

    @Before
    public void setUp() throws Exception {
        me=this;
        signal = new CountDownLatch(1);
    }
/*
    @Test
    public void test_response(){
        signal = new CountDownLatch(1);
        gdr = new GetDiscoverRecord("1",this);
        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnGetDiscoverRecord(ArrayList<Discover> discovers) {
        assertNotEquals(discovers.size(),0);
        signal.countDown();
    }*/

    @Test
    public void test_response(){
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<String,Void,String> myTask = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                gdr = new GetDiscoverRecord("1",me);
                return null;
            }
        };
    }

    @Override
    public void OnGetDiscoverRecord(ArrayList<Discover> discovers) {
        assertNotEquals(discovers.size(),0);
    }
}