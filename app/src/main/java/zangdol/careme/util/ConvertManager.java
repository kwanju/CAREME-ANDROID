package zangdol.careme.util;

import zangdol.careme.Config;

public class ConvertManager {
    public final static int DATE = 0;
    public final static int DATETIME = 1;

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
}
