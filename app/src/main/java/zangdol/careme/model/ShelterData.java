package zangdol.careme.model;

public class ShelterData
{
    private String shelter_name;
    private String address;
    private String phone;

    public ShelterData(String shelter_name, String address, String phone )
    {
        this.shelter_name = shelter_name;
        this.address= address;
        this.phone = phone;
    }
    public String getShelterName()
    {
        return shelter_name;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPhone()
    {
        return phone;
    }
}
