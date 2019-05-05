package zangdol.careme.util;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import zangdol.careme.R;


public class NullChecker {
    public static void image(String url, ImageView iv){
        if(url.equals("null"))
            iv.setImageResource(R.drawable.no_image);
        else
            Picasso.get().load(ConvertManager.url(url)).into(iv);
    }
    public static void text(String text, TextView tv){
        if(text.equals("null"))
            tv.setText("없음");
        else
            tv.setText(text);
    }

}

