package zangdol.careme.model;

public class Comment
{
    private String ID;
    private String date;
    private String time;
    private String comment;

    public Comment(String ID, String date, String time, String comment )
    {
        this.ID = ID;
        this.date= date;
        this.time = time;
        this.comment = comment;
    }
    public String getID()
    {
        return ID;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    public String getComment()
    {
        return comment;
    }
}
