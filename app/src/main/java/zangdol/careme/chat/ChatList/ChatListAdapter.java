package zangdol.careme.chat.ChatList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.R;
import zangdol.careme.chat.ChatActivity;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class ChatListAdapter extends ArrayAdapter<HashMap<String, String>> {
    private ArrayList<HashMap<String, String>> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_shelterName;
        TextView tv_lastMessage;
        TextView tv_date_time;
        TextView tv_count_badge;
    }

    public ChatListAdapter(ArrayList<HashMap<String, String>> data, Context context) {
        super(context, R.layout.chatroom_listitem, data);
        this.dataSet = data;
        this.mContext = context;

    }


    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final HashMap<String, String> chatRoom = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            ///////////
            //TextView tv_shelterName;
            //TextView tv_lastMessage;
            //TextView tv_date_time;
            //ImageView shelterPic;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.chatroom_listitem, parent, false);
            viewHolder.tv_shelterName = (TextView) convertView.findViewById(R.id.shelter_name_chat);
            viewHolder.tv_lastMessage = (TextView) convertView.findViewById(R.id.last_message);
            viewHolder.tv_date_time = (TextView) convertView.findViewById(R.id.date_time_chat);
            viewHolder.tv_count_badge = (TextView) convertView.findViewById(R.id.chat_count_badge);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_shelterName;
        //TextView tv_lastMessage;
        //TextView tv_date_time;
        //ImageView shelterPic;
        //////////////////


        NullChecker.text(chatRoom.get("shelterName"), viewHolder.tv_shelterName);
        NullChecker.text(chatRoom.get("message"), viewHolder.tv_lastMessage);
        NullChecker.text(ConvertManager.date(chatRoom.get("recent_send_time"), ConvertManager.DATETIME), viewHolder.tv_date_time);
        NullChecker.text(chatRoom.get("count"), viewHolder.tv_count_badge);

        if (chatRoom.get("count").equals("0"))
            viewHolder.tv_count_badge.setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("shelter_idx", chatRoom.get("shelter_idx"));
                mContext.startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
