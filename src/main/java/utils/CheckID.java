package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckID {
    public static boolean checkMaTheTV(String id) {
        String regex = "TTV\\d{4}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(id);

        return matcher.find();
    }
    
    public static boolean checkMaDG(String id) {
        String regex = "DG\\d{4}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(id);

        return matcher.find();
    }
    
    public static boolean checkMaNV(String id) {
        String regex = "NV\\d{4}";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(id);

        return matcher.find();
    }
}
