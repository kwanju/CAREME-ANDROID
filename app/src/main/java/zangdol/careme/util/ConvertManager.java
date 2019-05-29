package zangdol.careme.util;

import java.util.HashMap;

import zangdol.careme.Config;

public class ConvertManager {
    public final static int DATE = 0;
    public final static int DATETIME = 1;

    public static HashMap<String, String> species_code;

    public static String url(String url) {
        return Config.SERVERIP + url;
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
}
