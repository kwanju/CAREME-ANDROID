package zangdol.careme.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ChatRoom;

public class ChatRoomListAdapter extends ArrayAdapter<ChatRoom>
{
    private ArrayList<ChatRoom> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_shelterName;
        TextView tv_lastMessage;
        TextView tv_date_time;
        ImageView shelterPic;
    }

    public ChatRoomListAdapter(ArrayList<ChatRoom> data, Context context) {
        super(context, R.layout.chatroom_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        ChatRoom chatRoom = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
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
            viewHolder.shelterPic = (ImageView) convertView.findViewById(R.id.shelter_image);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_shelterName;
        //TextView tv_lastMessage;
        //TextView tv_date_time;
        //ImageView shelterPic;
        //////////////////

        viewHolder.tv_shelterName.setText(chatRoom.getShelterName());
        viewHolder.tv_lastMessage.setText(chatRoom.getLastMessage());
        viewHolder.tv_date_time.setText(chatRoom.getDate_time());
        viewHolder.shelterPic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
