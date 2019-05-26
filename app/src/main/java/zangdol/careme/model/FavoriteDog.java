package zangdol.careme.model;

public class FavoriteDog
{
    private String dog_type;
    private String dog_name;
    private String shelter_name;
    private String shelter_address;
    private String imageUrl;

    public FavoriteDog(String dog_type, String dog_name, String shelter_name, String shelter_address, String imageUrl) {
        this.dog_type = dog_type;
        this.dog_name = dog_name;
        this.shelter_name = shelter_name;
        this.shelter_address = shelter_address;
        this.imageUrl = imageUrl;
    }
    public String getDog_type()
    {
        return dog_type;
    }
    public String getDog_name()
    {
        return dog_name;
    }
    public String getShelter_name()
    {
        return shelter_name;
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
