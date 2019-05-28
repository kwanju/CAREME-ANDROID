package zangdol.careme.searchFilterDogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.FoundAnimal;
import zangdol.careme.util.NullChecker;

public class DogFilterAdapter extends ArrayAdapter<FoundAnimal> {

    private ArrayList<FoundAnimal> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_species_code;
        TextView tv_sex;
        TextView tv_date;
        TextView tv_discoredSpot;
        TextView tv_type;
        ImageView dogImg;
    }

    public DogFilterAdapter(ArrayList<FoundAnimal> data, Context context) {
        super(context, R.layout.filtered_dogs_listitem, data);
        this.dataSet = data;
        this.mContext = context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FoundAnimal foundAnimal = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            ///////////
            //TextView tv_species_code;
            //TextView tv_date;
            //TextView tv_discoredSpot;
            // TextView tv_place;
            // ImageView dogImg;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.filtered_dogs_listitem, parent, false);
            viewHolder.tv_species_code = (TextView) convertView.findViewById(R.id.textView25);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.textView27);
            viewHolder.tv_discoredSpot = (TextView) convertView.findViewById(R.id.textView29);
            viewHolder.dogImg = (ImageView) convertView.findViewById(R.id.imageView3);
            viewHolder.tv_sex = (TextView) convertView.findViewById(R.id.searchFilterItem_sex);
            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.sfda_tv_type);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_species_code;
        //TextView tv_date;
        //TextView tv_discoredSpot;
        // TextView tv_place;
        // ImageView dogImg;
        //////////////////

        viewHolder.tv_species_code.setText(foundAnimal.getSpecies_code());
        viewHolder.tv_date.setText(foundAnimal.getDate());
        viewHolder.tv_discoredSpot.setText(foundAnimal.getSpot());
        NullChecker.image(foundAnimal.getPicture(), viewHolder.dogImg);
        viewHolder.tv_type.setText(foundAnimal.getCodeType() == FoundAnimal.CodeType.DISCOVER ? "발견했어요" : "보호소 보호중");
        viewHolder.tv_sex.setText(foundAnimal.getSex().equals("null") ? "알수없음" : foundAnimal.getSex().equals("w") ? "여" : "남");
        // Return the completed view to render on screen
        return convertView;
    }


}
