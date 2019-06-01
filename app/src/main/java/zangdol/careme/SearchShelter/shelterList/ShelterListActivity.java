package zangdol.careme.SearchShelter.shelterList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.Shelter;

public class ShelterListActivity extends AppCompatActivity implements ShelterListContract.View {
    private ShelterListPresenter presenter;

    private ArrayList<String> values;
    private ArrayAdapter<String> adapter;
    private ListView shelterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterlist);

////////인텐트에서 해당 지역 받아오기/////////////////////////////////////////////////////////////
//////////String location;에 저장//////////////////////////////////////////////////////////////////

        presenter = new ShelterListPresenter(this);

        setElements();

        presenter.addShelters();

        shelterLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.moveShelter(position);

            }
        });
    }

    public void setElements() {
        values = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        shelterLV = (ListView) findViewById(R.id.shelterLV);
        shelterLV.setAdapter(adapter);
    }


    @Override
    public void addList(Shelter shelter) {
        values.add(shelter.getName());
    }
}
