package com.debuggeandoideas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Producer {

    private static Producer kafkaConfigs;
    private KafkaProducer<String, String> producer;

    private Producer() {
        try {
            var configs = new Properties();
            configs.load(new FileReader("src/main/resources/producer.properties"));
            this.producer = new KafkaProducer<>(configs);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void send(String key, String value) {
        try {
            var record = new ProducerRecord<>(TOPIC, PARTITION, key, value);
            this.producer.send(record);
        } catch (KafkaException e) {
            log.error(e);
            this.producer.close();
        }
    }

    public void closeProducer() {
        this.producer.close();
    }

    public static Producer getInstance() {
        return (Objects.nonNull(kafkaConfigs)) ? kafkaConfigs : new Producer();
    }

    private static final String TOPIC = "debuggeando-ideas";
    private static final Integer PARTITION = 0;
    private static final Logger log = LogManager.getLogger(Producer.class);

}
