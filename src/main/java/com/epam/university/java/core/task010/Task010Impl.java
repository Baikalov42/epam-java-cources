package com.epam.university.java.core.task010;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task010Impl implements Task010 {
    @Override
    public Map<String, Integer> countWordNumbers(File source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }

        Map<String, Integer> strings = new HashMap<>();
        try {
            strings = Files.lines(Paths.get(source.toURI()))
                    .map(l -> l.replaceAll("[^a-zA-Z0-9’\\s]", ""))
                    .flatMap(x -> Arrays.stream(x.split(" ")))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .collect(HashMap::new, (map, key) -> {
                        if (map.containsKey(key)) {
                            map.put(key, map.get(key) + 1);
                        } else {
                            map.put(key, 1);
                        }
                    }, HashMap::putAll);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return strings;
    }
}
