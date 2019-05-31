package zangdol.careme.volunteerRecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.animal.AnimalInfoActivity;
import zangdol.careme.model.VolunteerRecord;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class VolunteerRecordAdapter extends ArrayAdapter<VolunteerRecord> {
    private ArrayList<VolunteerRecord> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_shelterName;
        TextView tv_dogName;
        TextView tv_date;
        TextView tv_permission;
        ImageView dog;
    }

    public VolunteerRecordAdapter(ArrayList<VolunteerRecord> data, Context context) {
        super(context, R.layout.volunteer_listitem, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final VolunteerRecord volunteerRecord = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            ///////////
            // TextView tv_shelterName;
            // TextView tv_dogName;
            // TextView tv_date;
            //TextView tv_permission;
            // ImageView dog;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.volunteer_listitem, parent, false);
            viewHolder.tv_shelterName = (TextView) convertView.findViewById(R.id.shelterName);
            viewHolder.tv_dogName = (TextView) convertView.findViewById(R.id.dogName);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.date);
            viewHolder.tv_permission = (TextView) convertView.findViewById(R.id.permission);
            viewHolder.dog = (ImageView) convertView.findViewById(R.id.dogImg);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        lastPosition = position;

        ///////////
        // TextView tv_shelterName;
        // TextView tv_dogName;
        // TextView tv_date;
        // ImageView dog;
        //////////////////

        viewHolder.tv_shelterName.setText(volunteerRecord.getShelterName());
        viewHolder.tv_dogName.setText(volunteerRecord.getDogName());
        viewHolder.tv_date.setText(volunteerRecord.getDate());
        viewHolder.tv_permission.setText(volunteerRecord.getPermission());

        NullChecker.image(volunteerRecord.getImageUrl(),viewHolder.dog);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,AnimalInfoActivity.class);
                intent.putExtra("idx",Integer.parseInt(volunteerRecord.getAnimalIdx()));
                mContext.startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
