package com.debuggeandoideas;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;

public class Producer {

    private KafkaConfigs kafkaConfigs;
    private KafkaProducer<String, String> producer;

    public Producer(KafkaConfigs kafkaConfigs) {
        this.kafkaConfigs = kafkaConfigs;
        producer = new KafkaProducer<>(kafkaConfigs.getConfigs());
    }

    public void sendMessage(String key, String value) {
        try {
            var record = new ProducerRecord<>(TOPIC, PARTITION, key, value);
            this.producer.send(record);
        } catch (KafkaException e) {
            System.err.println(e.getMessage());
            this.producer.close();
        }
    }


    public void closeProducer() {
        this.producer.close();
    }

    private static final String TOPIC = "debuggeando-ideas";
    private static final Integer PARTITION = 0;

}
