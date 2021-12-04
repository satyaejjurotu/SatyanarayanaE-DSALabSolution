package services;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CredentialService {
    /// Email constants
    private static String emailSuffix = "company.com";
    //// Password constants
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String OTHER_PUNCTUATION = "!@#&()–[{}]:;',?/*";
    private static final String OTHER_SYMBOL = "~$^+=<>";
    private static final String OTHER_SPECIAL = OTHER_PUNCTUATION + OTHER_SYMBOL;
    private static final String PASSWORD_ALLOW =
    CHAR_LOWERCASE + CHAR_UPPERCASE + DIGIT + OTHER_SPECIAL;


    /// PASSWORD GENERATOR
    private static SecureRandom random = new SecureRandom();
    public static String generateStrongPassword(int passwordLength) {
        StringBuilder result = new StringBuilder(passwordLength);

        // at least 2 chars (lowercase)
        String strLowerCase = generateRandomString(CHAR_LOWERCASE, 2);
        result.append(strLowerCase);

        // at least 2 chars (uppercase)
        String strUppercaseCase = generateRandomString(CHAR_UPPERCASE, 2);
        result.append(strUppercaseCase);

        // at least 2 digits
        String strDigit = generateRandomString(DIGIT, 2);
        result.append(strDigit);

        // at least 2 special characters (punctuation + symbols)
        String strSpecialChar = generateRandomString(OTHER_SPECIAL, 2);
        result.append(strSpecialChar);

         // remaining, just random
        String strOther = generateRandomString(PASSWORD_ALLOW, passwordLength - 8);
        result.append(strOther);

        String password = result.toString();
       
        return shuffleString(password);
    }

    // generate a random char[], based on `input`
    private static String generateRandomString(String input, int size) {

        if (input == null || input.length() <= 0)
            throw new IllegalArgumentException("Invalid input.");
        if (size < 1) throw new IllegalArgumentException("Invalid size.");

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // produce a random order
            int index = random.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }

    // for final password, make it more random
    private static String shuffleString(String input) {
        List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        // java 8
        return result.stream().collect(Collectors.joining());
    }
    
    /// EMAIL GENERATOR
    public static String generateEmailForUser(String firstName, String lastName, String department){
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department.toLowerCase().replace(" ", "_") + "."+ emailSuffix;
    }

    /// SHOW CREDENTIALS
    public static void showCredentials(String email, String password, String department, String firstName) {
        String format = "\nDear %s your generated credentials are as follows\n\nEmail ==> %s\nPassword ==> %s\n\n";
        String result = String.format(format, firstName, email, password);
        System.out.println(result);
    }
}
