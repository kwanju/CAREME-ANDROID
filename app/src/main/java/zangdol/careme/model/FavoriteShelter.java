package zangdol.careme.model;

public class FavoriteShelter
{
    private String shelter_name;
    private String shelter_phone;
    private String shelter_address;
    private String imageUrl;

    public FavoriteShelter(String shelter_name, String shelter_phone, String shelter_address, String imageUrl)
    {
        this.shelter_name = shelter_name;
        this.shelter_phone = shelter_phone;
        this.shelter_address = shelter_address;
        this.imageUrl = imageUrl;
    }
    public String getShelter_name()
    {
        return shelter_name;
    }
    public String getShelter_phone()
    {
        return shelter_phone;
    }
    public String getShelter_address()
    {
        return shelter_address;
    }
    public String getImageUrl()
    {
        return imageUrl;
    }
}
