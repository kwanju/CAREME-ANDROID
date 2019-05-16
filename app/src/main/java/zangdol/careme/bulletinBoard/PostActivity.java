package zangdol.careme.bulletinBoard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.Animal;
import zangdol.careme.model.Comment;

public class PostActivity extends AppCompatActivity
{

    private ArrayList<Comment> comments;
    private ListView commentListView;
    private CommentListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        commentListView=(ListView)findViewById(R.id.commentLv);
        comments = new ArrayList<>();
        adapter= new CommentListAdapter( comments,getApplicationContext());

        comments.add(new Comment("급하게 가입했슴돠", "2019-05-16", "19:17",
                "가나다라마바사아가나다라마바사아가나다라마바사아가나다라마바사아가나다라마바사아가나다라마바사아가나다라마바사아"));

        commentListView.setAdapter(adapter);
        commentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Comment comment = comments.get(position);

            }
        });


    }
}
