package zangdol.careme.restapi.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.nio.charset.Charset;

public abstract class ImageParameters {
    private MultipartEntityBuilder builder;
    private Context context;

    public abstract void addParams();


    public ImageParameters(Context context) {
        this.context = context;
        builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8")).setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        addParams();
    }

    public void addTextParam(String key, String value) {
        builder.addPart(key, new StringBody(value, ContentType.APPLICATION_JSON));
    }

    public void addImageParam(String key, Uri imageUri) {
        builder.addPart(key, new FileBody(new File(getRealPathFromURI(context, imageUri))));
    }

    public MultipartEntityBuilder getBuilder() {
        return builder;
    }


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static void goGallery(Activity activity){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, 2);
    }
}
