package etc;

import java.security.SecureRandom;

public class CreateSalt {
    private String[] RANDOM_CODE = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };
    private int LENGTH = 20;

    public String getrSalt(){
        String result = "";
        for(int i = 0;i < LENGTH;i++){
            int index = new SecureRandom().nextInt(RANDOM_CODE.length);
            result += RANDOM_CODE[index];
        }
        return result;
    }
}
