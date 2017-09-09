package com.readmain.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class PasswordUtils {

    private static Random random = new Random();

    public static final char[] UPPER_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static final char[] LOWER_LETTERS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
            'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static final char[] NUMBERS = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static final char[] SPECIAL_CHARS = {'!', '@', '$', '^'};

    public static String encodePassword(String password) {
        return encodePassword(password, "-Hello,snow!");
    }

    public static String encodePassword(String password, String salt) {
        return DigestUtils.md5Hex(password + salt);
    }

    public static String generateRandomPassWord() {
        String randomPassword = RandomStringUtils.random(2, UPPER_LETTERS) + RandomStringUtils.random(3, LOWER_LETTERS)
                + RandomStringUtils.random(2, NUMBERS) + RandomStringUtils.random(1, SPECIAL_CHARS);
        char[] passwordChars = randomPassword.toCharArray();
        shuffleArray(passwordChars);
        return new String(passwordChars);
    }

    public static void shuffleArray(char[] a) {
        int n = a.length; // the length of the array.
        for (int i = 0; i < n; i++) {
            int t = random.nextInt(n); // pick a random number 0 - the length.
            if (t == i) {              // if the random number is the loop counter
                if (i > 0) {             // check if we're at the first element.
                    t = random.nextInt(i); // pick another number between 0 - and the loop counter.
                } else {
                    t = a.length - 1;      // the end of the loop.
                }
            }
            a[i] ^= a[t];              // swap a[i] and a[t]
            a[t] ^= a[i];
            a[i] ^= a[t];
        }
    }

}
