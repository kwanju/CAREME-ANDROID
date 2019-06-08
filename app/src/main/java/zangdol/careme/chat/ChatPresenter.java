package zangdol.careme.chat;

import android.icu.util.Calendar;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.GetChat;
import zangdol.careme.restapi.GetShelter;
import zangdol.careme.util.SaveSharedPreference;

public class ChatPresenter implements ChatContract.Presenter, ChatManager.OnChatListener, GetChat.OnGetChatListener, GetShelter.OnGetShelterListener {
    private ChatContract.View view;
    private ChatManager chatManager;
    private MessageListAdapter mMessageAdapter;

    private String shelterIdx;

    // private HashMap<String, String> shelterCache;

    private boolean checkGetData;

    public ChatPresenter(ChatContract.View view) {
        this.view = view;
        chatManager = new ChatManager();
        shelterIdx = view.getActivity().getIntent().getStringExtra("shelter_idx");
        //  shelterCache = new HashMap<>();
        //    checkGetData = false;
    }

    @Override
    public void getData() {
        HashMap<String, String> data = new HashMap<>();
        data.put("user_idx", SaveSharedPreference.getIdx(view.getAppContext()));
        data.put("shelter_idx", shelterIdx);
        new GetChat(this, data);
    }

    @Override
    public void connect() {
        if (!SaveSharedPreference.isLogin(view.getAppContext()))
            return;
        chatManager.connect(SaveSharedPreference.getIdx(view.getAppContext()), this);
    }

    @Override
    public void disconnect() {
        if (!SaveSharedPreference.isLogin(view.getAppContext()))
            return;
        chatManager.disconnect();
    }

    @Override
    public void sendMessage(String message) {
        HashMap<String, String> data = new HashMap<>();

        data.put("message", message);
        data.put("user_idx", SaveSharedPreference.getIdx(view.getAppContext()));
        data.put("shelter_idx", shelterIdx); // 쉘터 idx 추가 필요.
        data.put("type", "0");

        chatManager.sendMessage(data);
        data.put("time", new String(Calendar.getInstance().getTime().toString()).substring(10, 16));
        mMessageAdapter.addItem(data);
        view.setScrollBottom();
    }

    @Override
    public int getChatSize() {
        return mMessageAdapter.getItemCount();
    }

    @Override
    public void getShelter() {
        new GetShelter(shelterIdx, this);
    }

    @Override
    public void onMessage(HashMap<String, String> data) {

        data.put("type", "1");

        if (data.get("shelter_idx").equals(shelterIdx)) { // shelter_idx가 현재 채팅창이랑 똑같을 때
            final HashMap<String, String> message = data;
            view.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mMessageAdapter.addItem(message);
                    view.setScrollBottom();
                }
            });
            ackMessage(data.get("message_idx"));
        } else {
            /*if (shelterCache.containsKey(data.get("message_idx")))
                new GetShelter(data.get("shelter_idx"), this);*/
        }


    }

    @Override
    public void onGetChat(ArrayList<HashMap<String, String>> chats) {

        //ArrayList<HashMap<String, String>> list = new ArrayList<>();
        mMessageAdapter = new MessageListAdapter(view.getActivity(), chats);
        view.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setAdapter(mMessageAdapter);
                view.setScrollBottom();
            }
        });
    }

    private void ackMessage(String messageIdx) {
        HashMap<String, String> data = new HashMap<>();
        data.put("message_idx", messageIdx);
        chatManager.ackMessage(data);
    }


    @Override
    public void onGetShelter(Shelter shelter) {
        view.setShelter(shelter);
    }

}
