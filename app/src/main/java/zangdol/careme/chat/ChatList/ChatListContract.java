package zangdol.careme.chat.ChatList;

import android.app.Activity;

public class ChatListContract {
    public interface View{
        Activity getActivity();
        void setAdapter(ChatListAdapter adapter);
    }
    public interface Presenter{
        void getData();
    }
}
