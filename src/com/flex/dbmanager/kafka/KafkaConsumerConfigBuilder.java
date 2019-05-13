package com.flex.dbmanager.kafka;

import com.flex.utils.Constants;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * Builder to build config properties for kafka consumer. Default key & value class type is string.
 * Created by Tran Gia Minh on 7/7/2017.
 */
public final class KafkaConsumerConfigBuilder {
    private static Properties DEFAULT_PROPERTIES = null;
    private Properties properties = new Properties();

    /**
     * Default constructor. Init with default config properties. Default key & value class type is string.
     */
    public KafkaConsumerConfigBuilder() {
        properties.putAll(getDefaultProperties());
    }

    /**
     * Read file ini & init all config properties for Kafka consumer.
     *
     * @return Properties
     */
    private static final Properties initConfigProperties() {
        final String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
        final String configPath = Lib.combine(path, Constants.V4_CONFIG_FILENAME);

        Properties properties = IniFile.readConfigsInSectionName(configPath, Constants.KAFKA_CONSUMER_CATEGORY_NAME);
        if (properties == null) {
            properties = new Properties();
        }

        if (!properties.contains(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            final String servers = IniFile.readConfigFileString(configPath, Constants.KAFKA_CATEGORY_NAME, "servers", "192.168.10.36:9092", String.class);
            if (servers != null && !servers.isEmpty()) {
                properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
            }
        }

        // default key & value class type is string
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getDeserializerClassName(String.class));
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getDeserializerClassName((String.class)));
        return properties;

//        String servers = IniFile.readConfigFileString(configPath, Constants.KAFKA_CATEGORY_NAME, "servers", "127.0.0.1:9092", String.class);
//        Properties props = new Properties();
//        props.put("bootstrap.servers", servers);
////        props.put("group.id", "group-1");
//
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//
//        props.put("auto.offset.reset", "earliest");
//        props.put("session.timeout.ms", "30000");
//
////        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
////        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        return props;
    }

    /**
     * Get default config properties of kafka consumer.
     *
     * @return Properties
     */
    public static Properties getDefaultProperties() {
        if (DEFAULT_PROPERTIES == null) {
            DEFAULT_PROPERTIES = initConfigProperties();
        }
        return DEFAULT_PROPERTIES;
    }

    /**
     * Set bootstrapServer config.
     *
     * @param bootstrapServer
     * @return KafkaConsumerConfigBuilder
     */
    public KafkaConsumerConfigBuilder setBootstrapServer(String bootstrapServer) {
        this.properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return this;
    }

    /**
     * Set groupId config.
     *
     * @param groupId
     * @return KafkaConsumerConfigBuilder
     */
    public KafkaConsumerConfigBuilder setGroupId(String groupId) {
        this.properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return this;
    }

    /**
     * Set key deserializer config.
     *
     * @param clazz
     * @return KafkaConsumerConfigBuilder
     */
    public KafkaConsumerConfigBuilder setKeyDeserializerClass(Class<?> clazz) {
        this.properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getDeserializerClassName(clazz));
        return this;
    }

    /**
     * Set value deserializer config.
     *
     * @param clazz
     * @return KafkaConsumerConfigBuilder
     */
    public KafkaConsumerConfigBuilder setValueDeserializerClass(Class<?> clazz) {
        this.properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getDeserializerClassName(clazz));
        return this;
    }

    /**
     * Build config properties for kafka consumer.
     *
     * @return Properties
     */
    public Properties build() {
        return properties;
    }
}