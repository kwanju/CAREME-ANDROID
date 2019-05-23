package zangdol.careme.bulletinBoardDiscoverFind;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import zangdol.careme.model.DiscoverFind;

public class BulletinBoardDiscoverFindAdapter extends BaseAdapter {
    Context context;
    ArrayList<DiscoverFind> discoverFinds;

    public BulletinBoardDiscoverFindAdapter(Context context, ArrayList<DiscoverFind> discoverFinds) {
        this.context = context;
        this.discoverFinds = discoverFinds;
    }

    public int getCount() {
        return discoverFinds.size();
    }

    public Object getItem(int position) {
        return discoverFinds.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = new BulletinBoardDiscoverFindItem(context);
        ((BulletinBoardDiscoverFindItem) convertView).setData(discoverFinds.get(position));
        return convertView;
    }

}
