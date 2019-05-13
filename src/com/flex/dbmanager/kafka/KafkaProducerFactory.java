package com.flex.dbmanager.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.utils.Exit;

import com.flex.utils.FLLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Create KafkaProducer object with same configure.
 * Created by Tran Gia Minh on 7/6/2017.
 */
public final class KafkaProducerFactory {
	private static FLLogger Logger = FLLogger.getLogger("kafkaFactory/log");
    private static KafkaProducerFactory instance = null;
    private static Map<String, KafkaProducer>  mapKafkaProducer = new HashMap<String, KafkaProducer>();
    private KafkaProducerFactory() {
    }

    /**
     * Get instance.
     *
     * @return KafkaProducerFactory.
     */
    public static final KafkaProducerFactory getInstance() {
        if (instance == null) {
            instance = new KafkaProducerFactory();
        }
        return instance;
    }

    /**
     * Create Kafka producer object with default config properties.
     *
     * @return KafkaProducer object
     */
    public KafkaProducer createKafkaProducer() {
        return createKafkaProducer(new KafkaProducerConfigBuilder().build());
    }

    /**
     * Create Kafka producer object with your config properties.
     *
     * @param configProperties
     * @return KafkaProducer object
     */
    public KafkaProducer createKafkaProducer(Properties configProperties) {
    	if(null==configProperties){
    		configProperties = new KafkaProducerConfigBuilder().build();
    	}
        KafkaProducer kafkaProducer = mapKafkaProducer.get(configProperties.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG));
        if(null==kafkaProducer){
        	try{
            kafkaProducer = new KafkaProducer(configProperties);
            mapKafkaProducer.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducer);
        	}catch (Exception e) {
        		Logger.error(e);
				System.exit(-1);
			}
        }
        return kafkaProducer;
    }
}