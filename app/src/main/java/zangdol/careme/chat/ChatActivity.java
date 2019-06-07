package zangdol.careme.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zangdol.careme.R;


public class ChatActivity extends AppCompatActivity implements ChatContract.View, View.OnClickListener {

    private ChatContract.Presenter presenter;

    private Button bt_send;
    private EditText et_message;
    private RecyclerView mMessageRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_chat);
        ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.ab_chat_shelter_name)).setText(getIntent().getStringExtra("shelter_name"));

        presenter = new ChatPresenter(this);

        setItem();
        presenter.getData();


    }

    private void setItem() {
        mMessageRecycler = (RecyclerView) findViewById(R.id.chat_message_list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        bt_send = (Button) findViewById(R.id.chat_send_message);
        bt_send.setOnClickListener(this);
        et_message = (EditText) findViewById(R.id.chat_input_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_send_message:
                presenter.sendMessage(et_message.getText().toString());
                et_message.setText("");
                break;
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setAdapter(MessageListAdapter adapter) {
        mMessageRecycler.setAdapter(adapter);
    }

    @Override
    public void setScrollBottom() {
        mMessageRecycler.scrollToPosition(presenter.getChatSize() - 1);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }


}
