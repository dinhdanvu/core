package com.flex.dbmanager.kafka;

import java.util.concurrent.Callable;

public class HandlerProducerSequential implements Callable<String>{
	private String message;  
	private String topic;  
	private String messageKey;
	private int partition=-1;
	  
    public HandlerProducerSequential(String topic,String messageKey, String message) {  
        this.message = message;  
        this.topic = topic;  
        this.messageKey = messageKey;  
    }
    
    public HandlerProducerSequential(String topic,int partition, String message) {  
        this.message = message;  
        this.topic = topic;  
        this.partition = partition;  
    }
    
    public String call(){
    	try{
	    	KafkaProducerProvider kafkaProducerProvider = KafkaProducerProvider.getInstance();  
	    	kafkaProducerProvider.init(topic,1);  
	//    	System.out.println("当前线程:" + Thread.currentThread().getName() + ",获取的kafka实例:" + kafkaProducerProvider);
	    	Object future = null;
	    	if(partition>=0){
	    		future = kafkaProducerProvider.sendKafkaMessage(topic,partition,message);
	        }else{
	        	future = kafkaProducerProvider.sendKafkaMessage(topic,messageKey,message);
	        }
	    	return future.toString();
    	}catch (Exception e) {
    		return null;
		}
    }  
}
