package etc;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    public String sha256(String orgStr) {
        String hashed = "";
        String hashedStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(orgStr.getBytes());
            BigInteger bi = new BigInteger(1, hash);
            hashedStr = String.format("%0" + (hash.length << 1) + "x", bi);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashedStr;
    }
}
