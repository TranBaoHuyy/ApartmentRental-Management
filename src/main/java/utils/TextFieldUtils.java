package utils;

public class TextFieldUtils {
	public static boolean isPositiveNumber(String text) {
        try {
            double value = Float.parseFloat(text);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	public static boolean isPositiveNumberInt(String text) {
        try {
            int value = Integer.parseInt(text);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
