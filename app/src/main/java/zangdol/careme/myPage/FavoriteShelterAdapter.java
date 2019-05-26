package zangdol.careme.myPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.FavoriteShelter;

public class FavoriteShelterAdapter extends ArrayAdapter<FavoriteShelter>
{
    private ArrayList<FavoriteShelter> dataSet;
    Context mContext;

    private static class ViewHolder {
        ImageView shelterPic;
        TextView favorite_shelter_name;
        TextView favorite_shelter_phone;
        TextView favorite_shelter_address;
    }

    public FavoriteShelterAdapter(ArrayList<FavoriteShelter> data, Context context) {
        super(context, R.layout.favorite_shelter_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        FavoriteShelter favoriteShelter = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //shelterPic;
            //favorite_shelter_name;
            //favorite_shelter_phone;
            //favorite_shelter_address;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.favorite_shelter_listitem, parent, false);
            viewHolder.favorite_shelter_name = (TextView) convertView.findViewById(R.id.favorite_shelter_name);
            viewHolder.favorite_shelter_phone = (TextView) convertView.findViewById(R.id.favorite_shelter_phone);
            viewHolder.favorite_shelter_address = (TextView) convertView.findViewById(R.id.favorite_shelter_address);
            viewHolder.shelterPic = (ImageView) convertView.findViewById(R.id.favorite_shelter_pic);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //shelterPic;
        //favorite_shelter_name;
        //favorite_shelter_phone;
        //favorite_shelter_address;
        //////////////////

        viewHolder.favorite_shelter_name.setText(favoriteShelter.getShelter_name());
        viewHolder.favorite_shelter_phone.setText(favoriteShelter.getShelter_phone());
        viewHolder.favorite_shelter_address.setText(favoriteShelter.getShelter_address());
        viewHolder.shelterPic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
