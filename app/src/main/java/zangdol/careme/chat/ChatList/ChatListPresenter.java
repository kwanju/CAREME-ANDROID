package zangdol.careme.chat.ChatList;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.restapi.GetChatList;
import zangdol.careme.util.SaveSharedPreference;

public class ChatListPresenter implements ChatListContract.Presenter, GetChatList.OnGetChatListListener {
    private ChatListContract.View view;

    public ChatListPresenter(ChatListContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        if (!SaveSharedPreference.isLogin())
            return;
        new GetChatList(this, SaveSharedPreference.getIdx());
    }

    @Override
    public void onGetChatList(ArrayList<HashMap<String, String>> chats) {
        view.setAdapter(new ChatListAdapter(chats, view.getActivity()));
    }
}
