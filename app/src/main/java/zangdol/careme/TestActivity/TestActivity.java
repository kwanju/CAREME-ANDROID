package zangdol.careme.TestActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import zangdol.careme.R;
import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.SearchShelterCategory;


public class TestActivity extends AppCompatActivity implements SearchShelterCategory.OnResponseListener {
    private TextView test_name;
    private SearchShelterCategory ssc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test_name = (TextView) findViewById(R.id.test_name);

        test();
    }

    private void test() {
        ssc = new SearchShelterCategory();
        ssc.request("경기", "안산시", this);
    }


    @Override
    public void onResponse(List<Shelter> shelterList) {
        test_name.setText(shelterList.get(0).getName());
    }
}
