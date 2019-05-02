package zangdol.careme.volunteerRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.VolunteerRecord;

public class VolunteerRecordAdapter extends ArrayAdapter<VolunteerRecord> implements View.OnClickListener
{
    private ArrayList<VolunteerRecord> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_shelterName;
        TextView tv_dogName;
        TextView tv_date;
        ImageView shelter;
        ImageView dog;
    }

    public VolunteerRecordAdapter(ArrayList<VolunteerRecord> data, Context context) {
        super(context, R.layout.volunteer_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v)
    {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        VolunteerRecord volunteerRecord=(VolunteerRecord)object;

        Toast.makeText( mContext, volunteerRecord.getShelterName()
                + " " +volunteerRecord.getDogName() + " "+ volunteerRecord.getDate(), Toast.LENGTH_LONG).show();
        /////////////////////////////////Toast getApplicatinoContext()부분.////////////////

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        VolunteerRecord volunteerRecord = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
           // TextView tv_shelterName;
           // TextView tv_dogName;
           // TextView tv_date;
           // ImageView shelter;
           // ImageView dog;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.volunteer_listitem, parent, false);
            viewHolder.tv_shelterName = (TextView) convertView.findViewById(R.id.shelterName);
            viewHolder.tv_dogName = (TextView) convertView.findViewById(R.id.dogName);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.shelter = (ImageView) convertView.findViewById(R.id.shelterImg);
            viewHolder.dog = (ImageView) convertView.findViewById(R.id.dogImg);


            convertView.setTag(viewHolder);
        } else
            {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

        lastPosition = position;

        ///////////
        // TextView tv_shelterName;
        // TextView tv_dogName;
        // TextView tv_date;
        // ImageView shelter;
        // ImageView dog;
        //////////////////

        viewHolder.tv_shelterName.setText(volunteerRecord.getShelterName());
        viewHolder.tv_dogName.setText(volunteerRecord.getDogName());
        viewHolder.tv_date.setText(volunteerRecord.getDate());
        viewHolder.dog.setOnClickListener(this);
        viewHolder.dog.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
