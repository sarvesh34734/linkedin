package dev.svyas.linkedin.user_service;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtils {

    public static String encryptPass(String rawPw){
        return BCrypt.withDefaults().hashToString(12, rawPw.toCharArray());
    }

    public static boolean verifyPw(String rawPw,String hashPw){
        return BCrypt.verifyer().verify(rawPw.toCharArray(),hashPw.toCharArray()).verified;
    }


}
