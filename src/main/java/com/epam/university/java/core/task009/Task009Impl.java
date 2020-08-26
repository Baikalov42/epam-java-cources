package com.epam.university.java.core.task009;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Task009Impl implements Task009 {

    @Override
    public Collection<String> countWords(File sourceFile) {
        Set<String> strings = new HashSet<>();
        try {
            strings = Files.lines(Paths.get(sourceFile.toURI()))
                    .map(l -> l.replaceAll("[^a-zA-Z0-9’\\s]", ""))
                    .flatMap(x -> Arrays.stream(x.split(" ")))
                    .map(String::toUpperCase)
                    .map(String::trim)
                    .collect(Collectors.toSet());

        } catch (IOException e) {
            e.printStackTrace();
        }
        strings.remove("");
        return strings;
    }
}
