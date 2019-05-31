package zangdol.careme.model;

import android.graphics.Bitmap;

public class ItemForMain
{
    Bitmap image;
    String functionName;

    public ItemForMain(Bitmap image,String functionName)
    {
        super();
        this.image = image;
        this.functionName = functionName;
    }
    public Bitmap getImage()
    {
        return image;
    }
    public void setImage(Bitmap image)
    {
        this.image = image;
    }
    public String getFunctionName()
    {
        return functionName;
    }
    public void setFunctionName( String functionName )
    {
        this.functionName = functionName;
    }

}
