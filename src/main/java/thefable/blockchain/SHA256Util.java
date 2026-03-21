package thefable.blockchain;

import java.security.MessageDigest;

public class SHA256Util {

    public static String hash(String input) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] bytes = sha256.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for(byte b: bytes) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
