package zangdol.careme.dogEnrollment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

import zangdol.careme.R;

public class DogEnrollmentActivity extends AppCompatActivity
{

    private Button bt_img;
    private ImageView dogImage;

    private Bitmap dogImageUploaded;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogenrollment);

        setItem();

        bt_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
               // intent.setType("image/*");
               // intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });



    }
    public void setItem()
    {
        bt_img = (Button)findViewById( R.id.imgbutton );
        dogImage = (ImageView)findViewById( R.id.dogImage );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request we're responding to
        if (requestCode == 1)
        {
            // Make sure the request was successful
            if (resultCode == RESULT_OK)
            {
                try
                {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    dogImage.setImageBitmap(img);
                    dogImageUploaded = img;
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


}
