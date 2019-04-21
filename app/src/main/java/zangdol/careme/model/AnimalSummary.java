package zangdol.careme.model;

public class AnimalSummary {

    private String imageURL;
    private String name;
    private String idx;
    private String speciesCode;

    public AnimalSummary(String imageURL, String name, String idx, String speciesCode) {
        this.imageURL = imageURL;
        this.name = name;
        this.idx = idx;
        this.speciesCode = speciesCode;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
