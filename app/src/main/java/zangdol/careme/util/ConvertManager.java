package zangdol.careme.util;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.GetSpeciesCode;

public class ConvertManager implements GetSpeciesCode.OnGetSpeciesCodeListener {
    public final static int DATE = 0;
    public final static int DATETIME = 1;

    public static ConvertManager instance = new ConvertManager();

    public static HashMap<String, String> species_code = null;


    public static boolean hasSpecies(){
        return species_code !=null;
    }
    public static String url(String url) {
        return Config.SERVERIP + url;
    }

    public static String time(String rowTime) {
        return rowTime.substring(0,5);
    }

    public static String date(String rowDate, int type) {
        String date = rowDate.split("T")[0].substring(0, 10);
        String time = rowDate.split("T")[1].substring(0, 5);

        if (type == DATE)
            return date;
        else
            return date.concat(" ").concat(time);
    }

    public static HashMap<String, String> search(String keyword) {
        HashMap<String, String> results = new HashMap<>();

        for (Object key : species_code.keySet()) {
            if (((String) species_code.get(key)).contains(keyword)) {
                results.put((String) key, species_code.get(key));
            }
        }

        return results;
    }

    public static String getCode(String species) {
        for (Object key : species_code.keySet()) {
            if (((String) species_code.get(key)).equals(species)) {
                return (String) key;
            }
        }
        return null;
    }

    public static String getSpecies(String code) {
        for (Object key : species_code.keySet()) {
            if (((String) key).equals(code))
                return species_code.get(key);
        }
        return "없음";
    }

    @Override
    public void onGetSpeciesCode(HashMap<String, String> speciesCodes) {
        ConvertManager.species_code = speciesCodes;
    }
}
