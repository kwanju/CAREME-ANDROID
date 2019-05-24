package zangdol.careme.bulletinBoardDiscoverFind;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import zangdol.careme.bulletinBoardDiscoverFind.discoverFind.DiscoverFindActivity;
import zangdol.careme.model.Discover;
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

        final DiscoverFind discoverFind = discoverFinds.get(position);
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

}
