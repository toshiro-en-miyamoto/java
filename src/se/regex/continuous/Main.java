package se.regex.continuous;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

class Main
{
    public static void main(String[] args)
    {
        final Pattern CONT_LINE = Pattern.compile("^(.*)\\\\\\h*$");
        final String filename = "data/continuous/continuousLines.txt";

        try {
            Files.lines(Paths.get(filename))
                .collect(
                    ArrayList<String>::new,
                    (ArrayList<String> list, String line) -> {
                        String next = line;
                        if(!list.isEmpty()) {
                            String prev = list.get(list.size()-1);
                            Matcher matcher = CONT_LINE.matcher(prev);
                            if(matcher.find()) {
                                list.remove(list.size()-1);
                                next = matcher.group(1) + line;
                            }
                        }
                        list.add(next);
                    },
                    ArrayList<String>::addAll
                )
                .stream()
                .forEach(o -> System.out.println(o));
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}