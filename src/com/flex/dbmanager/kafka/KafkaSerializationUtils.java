package com.flex.dbmanager.kafka;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 7/6/2017.
 */
public final class KafkaSerializationUtils {
    private static Map<String, String> mapClassTypeToSerializer = new HashMap<String, String>();
    private static Map<String, String> mapClassTypeToDeserializer = new HashMap<String, String>();

    static {
        final String packageString = "org.apache.kafka.common.serialization.";
        mapClassTypeToSerializer.put("java.nio.ByteBuffer", packageString + "ByteBufferSerializer");
        mapClassTypeToSerializer.put("byte[]", packageString + "ByteArraySerializer");

        mapClassTypeToSerializer.put("java.lang.Integer", packageString + "IntegerSerializer");
        mapClassTypeToSerializer.put("int", packageString + "IntegerSerializer");

        mapClassTypeToSerializer.put("java.lang.Long", packageString + "LongSerializer");
        mapClassTypeToSerializer.put("long", packageString + "LongSerializer");

        mapClassTypeToSerializer.put("java.lang.Double", packageString + "DoubleSerializer");
        mapClassTypeToSerializer.put("double", packageString + "DoubleSerializer");

        mapClassTypeToSerializer.put("java.lang.String", packageString + "StringSerializer");


        mapClassTypeToDeserializer.put("java.nio.ByteBuffer", packageString + "ByteBufferDeserializer");
        mapClassTypeToDeserializer.put("byte[]", packageString + "ByteArrayDeserializer");

        mapClassTypeToDeserializer.put("java.lang.Integer", packageString + "IntegerDeserializer");
        mapClassTypeToDeserializer.put("int", packageString + "IntegerDeserializer");

        mapClassTypeToDeserializer.put("java.lang.Long", packageString + "LongDeserializer");
        mapClassTypeToDeserializer.put("long", packageString + "LongDeserializer");

        mapClassTypeToDeserializer.put("java.lang.Double", packageString + "DoubleDeserializer");
        mapClassTypeToDeserializer.put("double", packageString + "DoubleDeserializer");

        mapClassTypeToDeserializer.put("java.lang.String", packageString + "StringDeserializer");
    }

    private KafkaSerializationUtils() {
    }

    /**
     * Get deserializer class name by clazz.
     *
     * @param clazz
     * @return class name.
     */
    public static String getDeserializerClassName(Class<?> clazz) {
        if (clazz != null) {
            return mapClassTypeToDeserializer.get(clazz.getTypeName());
        }
        return null;
    }

    /**
     * Get serializer class name by clazz.
     *
     * @param clazz
     * @return class name.
     */
    public static String getSerializerClassName(Class<?> clazz) {
        if (clazz != null) {
            return mapClassTypeToSerializer.get(clazz.getTypeName());
        }
        return null;
    }
}