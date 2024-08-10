package com.github.macwille.chess;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ClassicNotation implements Notation {
    private final String rank;
    private final String file;
    private final Map<String, Integer> filesMap;

    public ClassicNotation(String file, String rank) {
        this.rank = rank.toUpperCase().trim();
        this.file = file.toUpperCase().trim();
        this.filesMap = Map.ofEntries(
                Map.entry("A", 1),
                Map.entry("B", 2),
                Map.entry("C", 3),
                Map.entry("D", 4),
                Map.entry("E", 5),
                Map.entry("F", 6),
                Map.entry("G", 7),
                Map.entry("H", 8)
        );
    }

    public int fileInt() {
        return filesMap.get(file);
    }

    public int rankInt() {
        return Integer.parseInt(rank);
    }

    public boolean isValid() {
        Pattern filePattern = Pattern.compile("^[A-H]{1}");
        Matcher fileMatcher = filePattern.matcher(file);
        Pattern rankPattern = Pattern.compile("^[1-8]{1}");
        Matcher colMatcher = rankPattern.matcher(rank);
        return fileMatcher.find() && colMatcher.find();
    }

    @Override
    public String toString() {
        return String.format("(%s%s)", file, rank);
    }
}
