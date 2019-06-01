package zangdol.careme.myPage.FavoriteAnimal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.R;
import zangdol.careme.animal.AnimalInfoActivity;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class FavoriteAnimalAdapter extends ArrayAdapter<HashMap<String, String>> {
    private ArrayList<HashMap<String, String>> dataSet;
    Context mContext;

    private static class ViewHolder {
        ImageView dogPic;
        TextView tv_dog_type;
        TextView tv_dog_name;
        TextView tv_shelter_name;
    }

    public FavoriteAnimalAdapter(ArrayList<HashMap<String, String>> data, Context context) {
        super(context, R.layout.favorite_dog_listitem, data);
        this.dataSet = data;
        this.mContext = context;
    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final HashMap<String, String> favoriteDog = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
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
            viewHolder.dogPic = (ImageView) convertView.findViewById(R.id.favorite_dog_pic);


            convertView.setTag(viewHolder);
        } else {
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

        viewHolder.tv_dog_type.setText(ConvertManager.getSpecies(favoriteDog.get("species_code")));
        viewHolder.tv_dog_name.setText(favoriteDog.get("name"));
        viewHolder.tv_shelter_name.setText(favoriteDog.get("shelterName"));
        NullChecker.image(favoriteDog.get("url_picture"), viewHolder.dogPic);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AnimalInfoActivity.class);
                intent.putExtra("idx", Integer.parseInt(favoriteDog.get("idx")));
                mContext.startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
