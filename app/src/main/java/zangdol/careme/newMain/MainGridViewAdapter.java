package zangdol.careme.newMain;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ItemForMain;

public class MainGridViewAdapter extends ArrayAdapter<ItemForMain>
{
    Context context;
    int layoutResourceId;
    ArrayList<ItemForMain> data = new ArrayList<ItemForMain>();

    public MainGridViewAdapter(Context context, int layoutResourceId, ArrayList<ItemForMain> data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    static class RecordHolder
    {
        ImageView imageItem;
        TextView txtTitle;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.item_text_mainpage);
            holder.imageItem = (ImageView) row.findViewById(R.id.item_image_mainpage);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        ItemForMain item = data.get(position);
        holder.txtTitle.setText(item.getFunctionName());
        holder.imageItem.setImageBitmap(item.getImage());
        return row;

    }



}
