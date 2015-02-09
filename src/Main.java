import objects.Theme;

/**
 * Created by Seba on 2015-01-23.
 */

public class Main {
    public static void main(String[] args) {
        new Frame("Java tower defense", new Board(20, 20, new Theme(Theme.Type.GREEN_IS_GOOD)));
    }
}
