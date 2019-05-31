package zangdol.careme.model;

import java.util.Date;

public class Animal {
    private String idx;
    private String speciesCode;
    private String estimatedBirthAge;
    private String name;
    private char sex;
    private int weight;
    private String urlPicture;
    private Date registerDate;
    private String discoveredSpot;
    private int state;
    private String shelterIdx;
    private String shelterName;
    private String description;
    private String discoveredSpotLatitude;
    private String discoveredSpotLongitude;

    public String getDiscoveredSpotLatitude() {
        return discoveredSpotLatitude;
    }

    public void setDiscoveredSpotLatitude(String discoveredSpotLatitude) {
        this.discoveredSpotLatitude = discoveredSpotLatitude;
    }

    public String getDiscoveredSpotLongitude() {
        return discoveredSpotLongitude;
    }

    public void setDiscoveredSpotLongitude(String discoveredSpotLongitude) {
        this.discoveredSpotLongitude = discoveredSpotLongitude;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getEstimatedBirthAge() {
        return estimatedBirthAge;
    }

    public void setEstimatedBirthAge(String estimatedBirthAge) {
        this.estimatedBirthAge = estimatedBirthAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getDiscoveredSpot() {
        return discoveredSpot;
    }

    public void setDiscoveredSpot(String discoveredSpot) {
        this.discoveredSpot = discoveredSpot;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getShelterIdx() {
        return shelterIdx;
    }

    public void setShelterIdx(String shelterIdx) {
        this.shelterIdx = shelterIdx;
    }
}
