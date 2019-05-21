package zangdol.careme.model;

public class DogInfoFiltered
{
    private String type;
    private String date;
    private String region;
    private String place;


    public DogInfoFiltered(String type, String date, String region, String place)
    {
        this.type = type;
        this.date = date;
        this.region = region;
        this.place = place;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public String getRegion()
    {
        return region;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public String getPlace()
    {
        return place;
    }
    public void setPlace(String place)
    {
        this.place = place;
    }

}