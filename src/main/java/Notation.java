import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Notation {
    String file;
    String column;
    private final static Map<String, Integer> map = Map.ofEntries(
            Map.entry("A", 1),
            Map.entry("B", 2),
            Map.entry("C", 3),
            Map.entry("D", 4),
            Map.entry("E", 5),
            Map.entry("F", 6),
            Map.entry("G", 7),
            Map.entry("H", 8)
    );

    public Notation(String file, String column) {
        this.file = file.toUpperCase().trim();
        this.column = column.toUpperCase().trim();
    }

    public int fileInt() {
        Pattern pattern = Pattern.compile("^[A-H]{1}");
        Matcher matcher = pattern.matcher(file);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("File input <%s> was not betweeen A-H", file));
        }
        return map.get(file);
    }

    public int columnInt() {
        Pattern pattern = Pattern.compile("^[1-8]{1}");
        Matcher matcher = pattern.matcher(column);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("Column input <%s> was not betweeen 1-8", column));
        }
        return Integer.parseInt(column);
    }

    public boolean isValid() {
        int fileInt = fileInt();
        int colInt = columnInt();

        return fileInt > 0 && fileInt < 9 && colInt > 0 && colInt < 9;
    }

    public String toString() {
        return String.format("(%s, %s)", file, column);
    }
}
