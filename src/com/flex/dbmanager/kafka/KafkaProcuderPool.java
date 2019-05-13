package com.flex.dbmanager.kafka;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.flex.utils.Constants;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;

public class KafkaProcuderPool {
//	private static final String CONFIG_FILENAME = Constants.V4_CONFIG_FILENAME;
//	private static String CATEGORY_NAME = Constants.KAFKA_CATEGORY_NAME;
	
	private static ThreadPoolExecutor executor=null;
	static{
		String CONFIG_FILENAME = Constants.V4_CONFIG_FILENAME;
		String CATEGORY_NAME = Constants.KAFKA_CATEGORY_NAME;
		int backLog=100;
		TimeUnit unit = TimeUnit.MINUTES;
		int keepAlive=1;
		String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
		String configPath = Lib.combine(path, CONFIG_FILENAME);
		int corePoolSize = IniFile.readConfigFileString(configPath, CATEGORY_NAME, "core_pool_size", "500", int.class);
		int maxPoolSize = IniFile.readConfigFileString(configPath, CATEGORY_NAME, "max_pool_size", "400", int.class);
		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(backLog, true);
		executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAlive, unit, workQueue, handler);
	}
	public static void main(String[] args) {
		KafkaProcuderPool kafka = new KafkaProcuderPool();
//		kafka.sendMessageSequential("tp1", "x", "www");
		System.out.println("");
	}
	public KafkaProcuderPool(){
//		String CONFIG_FILENAME = Constants.V4_CONFIG_FILENAME;
//		String CATEGORY_NAME = Constants.KAFKA_CATEGORY_NAME;
//		int backLog=100;
//		TimeUnit unit = TimeUnit.MINUTES;
//		int keepAlive=1;
//		String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
//		String configPath = Lib.combine(path, CONFIG_FILENAME);
//		int corePoolSize = IniFile.readConfigFileString(configPath, CATEGORY_NAME, "core_pool_size", "500", int.class);
//		int maxPoolSize = IniFile.readConfigFileString(configPath, CATEGORY_NAME, "max_pool_size", "400", int.class);
//		RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
//		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(backLog, true);
//		executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAlive, unit, workQueue, handler);
//		System.out.println("");
//		executor = new (500, 400, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100, true), new ThreadPoolExecutor.DiscardOldestPolicy());
	}
	
	public void sendMessage(String topic, String key, String message) throws InterruptedException {
		executor.execute(new HandlerProducer(topic, key, message));
	}

	public String sendMessageSequential(String topic, String key, String message) {
		try{
			Future<String> future = executor.submit(new HandlerProducerSequential(topic, key, message));
			return future.get().toString();
		}catch (Exception e) {
			 return null;
		}
	}
	public String sendMessageSequential(String topic, int partition, String message) {
		try{
			Future<String> future = executor.submit(new HandlerProducerSequential(topic, partition, message));
			return future.get().toString();
		}catch (Exception e) {
			 return null;
		}
	}
}
