package zangdol.careme.lostFound;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import zangdol.careme.model.DogReport;

public class DogFoundLostAdapter extends BaseAdapter
{
    Context context;
    ArrayList<DogReport> dogReports = new ArrayList<>();
    boolean visible = false;

    public DogFoundLostAdapter( Context context, ArrayList<DogReport> dogReports)
    {
        this.context = context;
        this.dogReports = dogReports;
    }
    public int getCount()
    {
        return dogReports.size();
    }
    public Object getItem( int position )
    {
        return dogReports.get(position);
    }
    public long getItemId( int position )
    {
        return position;
    }
    public View getView( int position, View convertView, ViewGroup parent )
    {
        if( convertView == null )
            convertView = new GridItem(context);
        ((GridItem)convertView).setData( dogReports.get(position), visible );
        return convertView;
    }

}
