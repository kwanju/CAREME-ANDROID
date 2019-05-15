package zangdol.careme.model;

public class DogReport
{
    private String status;
    private String type;
    private String description;
    private String date;
    private String place;
    private String imgURL;

    public DogReport( String status, String type, String description, String date, String place, String imgURL)
    {
        this.status = status;
        this.type = type;
        this.description = description;
        this.date = date;
        this.place = place;
        this.imgURL = imgURL;
    }
    public void setStatus( String status )
    {
        this.status = status;
    }
    public String getStatus()
    {
        return status;
    }
    public void setType( String type )
    {
        this.type = type;
    }
    public String getType()
    {
        return type;
    }
    public void setDescription( String description )
    {
        this.description = description;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDate( String date )
    {
        this.date = date;
    }
    public String getDate()
    {
        return date;
    }
    public void setPlace( String place )
    {
        this.place = place;
    }
    public String getPlace()
    {
        return place;
    }
    public String getImgURL()
    {
        return imgURL;
    }
    public void setImgURL( String imgURL )
    {
        this.imgURL = imgURL;
    }
}
