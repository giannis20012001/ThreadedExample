package org.lumi.threadedexample;


import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis (A.K.A lumi) on 16/12/2015.
 */
public class ThreadExample {
    private static final Logger logger = Logger.getLogger(ThreadExample.class.getName());

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            Map<String,Long> counts = ForkJoinWordCount.wordCount("/home/ubuntu/resources");
            /*logger.info("Counts" + counts);*/

            System.out.println("time taken concurrent:" + (System.currentTimeMillis() - start));

            /*System.out.println("concurrent words =  " + counts);*/

        }
        catch (IOException ex) {
            System.out.println("Exeption: " + ex);

        }

    }

}