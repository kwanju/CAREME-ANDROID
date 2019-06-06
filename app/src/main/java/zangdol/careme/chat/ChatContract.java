package zangdol.careme.chat;

import android.app.Activity;

public class ChatContract {

    public interface View{
        Activity getActivity();
        void setAdapter(MessageListAdapter adapter);
        void setScrollBottom();
    }
    public interface Presenter{
        void getData();
        void connect();
        void disconnect();
        void sendMessage(String message);
        int getChatSize();
    }
}
