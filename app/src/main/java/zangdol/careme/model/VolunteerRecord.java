package zangdol.careme.model;

public class VolunteerRecord {
    private String shelter_name;
    private String dog_name;
    private String date;
    private String permission;
    private String imageUrl;
    private String animalIdx;

    public VolunteerRecord(String shelter_name, String dog_name, String date, String permission, String imageUrl, String animalIdx) {
        this.shelter_name = shelter_name;
        this.dog_name = dog_name;
        this.date = date;
        this.permission = permission;
        this.imageUrl = imageUrl;
        this.animalIdx = animalIdx;
    }

    public String getAnimalIdx() {
        return animalIdx;
    }

    public String getShelterName() {
        return shelter_name;
    }

    public String getDogName() {
        return dog_name;
    }

    public String getDate() {

        return date.substring(0, 10);
    }

    public String getPermission() {
        if (permission.equals("1"))
            return "허가";
        else if (permission.equals("0"))
            return "보류";
        else if (permission.equals("-1"))
            return "거절";
        return null;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
