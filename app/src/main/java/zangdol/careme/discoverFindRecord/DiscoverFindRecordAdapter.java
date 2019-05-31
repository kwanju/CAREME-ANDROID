package zangdol.careme.discoverFindRecord;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import zangdol.careme.R;
import zangdol.careme.bulletinBoardDiscoverFind.discoverFind.DiscoverFindActivity;
import zangdol.careme.model.Discover;
import zangdol.careme.model.DiscoverFind;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class DiscoverFindRecordAdapter extends ArrayAdapter<DiscoverFind> {
    private Context context;

    public DiscoverFindRecordAdapter(Context context, List<DiscoverFind> objects) {
        super(context, R.layout.discover_record_listitem, objects);
        this.context = context;
    }

    private static class ViewHolder {
        CircleImageView iv_image;
        TextView tv_datetime;
        TextView tv_spot;
        TextView tv_matching;
        LinearLayout ll_matching;
        TextView tv_type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DiscoverFind discoverFind = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.discover_record_listitem, parent, false);
            viewHolder.iv_image = (CircleImageView) convertView.findViewById(R.id.discover_record_item_image);
            viewHolder.tv_datetime = (TextView) convertView.findViewById(R.id.discover_record_item_datetime);
            viewHolder.tv_spot = (TextView) convertView.findViewById(R.id.discover_record_item_spot);
            viewHolder.tv_matching = (TextView) convertView.findViewById(R.id.discover_record_item_matching);
            viewHolder.ll_matching = (LinearLayout) convertView.findViewById(R.id.drl_ll_shelter_matching);
            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.drl_tv_type);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();


        NullChecker.image(discoverFind.getUrl_picture(), viewHolder.iv_image);
        viewHolder.tv_datetime.setText(ConvertManager.date(discoverFind.getEventDateTime(), ConvertManager.DATETIME));
        viewHolder.tv_spot.setText(discoverFind.getEventSpot());

        if (Discover.class.isInstance(discoverFind))
            NullChecker.text(((Discover) discoverFind).getShelterName(), viewHolder.tv_matching, "매칭중");
        else
            viewHolder.ll_matching.setVisibility(View.GONE);

        setStatus(discoverFind, viewHolder.tv_type);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DiscoverFindActivity.class);
                intent.putExtra("idx", discoverFind.getIdx());
                intent.putExtra("code", Discover.class.isInstance(discoverFind) ? 0 : 1);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private void setStatus(DiscoverFind discoverFind, TextView type) {
        if (Discover.class.isInstance(discoverFind)) {
            type.setText("발견했어요");
            type.setBackgroundColor(0x6A5ACD00);
        } else {
            type.setText("찾아요");
            type.setBackgroundColor(0x90FFA500);
        }
    }
}
