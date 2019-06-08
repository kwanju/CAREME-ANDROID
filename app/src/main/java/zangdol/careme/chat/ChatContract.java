package zangdol.careme.chat;

import android.app.Activity;
import android.content.Context;

import zangdol.careme.model.Shelter;

public class ChatContract {

    public interface View{
        Activity getActivity();
        void setAdapter(MessageListAdapter adapter);
        void setScrollBottom();
        Context getAppContext();
        void setShelter(Shelter shelter);
    }
    public interface Presenter{
        void getData();
        void connect();
        void disconnect();
        void sendMessage(String message);
        int getChatSize();
        void getShelter();
    }
}
