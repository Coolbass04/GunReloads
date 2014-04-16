package jant.gunreloads.app.sql.model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by anikolic on 4/15/14.
 */
public class Caliber {
    private static HashSet<String> calibers = new HashSet<String>();

    static {
        calibers.add("9mm");
        calibers.add("40 S&W");
        calibers.add("45 ACP");
        calibers.add("5.56");
    }

    public Caliber() {
    }

    public static String[] getCalibers() {
        return calibers.toArray(new String[calibers.size()]);
    }

    public static boolean containsValue(String caliber) {
        return calibers.contains(caliber);
    }

    public static void addCaliber(String caliber) {
        calibers.add(caliber);
    }
}
