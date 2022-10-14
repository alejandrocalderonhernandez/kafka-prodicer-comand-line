package com.debuggeandoideas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class KafkaConfigs {

    private static KafkaConfigs kafkaConfigs;
    private final Properties configs = new Properties();

    private KafkaConfigs() {
        try {
            this.configs.load(new FileReader("src/main/resources/producer.properties"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Properties getConfigs() {
      return this.configs;
    }

    public static KafkaConfigs getInstance() {
        return (Objects.nonNull(kafkaConfigs)) ? kafkaConfigs : new KafkaConfigs();
    }
}
