package zangdol.careme.adoptionRecordList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.R;
import zangdol.careme.model.AdoptionRecord;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class AdoptionRecordAdapter extends ArrayAdapter<HashMap<String,String>>
{
    private ArrayList<HashMap<String,String>> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_dog_name_adopt;
        TextView tv_dog_type_adopt;
        TextView tv_shelter_name_adopt;
        TextView tv_date_adopt;
        TextView tv_status_adopt;
        ImageView dog_pic;
    }

    public AdoptionRecordAdapter(ArrayList<HashMap<String,String>> data, Context context) {
        super(context, R.layout.adoption_record_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        HashMap<String,String> adoptionRecord = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //tv_dog_name_adopt;
            //tv_dog_type_adopt;
            //tv_shelter_name_adopt;
            //tv_date_adopt;
            //tv_status_adopt;
            //dog_pic;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adoption_record_listitem, parent, false);
            viewHolder.tv_dog_name_adopt = (TextView) convertView.findViewById(R.id.dog_name_adopt);
            viewHolder.tv_dog_type_adopt = (TextView) convertView.findViewById(R.id.dog_type_adopt);
            viewHolder.tv_shelter_name_adopt = (TextView) convertView.findViewById(R.id.shelter_name_adopt);
            viewHolder.tv_date_adopt = (TextView) convertView.findViewById(R.id.date_adopt);
            viewHolder.tv_status_adopt = (TextView) convertView.findViewById(R.id.status_adopt);
            viewHolder.dog_pic = (ImageView) convertView.findViewById(R.id.dog_adopted_image);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //tv_dog_name_adopt;
        //tv_dog_type_adopt;
        //tv_shelter_name_adopt;
        //tv_date_adopt;
        //tv_status_adopt;
        //dog_pic;
        //////////////////

        NullChecker.text(adoptionRecord.get("animalName"),viewHolder.tv_dog_name_adopt);
        NullChecker.text(ConvertManager.getSpecies(adoptionRecord.get("species_code")),viewHolder.tv_dog_type_adopt);
        NullChecker.text(adoptionRecord.get("shelterName"),viewHolder.tv_shelter_name_adopt);
        NullChecker.text(ConvertManager.date(adoptionRecord.get("datetime"),ConvertManager.DATETIME),viewHolder.tv_date_adopt);
        NullChecker.image(adoptionRecord.get("url_picture"),viewHolder.dog_pic);

        String permit = adoptionRecord.get("permit");

        if(permit.equals("0"))
            viewHolder.tv_status_adopt.setText("서류 검토중");
        else if(permit.equals("1"))
            viewHolder.tv_status_adopt.setText("입양허가");
        else if(permit.equals("-1"))
            viewHolder.tv_status_adopt.setText("입양거절");

        viewHolder.dog_pic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
