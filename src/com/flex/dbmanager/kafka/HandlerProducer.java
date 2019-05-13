package com.flex.dbmanager.kafka;


public class HandlerProducer implements Runnable{
	private String message;  
	private String topic;  
	private String messageKey;
	private int partition=-1;
	  
    public HandlerProducer(String topic,String messageKey, String message) {  
        this.message = message;  
        this.topic = topic;  
        this.messageKey = messageKey;  
    }
    
    public HandlerProducer(String topic,int partition, String message) {  
        this.message = message;  
        this.topic = topic;  
        this.partition = partition;  
    }
    @Override  
    public void run() {  
    	KafkaProducerProvider kafkaProducerProvider = KafkaProducerProvider  
                .getInstance();  
    	kafkaProducerProvider.init(topic,1);  
        System.out.println("当前线程:" + Thread.currentThread().getName()  
                + ",获取的kafka实例:" + kafkaProducerProvider); 
        if(partition>=0){
        	kafkaProducerProvider.sendKafkaMessage(topic,partition,message);
        }else{
        	kafkaProducerProvider.sendKafkaMessage(topic,messageKey,message);
        }
    }  
}
