package zangdol.careme.bulletinBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.Comment;

public class CommentListAdapter extends ArrayAdapter<Comment>
{
    private ArrayList<Comment> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_id;
        TextView tv_date;
        TextView tv_time;
        TextView tv_comment;

    }

    public CommentListAdapter(ArrayList<Comment> data, Context context) {
        super(context, R.layout.post_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        Comment comment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //TextView tv_id;
            //TextView tv_time;
            //TextView tv_date;
            //TextView tv_comment;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.bulletin_listitem, parent, false);
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_comment = (TextView) convertView.findViewById(R.id.tv_comment);





            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_id;
        //TextView tv_date;
        //TextView tv_time;
        //TextView tv_comment;
        //////////////////

        viewHolder.tv_id.setText(comment.getID());
        viewHolder.tv_date.setText(comment.getDate());
        viewHolder.tv_time.setText(comment.getTime());
        viewHolder.tv_comment.setText(comment.getComment());

        // Return the completed view to render on screen
        return convertView;
    }
}
