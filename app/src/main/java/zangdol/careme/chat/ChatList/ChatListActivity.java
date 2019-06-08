package zangdol.careme.chat.ChatList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ChatRoom;

public class ChatListActivity extends AppCompatActivity implements ChatListContract.View {
    ArrayList<ChatRoom> chatRooms;
    ListView chatRoomListView;
    private ChatListAdapter adapter;

    private ChatListContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_chatroom_list);

        chatRoomListView = (ListView) findViewById(R.id.listview_chatroom);

        presenter = new ChatListPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setAdapter(final ChatListAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatRoomListView.setAdapter(adapter);
            }
        });
    }
}