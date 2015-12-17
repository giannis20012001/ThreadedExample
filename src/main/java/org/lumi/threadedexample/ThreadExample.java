package org.lumi.threadedexample;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

/**
 * Created by John Tsantilis (A.K.A lumi) on 16/12/2015.
 */
public class ThreadExample {
    public static void main(String[] args) {
        URL url1 = ThreadExample.class.getClassLoader().getResource("big1.txt");
        URL url2 = ThreadExample.class.getClassLoader().getResource("big2.txt");
        URL url3 = ThreadExample.class.getClassLoader().getResource("big3.txt");

        try {
            Path path1 = Paths.get(url1.toURI());
            Path path2 = Paths.get(url2.toURI());
            Path path3 = Paths.get(url3.toURI());
            Stream<String> stream1 = Files.lines(path1, Charset.forName("ISO-8859-1"));
            Stream<String> stream2 = Files.lines(path2, Charset.forName("ISO-8859-1"));
            Stream<String> stream3 = Files.lines(path3, Charset.forName("ISO-8859-1"));

            long start = System.currentTimeMillis();
            Stream <String> finalStream = Stream.concat(stream1, Stream.concat(stream2, stream3));
            Map<Object, Long> words = finalStream.parallel()
                    .map(line->line.split("\\W"))
                    .flatMap(Arrays::stream)
                    .map(String::toLowerCase)
                    .collect(groupingByConcurrent(s -> s, counting()));

            stream1.close();
            stream2.close();
            stream3.close();
            finalStream.close();

            System.out.println("time taken concurrent:"
                    + (System.currentTimeMillis() - start));

            System.out.println("concurrent words =  " + words);

        }
        catch(Exception ex) {
            System.out.println("Exeption: " + ex);

        }


    }

}