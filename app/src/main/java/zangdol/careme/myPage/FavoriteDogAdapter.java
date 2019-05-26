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
import zangdol.careme.model.FavoriteDog;

public class FavoriteDogAdapter extends ArrayAdapter<FavoriteDog>
{
    private ArrayList<FavoriteDog> dataSet;
    Context mContext;

    private static class ViewHolder {
        ImageView dogPic;
        TextView tv_dog_type;
        TextView tv_dog_name;
        TextView tv_shelter_name;
        TextView tv_shelter_address;
    }

    public FavoriteDogAdapter(ArrayList<FavoriteDog> data, Context context) {
        super(context, R.layout.favorite_dog_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        FavoriteDog favoriteDog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //dogPic;
            //tv_dog_type;
            //tv_dog_name;
            //tv_shelter_name;
            //tv_shelter_address;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.favorite_dog_listitem, parent, false);
            viewHolder.tv_dog_type = (TextView) convertView.findViewById(R.id.favorite_dog_type);
            viewHolder.tv_dog_name = (TextView) convertView.findViewById(R.id.favorite_dog_name);
            viewHolder.tv_shelter_name = (TextView) convertView.findViewById(R.id.favorite_dog_shelter_name);
            viewHolder.tv_shelter_address = (TextView) convertView.findViewById(R.id.favorite_dog_shelter_place);
            viewHolder.dogPic = (ImageView) convertView.findViewById(R.id.favorite_dog_pic);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //dogPic;
        //tv_dog_type;
        //tv_dog_name;
        //tv_shelter_name;
        //tv_shelter_address;
        //////////////////

        viewHolder.tv_dog_type.setText(favoriteDog.getDog_type());
        viewHolder.tv_dog_name.setText(favoriteDog.getDog_name());
        viewHolder.tv_shelter_name.setText(favoriteDog.getShelter_name());
        viewHolder.tv_shelter_address.setText(favoriteDog.getShelter_address());
        viewHolder.dogPic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
