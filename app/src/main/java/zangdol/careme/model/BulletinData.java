package zangdol.careme.model;

public class BulletinData
{
    private String title;
    private String id;
    private String time;
    private String hit;
    private String noOfComments;

    public BulletinData(String title, String id, String time, String hit, String noOfComments )
    {
        this.title = title;
        this.id = id;
        this.time = time;
        this.hit = hit;
        this.noOfComments = noOfComments;
    }
    public String getTitle()
    {
        return title;
    }
    public String getId()
    {
        return id;
    }
    public String getTime()
    {
        return time;
    }
    public String getHit()
    {
        return hit;
    }
    public String getNoOfComments()
    {
        return noOfComments;
    }
}
