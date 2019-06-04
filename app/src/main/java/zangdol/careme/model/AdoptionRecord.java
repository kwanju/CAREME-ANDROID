package zangdol.careme.model;

public class AdoptionRecord
{
    private String dog_name_adopt;
    private String dog_type_adopt;
    private String shelter_name_adopt;
    private String date_adopt;
    private String status_adopt;
    private String imageUrl;

    public AdoptionRecord(String dog_name, String dog_type, String shelter_name, String date, String status, String imageUrl)
    {
        dog_name_adopt = dog_name;
        dog_type_adopt = dog_type;
        shelter_name_adopt = shelter_name;
        date_adopt = date;
        status_adopt = status;
        this.imageUrl = imageUrl;
    }
    public String getDog_name()
    {
        return dog_name_adopt;
    }
    public String getDog_type()
    {
        return dog_type_adopt;
    }
    public String getShelter_name()
    {
        return shelter_name_adopt;
    }
    public String getDate()
    {
        return date_adopt;
    }
    public String getStatus()
    {
        return status_adopt;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }

}
