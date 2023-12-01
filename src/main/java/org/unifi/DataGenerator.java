package org.unifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.unifi.model.MatchStats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class DataGenerator {

    public static void main(String[] args) {

        MatchStats randomMatchStats = new MatchStats(
            "Milan", "Inter", "/assets/imgs/Milan.png", "/assets/imgs/Inter.png");

        for (long stop = System.nanoTime() + TimeUnit.SECONDS.toNanos(91); stop > System.nanoTime();) {
            try {

                Thread.sleep(3000);
                randomMatchStats.timePassed += 3;
                randomMatchStats = randomDataGenerator(randomMatchStats);
                System.out.println(randomMatchStats);
                sendToKafka(randomMatchStats);
            
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        System.out.println("Random data posted successfully!");
    }

    private static MatchStats randomDataGenerator(MatchStats stats) {
        
        Random random = new Random();
        // random.nextInt(max - min + 1) + min
        
        // random change of possession +/- 3 every second
        Integer randomPossession = random.nextInt(3 + 3 + 1) - 3;
        stats.ballPossessionH += randomPossession;
        stats.ballPossessionA -= randomPossession;

        // random score change 5% (1,5 di media)
        Integer scorePercentageH = random.nextInt(100);
        if (scorePercentageH < 5) {
            stats.scoreH += 1;
            stats.shotsOnTargetH += 1;
        }
        Integer scorePercentageA = random.nextInt(100);
        if (scorePercentageA < 5) {
            stats.scoreA += 1;
            stats.shotsOnTargetA += 1;
        }

        if (random.nextInt(100) < 10) {
            stats.shotsOnTargetH += 1;
        }
        if (random.nextInt(100) < 10) {
            stats.shotsOnTargetA += 1;
        }

        // random fouls 20% (6 di media)
        if (random.nextInt(100) < 15) {
            stats.foulsH += 1;
        }
        if (random.nextInt(100) < 15) {
            stats.foulsA += 1;
        }

        // random yellow cards Off/On target 10% (3 di media)
        if (random.nextInt(100) < 10) {
            stats.yellowCardsH += 1;
        }
        if (random.nextInt(100) < 10) {
            stats.yellowCardsA += 1;
        }

        stats.totalPassesH += random.nextInt(30);
        stats.totalPassesA += random.nextInt(30);
        stats.completedPassesH += random.nextInt(24);
        stats.completedPassesA += random.nextInt(24);

        return stats;
    }

    private static void sendToKafka(MatchStats stats) {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String prettyStats;
        
        try {
            
            prettyStats = ow.writeValueAsString(stats);
            System.out.println(prettyStats);
            
            // creating connection to quarkus-kafka
            URL url = new URL ("http://localhost:8080/extStats/external");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
                
            OutputStream os = connection.getOutputStream();
            byte[] input = prettyStats.getBytes("utf-8");
            os.write(input, 0, input.length);
        
            BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8")
                );
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }    

        } catch (IOException e) {
            e.printStackTrace();
        }
    }  
    
}
