package com.flex.dbmanager.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;

public final class KafkaProducerProvider {
	private static FLLogger Logger = FLLogger.getLogger("kafka/log");
	private static KafkaProducer<String, String> kafkaProducer;
	private String topic;
	private int retry;


	private static final String CONFIG_FILENAME = Constants.V4_CONFIG_FILENAME;
	// Cấu hình tracking category name
	private static String CATEGORY_NAME = Constants.KAFKA_CATEGORY_NAME;
	

	private KafkaProducerProvider() {

	}
	private Properties initProperties(){
		String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
		// String path = Lib.getAbsolutePath(V4Worker.class);
		// String path = new
		// File(absolutePath).getParentFile().getAbsolutePath();
		String configPath = Lib.combine(path, CONFIG_FILENAME);

		String servers = IniFile.readConfigFileString(configPath, CATEGORY_NAME, "servers", "127.0.0.1:9092", String.class);
		Properties props = new Properties();
	    props.put("bootstrap.servers", servers);
	    props.put("acks", "all");
	    props.put("retries", 0);
	    props.put("batch.size", 16384);
	    props.put("linger.ms", 1);
	    props.put("buffer.memory", 33554432);
	    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    return props;
	}
	/**
	 * 静态内部类
	 * 
	 * @author tanjie
	 * 
	 */
	private static class LazyHandler {

		private static final KafkaProducerProvider instance = new KafkaProducerProvider();
	}

	/**
	 * 单例模式,kafkaProducer是线程安全的,可以多线程共享一个实例
	 * 
	 * @return
	 */
	public static final KafkaProducerProvider getInstance() {
		return LazyHandler.instance;
	}

	/**
	 * kafka生产者进行初始化
	 * 
	 * @return KafkaProducer
	 */
	public void init(String topic, int retry) {
		this.topic = topic;
		this.retry = retry;
		if (null == kafkaProducer) {
			Properties props = initProperties();
			kafkaProducer = new KafkaProducer<String, String>(props);
		}
	}

	/**
	 * 通过kafkaProducer发送消息
	 * 
	 * @param topic
	 *            消息接收主题
	 * @param partitionNum
	 *            哪一个分区
	 * @param retry
	 *            重试次数
	 * @param message
	 *            具体消息值
	 */
	public Object sendKafkaMessage(final String topic, final int partition, final String message) {
		/**
		 * 1、如果指定了某个分区,会只讲消息发到这个分区上 2、如果同时指定了某个分区和key,则也会将消息发送到指定分区上,key不起作用
		 * 3、如果没有指定分区和key,那么将会随机发送到topic的分区中 4、如果指定了key,那么将会以hash<key>的方式发送到分区中
		 */
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, partition, "", message);
		// send方法是异步的,添加消息到缓存区等待发送,并立即返回，这使生产者通过批量发送消息来提高效率
		// kafka生产者是线程安全的,可以单实例发送消息
		return kafkaProducer.send(record, new Callback() {
			public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
				if (null != exception) {
					Logger.error("kafkaProducer send error: ", exception);
					retryKakfaMessage(record);
				}
			}
		});
	}

	public Object sendKafkaMessage(final String topic, final String keyString, final String message) {
		/**
		 * 1、如果指定了某个分区,会只讲消息发到这个分区上 2、如果同时指定了某个分区和key,则也会将消息发送到指定分区上,key不起作用
		 * 3、如果没有指定分区和key,那么将会随机发送到topic的分区中 4、如果指定了key,那么将会以hash<key>的方式发送到分区中
		 */
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, keyString, message);
		// send方法是异步的,添加消息到缓存区等待发送,并立即返回，这使生产者通过批量发送消息来提高效率
		// kafka生产者是线程安全的,可以单实例发送消息
		return kafkaProducer.send(record, new Callback() {
			public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
				if (null != exception) {
					Logger.error("kafkaProducer send error: ", exception);
					retryKakfaMessage(record);
				}
			}
		});
	}

	/**
	 * 当kafka消息发送失败后,重试
	 * 
	 * @param retryMessage
	 */
	private void retryKakfaMessage(final ProducerRecord<String, String> record) {
		// ProducerRecord<String, String> record = new ProducerRecord<String,
		// String>(
		// topic, random.nextInt(3), "", retryMessage);
		for (int i = 1; i <= retry; i++) {
			try {
				kafkaProducer.send(record);
				return;
			} catch (Exception e) {

				Logger.error("kafkaProducer re-send error: ", e);
				retryKakfaMessage(record);
			}
		}
	}

	/**
	 * kafka实例销毁
	 */
	public void close() {
		if (null != kafkaProducer) {
			kafkaProducer.close();
		}
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

}