package com.flex.dbmanager.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * Create KafkaConsumer object with same configure.
 * Created by Tran Gia Minh on 7/6/2017.
 */
public final class KafkaConsumerFactory {
    private static KafkaConsumerFactory instance = null;

    private KafkaConsumerFactory() {
    }

    /**
     * Get instance.
     *
     * @return KafkaConsumerFactory.
     */
    public static final KafkaConsumerFactory getInstance() {
        if (instance == null) {
            instance = new KafkaConsumerFactory();
        }
        return instance;
    }

    /**
     * Create Kafka consumer object with default config properties.
     *
     * @return KafkaConsumer object
     */
    public KafkaConsumer createKafkaConsumer() {
        Properties properties = new KafkaConsumerConfigBuilder().build();
        return new KafkaConsumer(properties);
    }

    /**
     * Create Kafka consumer object with your config properties.
     *
     * @param configProperties
     * @return KafkaConsumer object
     */
    public KafkaConsumer createKafkaConsumer(Properties configProperties) {
        Properties properties = new Properties();
        if (configProperties != null) {
            properties.putAll(configProperties);
        }
        return new KafkaConsumer(properties);
    }
}