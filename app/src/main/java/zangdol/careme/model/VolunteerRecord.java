package zangdol.careme.model;

public class VolunteerRecord
{
    private String shelter_name;
    private String dog_name;
    private String date;
    private String permission;

    public VolunteerRecord( String shelter_name, String dog_name, String date, String permission )
    {
        this.shelter_name = shelter_name;
        this.dog_name = dog_name;
        this.date = date;
        this.permission = permission;
    }
    public String getShelterName()
    {
        return shelter_name;
    }
    public String getDogName()
    {
        return dog_name;
    }
    public String getDate()
    {
        return date;
    }
    public String getPermission()
    {
        return permission;
    }
}
