package zangdol.careme.model;

public class Discover extends DiscoverFind{

    private String matchingShelterIdx;
    private String shelterName;

    public String getMatchingShelterIdx() {
        return matchingShelterIdx;
    }

    public void setMatchingShelterIdx(String matchingShelterIdx) {
        this.matchingShelterIdx = matchingShelterIdx;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }
}
