package zangdol.careme.model;

public class ChatRoom
{
    private String shelterName;
    private String lastMessage;
    private String date_time;
    private String imgUrl;
    private String roomID;

    public ChatRoom( String shelterName, String lastMessage, String date_time, String imgUrl )
    {
        this.shelterName = shelterName;
        this.lastMessage = lastMessage;
        this.date_time = date_time;
        this.imgUrl = imgUrl;
    }
    public String getShelterName()
    {
        return shelterName;
    }
    public String getLastMessage()
    {
        return lastMessage;
    }
    public String getDate_time()
    {
        return date_time;
    }
    public String getImgUrl()
    {
        return imgUrl;
    }
    public String getRoomID()
    {
        return roomID;
    }
}
