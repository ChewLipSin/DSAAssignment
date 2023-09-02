package utility;
/**
 *
 * @author Lim Yi Leong
 */
public enum ConsoleColor {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BRIGHTRED("\u001B[31;1m"),
    BRIGHTGREEN("\u001B[32;1m"),
    BRIGHTYELLOW("\u001B[33;1m"),
    BRIGHTBLUE("\u001B[34;1m"),
    CYAN("\u001b[36m"),
    MAGENTA("\u001B[35m"),
    BRIGHTMAGENTA("\u001B[35;1m"),
    WHITE("\u001B[37m"),
    BRIGHTWHITE("\u001B[37;1m");

    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
