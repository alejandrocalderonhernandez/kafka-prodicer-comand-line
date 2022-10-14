package com.debuggeandoideas;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        var kafkaProducer = new Producer(KafkaConfigs.getInstance());
        var scanner = new Scanner(System.in);
        do {
            var line = scanner.next();
            var isValidLine = validFormat(line);
            if (isValidLine || line.equals("exit")) {
                if (line.equals("exit")) {
                    System.out.println("Finish");
                    kafkaProducer.closeProducer();
                    break;
                }
                var keyAndMessage = line.split(":");
                kafkaProducer.sendMessage(keyAndMessage[0], keyAndMessage[1]);
            } else {
                System.err.println("The formant must be string:string");
            }
        } while (true);

    }

    private static Boolean validFormat(String line) {
        var regex = Pattern.compile("\\w+:\\w+");
        var matcher = regex.matcher(line);
        return matcher.matches();
    }
}