package etc;

public class StringCheck {

    // 半角数字のみならTrue
    public boolean checkD(String string) {
        if (string.matches("[\\d]+$")) {
            return true;
        } else {
            return false;
        }
    }

    //半角英数字のみならTrue
    public boolean checkW(String string) {
        if (string.matches("[\\w]+$")) {
            return true;
        } else {
            return false;
        }
    }

    //全角のみならTrue
    public boolean checkZENKAKU(String string) {
        if (string.matches("[^\\x00-\\x7F]+$")) {
            return true;
        } else {
            return false;
        }
    }

    //半角英数字,全角カタカナのみならTrue
    public boolean checkPhonetic(String string) {
        if (string.matches("[\\wァ-ヶ&;]+$")) {
            return true;
        } else {
            return false;
        }
    }

    //記号が含まれないならTrue
    public boolean checkNotSymbols(String string) {
        if (string.matches("([^\\x00-\\x7F]|[\\w&;])+$")) {
            return true;
        } else {
            return false;
        }
    }

    //全角全て、半角英数字、一部記号のみならTrue
    public boolean checkFreeText(String string) {
        if (string.matches("([^\\x00-\\x7F]|[-^\\w&;.,()</>?!@=:#*+{}\\[\\]])+$")) {
            return true;
        } else {
            return false;
        }
    }
}
