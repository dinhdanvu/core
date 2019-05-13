package com.flex.dbmanager.kafka;

import com.flex.utils.Constants;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;

/**
 * Builder to build config properties for kafka producer. Default key & value class type is string.
 * Created by Tran Gia Minh on 7/7/2017.
 */
public final class KafkaProducerConfigBuilder {
    private static Properties DEFAULT_PROPERTIES = null;
    private Properties properties = new Properties();

    /**
     * Default constructor. Init with default config properties. Default key & value class type is string.
     */
    public KafkaProducerConfigBuilder() {
        properties.putAll(getDefaultProperties());
    }

    /**
     * Read file ini & init all config properties for Kafka producer.
     *
     * @return Properties
     */
    private static final Properties initConfigProperties() {
        final String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
        final String configPath = Lib.combine(path, Constants.V4_CONFIG_FILENAME);

        Properties properties = IniFile.readConfigsInSectionName(configPath, Constants.KAFKA_PRODUCER_CATEGORY_NAME);
        if (properties == null) {
            properties = new Properties();
        }

        if (!properties.contains(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            final String servers = IniFile.readConfigFileString(configPath, Constants.KAFKA_CATEGORY_NAME, "servers", "127.0.0.1:9092", String.class);
            if (servers != null && !servers.isEmpty()) {
                properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
            }
        }

        // default key & value class type is string
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getSerializerClassName(String.class));
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getSerializerClassName((String.class)));
        return properties;

//        String servers = IniFile.readConfigFileString(configPath, Constants.KAFKA_CATEGORY_NAME, "servers", "127.0.0.1:9092", String.class);
//        Properties props = new Properties();
//        props.put("bootstrap.servers", servers);
//
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//
////        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
////        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        return props;
    }

    /**
     * Get default config properties of kafka producer.
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
     * @return KafkaProducerConfigBuilder
     */
    public KafkaProducerConfigBuilder setBootstrapServer(String bootstrapServer) {
        this.properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return this;
    }

    /**
     * Set key serializer config.
     *
     * @param clazz
     * @return KafkaProducerConfigBuilder
     */
    public KafkaProducerConfigBuilder setKeySerializerClass(Class<?> clazz) {
        this.properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getSerializerClassName(clazz));
        return this;
    }

    /**
     * Set value serializer config.
     *
     * @param clazz
     * @return KafkaProducerConfigBuilder
     */
    public KafkaProducerConfigBuilder setValueSerializerClass(Class<?> clazz) {
        this.properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaSerializationUtils.getSerializerClassName(clazz));
        return this;
    }

    /**
     * Build config properties for kafka producer.
     *
     * @return Properties
     */
    public Properties build() {
        return properties;
    }
}