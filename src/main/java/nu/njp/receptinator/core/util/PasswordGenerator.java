package nu.njp.receptinator.core.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Andreas on 2015-12-20.
 */
public class PasswordGenerator {
    private static SecureRandom secureRandom = new SecureRandom();
    private static Random random;
    private static String numbersAndCharacters= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";

    public static String generatePassword() {
        return new BigInteger(130, secureRandom).toString(32);
    }

    public static String generateSalt(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i < 10; i++) {
            stringBuilder.append(numbersAndCharacters.charAt(random.nextInt(numbersAndCharacters.length())));
        }
        return stringBuilder.toString();
    }
}
