package zangdol.careme.SearchShelter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;

public class ShelterListActivity extends AppCompatActivity
{

    private ArrayList<String> values;
    private ArrayAdapter<String> adapter;
    private ListView shelterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterlist);
////////인텐트에서 해당 지역 받아오기/////////////////////////////////////////////////////////////
//////////String location;에 저장//////////////////////////////////////////////////////////////////

        setItem();
////////String location을 서버에 보내서 해당 지역에 있는 보호센터들 목록 받아오기//////////////////////////////////////
////////여기서는 임의로 유기견 보호센터1,2,3을 받아왔다고 가정한다./////////////////////////////////
        values.add( "유기견 보호센터1" );
        values.add( "유기견 보호센터2" );
        values.add( "유기견 보호센터3" );

        shelterLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String shelterName = values.get( position );
///////////////////shleter name을 intent에 담아서 다음 화면으로 진행./////////////////////////////
                Intent intent = new Intent( ShelterListActivity.this, ShelterInfoActivity.class );
                startActivity( intent );

            }
            });
    }
    public void setItem()
    {
        values = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        shelterLV = (ListView) findViewById( R.id.shelterLV );
        shelterLV.setAdapter(adapter);
    }
}
