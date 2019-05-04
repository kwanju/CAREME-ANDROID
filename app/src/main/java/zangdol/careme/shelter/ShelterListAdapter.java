package zangdol.careme.shelter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ShelterData;


public class ShelterListAdapter extends ArrayAdapter<ShelterData>
{
    private ArrayList<ShelterData> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_shelterName;
        TextView tv_address;
        TextView tv_phone;
        ImageView shelterPic;
    }

    public ShelterListAdapter(ArrayList<ShelterData> data, Context context) {
        super(context, R.layout.shelter_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        ShelterData shelterData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //TextView tv_shelterName;
            //TextView tv_address;
            //TextView tv_phone;
            //ImageView shelterPic;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.shelter_listitem, parent, false);
            viewHolder.tv_shelterName = (TextView) convertView.findViewById(R.id.shelterName);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.address);
            viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.shelterPic = (ImageView) convertView.findViewById(R.id.shelterImg);



            convertView.setTag(viewHolder);
        } else
            {
                viewHolder = (ViewHolder) convertView.getTag();

            }

        lastPosition = position;

        ///////////
        //TextView tv_shelterName;
        //TextView tv_address;
        //TextView tv_phone;
        //ImageView shelterPic;
        //////////////////

        viewHolder.tv_shelterName.setText(shelterData.getShelterName());
        viewHolder.tv_address.setText(shelterData.getAddress());
        viewHolder.tv_phone.setText(shelterData.getPhone());
        viewHolder.shelterPic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
