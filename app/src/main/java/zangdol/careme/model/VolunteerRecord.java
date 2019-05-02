package zangdol.careme.model;

public class VolunteerRecord
{
    private String shelter_name;
    private String dog_name;
    private String date;

    public VolunteerRecord( String shelter_name, String dog_name, String date )
    {
        this.shelter_name = shelter_name;
        this.dog_name = dog_name;
        this.date = date;
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
}
