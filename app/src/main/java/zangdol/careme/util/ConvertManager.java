package zangdol.careme.util;

import zangdol.careme.Config;

public class ConvertManager {
    public static String url(String url) {
        return Config.SERVERIP + url;
    }
}
