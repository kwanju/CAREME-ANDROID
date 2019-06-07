package zangdol.careme.chat;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ChatRoom;

public class ChatRoomListActivity extends AppCompatActivity
{
    ArrayList<ChatRoom> chatRooms;
    ListView chatRoomListView;
    private ChatRoomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_chatroom_list);

        chatRoomListView=(ListView)findViewById(R.id.listview_chatroom);
        chatRooms = new ArrayList<>();
        adapter= new ChatRoomListAdapter( chatRooms,getApplicationContext());

        chatRoomListView.setAdapter(adapter);
        chatRoomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ChatRoom chatRoom = chatRooms.get(position);
////////////////////////////chatroom에 있는 필요한 정보를 추출하여 사용한다..///////////////////////////////////


            }
        });

        //////// 사용자의 아이디를 서버에 보내면 서버에서 채팅방 목록을 받아온다.
        chatRooms.add(new ChatRoom("해피해피보호소", "사진을 보냈습니다.", "오전6:00", null ));


    }
}