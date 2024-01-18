package Utilidades;

import java.util.Random;

/**
 *
 * @author Emanuel
 */
public class CaptchaGenerador {
    
    public static String generateCaptcha() {
        String[] alpha = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(alpha.length);
            code.append(alpha[randomIndex]);
        }

        return code.toString();
    }

    public static boolean validateCaptcha(String generatedCode, String userInput) {
        return generatedCode.equals(removeSpaces(userInput));
    }

    public static String removeSpaces(String input) {
        return input.replaceAll(" ", "");
    }
    
}
