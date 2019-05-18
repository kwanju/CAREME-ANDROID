package zangdol.careme.discoverRecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import zangdol.careme.R;
import zangdol.careme.model.Discover;
import zangdol.careme.util.NullChecker;

public class DiscoverRecordAdapter extends ArrayAdapter<Discover> {
    public DiscoverRecordAdapter(Context context, List<Discover> objects) {
        super(context, R.layout.discover_record_listitem, objects);
    }

    private static class ViewHolder {
        CircleImageView iv_image;
        TextView tv_datetime;
        TextView tv_spot;
        TextView tv_matching;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Discover discover = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.discover_record_listitem, parent, false);
            viewHolder.iv_image = (CircleImageView) convertView.findViewById(R.id.discover_record_item_image);
            viewHolder.tv_datetime = (TextView) convertView.findViewById(R.id.discover_record_item_datetime);
            viewHolder.tv_spot = (TextView) convertView.findViewById(R.id.discover_record_item_spot);
            viewHolder.tv_matching = (TextView) convertView.findViewById(R.id.discover_record_item_matching);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();


        NullChecker.image(discover.getUrl_picture(), viewHolder.iv_image);
        viewHolder.tv_datetime.setText(discover.getDiscoverDateTime());
        viewHolder.tv_spot.setText(discover.getDiscoveredSpot());
        NullChecker.text(discover.getShelterName(), viewHolder.tv_matching, "매칭중");

        return convertView;
    }
}
