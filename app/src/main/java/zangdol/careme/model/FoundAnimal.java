package zangdol.careme.model;

public class FoundAnimal {
    public enum CodeType {
        DISCOVER, SHELTER
    }
    private String species_code;
    private String date;
    private String spot;
    private String picture;
    private String idx;
    private CodeType codeType;
    private String shelter_idx;
    private String shelter_name;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpecies_code() {
        return species_code;
    }

    public void setSpecies_code(String species_code) {
        this.species_code = species_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public String getShelter_idx() {
        return shelter_idx;
    }

    public void setShelter_idx(String shelter_idx) {
        this.shelter_idx = shelter_idx;
    }

    public String getShelter_name() {
        return shelter_name;
    }

    public void setShelter_name(String shelter_name) {
        this.shelter_name = shelter_name;
    }
}
