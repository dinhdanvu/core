package com.flex.dbmanager.attributes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flex.utils.Lib;

public class RedisAttributes {
	/**
	 * lay khoa chinh
	 * @param clazz
	 * @return
	 */
	public static <T> String getKey(Class<T> clazz) {
		RedisKeyAttribute indexAnno = (RedisKeyAttribute) clazz
				.getAnnotation(RedisKeyAttribute.class);
		if(indexAnno==null) return null;
		return indexAnno.key();
	}
	public static <T> String getAliasClassName(Class<T> clazz) {
		RedisClassNameAliasAttribute classAnno = (RedisClassNameAliasAttribute) clazz
				.getAnnotation(RedisClassNameAliasAttribute.class);
		if(classAnno==null) return null;
		return classAnno.name();
	}
	/**
	 * lay danh sach index field
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String[] getIndex(Class<T> clazz) {
		RedisIndexAttribute indexAnno = (RedisIndexAttribute) clazz
				.getAnnotation(RedisIndexAttribute.class);
		if(indexAnno==null) return null;
		return indexAnno.indexs();
	}
	/**
	 * lay danh sach key khong index redis
	 * @param clazz
	 * @return
	 */
	public static <T> String[] getIgnoreKeys(Class<T> clazz) {
		RedisIgnoreKeyAttributes indexAnno = (RedisIgnoreKeyAttributes) clazz
				.getAnnotation(RedisIgnoreKeyAttributes.class);
		if(indexAnno==null) return null;
		return indexAnno.ignoreKey();
	}
	/**
	 * lay value cua khoa chinh
	 * 
	 * @param item
	 * @return
	 */
	public static <T> String getKeyValue(T item) {
		if(null==item){
			return null;
		}
		String key = getKey(item.getClass());
		Object valueObj=getFieldValue(item, key);
		return  getStringValue(valueObj);
	}
	/**
	 * get method by field name
	 * @param fieldName
	 * @return
	 */
	public static <T> Method findGetMethod(String fieldName,Class<T> clazz){
		Method[] methods= clazz.getDeclaredMethods();
		if(methods!=null && methods.length>0){
			for (Method method : methods) {
				String methodName=method.getName().toLowerCase();
				//method bat dau bang is thi thuong la boolean
				if((methodName.indexOf("get")==0 || methodName.indexOf("is")==0) && methodName.toLowerCase().indexOf(fieldName.toLowerCase())!=-1){
					return method;
				}
			}
		}
		return null;
	}
	/**
	 * get set method 
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	public static <T> Method findSetMethod(String fieldName,Class<T> clazz){
		Method[] methods= clazz.getDeclaredMethods();
		if(methods!=null && methods.length>0){
			for (Method method : methods) {
				String methodName=method.getName().toLowerCase();
				//method bat dau bang is thi thuong la boolean
				if(methodName.indexOf("set")!=-1  && methodName.toLowerCase().indexOf(fieldName.toLowerCase())!=-1){
					return method;
				}
			}
		}
		return null;
	}
	/**
	 * lay gia tri cua field
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws NullPointerException 
	 */
	public static <T> T getFieldValue(Object obj, String fieldName) throws NullPointerException {
		Class clazz = obj.getClass();
		if(Lib.isBlank(fieldName)){
			throw new NullPointerException("getFieldValue, fieldName cannot be null: "+obj.getClass().getName());
		}
		String methodName = "get" + firstCharCapitalize(fieldName);
		Method method = null;
		try {
			method = clazz.getMethod(methodName, null);
		} catch (NoSuchMethodException e1) {
			method=findGetMethod(fieldName, clazz);

		} catch (SecurityException e1) {
			method=findGetMethod(fieldName, clazz);
		}
		if (method != null) {
			try {
				return (T) method.invoke(obj, null);

			} catch (IllegalAccessException e) {
				return null;
			} catch (IllegalArgumentException e) {
				return null;
			} catch (InvocationTargetException e) {
				return null;
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * gan gia tri cho field
	 * 
	 * @param <T>
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T> String setFieldValue(Object obj, String fieldName,
			String value) {
		Class clazz = obj.getClass();

		String methodName = "set" + firstCharCapitalize(fieldName);
		Method method = null;
		Field field = null;
		try {
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			method = clazz.getDeclaredMethod(methodName, field.getType());

		} catch (NoSuchMethodException e1) {
			return null;
		} catch (SecurityException e1) {
			return null;
		}
		if (method != null) {
			try {
			
				Object t = convertTo(value, convertToClassT(field.getType()));
				method.invoke(obj, t);

			} catch (IllegalAccessException e) {
				return null;
			} catch (IllegalArgumentException e) {
				return null;
			} catch (InvocationTargetException e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * convert string to object bat ky
	 * 
	 * @param value
	 * @param classTo
	 * @return
	 */
	public static <T> T convertTo(String value, Class<T> classTo) {
		if (classTo.getName().equals(int.class.getName())) {
			return classTo.cast(Lib.strToInteger(value));
		}

		if (classTo.getName().equals(Integer.class.getName())) {
			return classTo.cast(Lib.strToInteger(value));
		}

		if (classTo.getName().equals(long.class.getName())) {
			return classTo.cast(Lib.strToLong(value));
		}

		if (classTo.getName().equals(Long.class.getName())) {
			return classTo.cast(Lib.strToLong(value));
		}

		if (classTo.getName().equals(double.class.getName())) {
			return classTo.cast(Lib.strToDouble(value));
		}
		if (classTo.getName().equals(Double.class.getName())) {
			return classTo.cast(Lib.strToDouble(value));
		}
		if (classTo.getName().equals(float.class.getName())) {
			return classTo.cast(Lib.strToFloat(value));
		}
		if (classTo.getName().equals(Float.class.getName())) {
			return classTo.cast(Lib.strToFloat(value));
		}
		if (classTo.getName().equals(String.class.getName())) {
			return classTo.cast(value);
		}
		if (classTo.getName().equals(Boolean.class.getName())) {
			if("true".equals(value) || "1".equals(value)){
				return classTo.cast(true);	
			}
			if("false".equals(value) || "0".equals(value)){
				return classTo.cast(false);	
			}
			return classTo.cast(false);
		}
		if (classTo.getName().equals(boolean.class.getName())) {
			return classTo.cast(value);
		}
		if (classTo.getName().equals(Date.class.getName())) {

			return classTo.cast(Lib.stringToDate(value, "dd/MM/yyyy HH:mm:ss"));
		}
		if (classTo.getName().equals(List.class.getName())) {

			return Lib.convertJson2Object(value, classTo);

		}
		try {
			return Lib.convertJson2Object(value, classTo);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * uppercase ky tu dau tien
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharCapitalize(String s) {
		if (s.length() == 0)
			return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	/**
	 * lay danh sach field trong class
	 * @param clazz
	 * @return
	 */
	public static <T> List<String> getFieldList(Class<T> clazz) {
		List<String> fieldList = new ArrayList<String>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			fieldList.add(field.getName());
		}
		return fieldList;
	}
	/**
	 * lay danh sách field cua lop hien tai khong lay lop no thua ke
	 * @param item
	 * @return
	 */
	public static <T> Map<String, String> getFieldValueMap(T item) {
		Map<String, String> keyValues = new HashMap<String, String>();
		List<String> fieldList = new ArrayList<String>();
		Field[] fields = item.getClass().getDeclaredFields();
		String[] ignoreKeys = getIgnoreKeys(item.getClass());
		for (Field field : fields) {
			if (!Lib.isBlank(field.getName())) {
				if(null!=ignoreKeys && ignoreKeys.length>0){
					if(Arrays.asList(ignoreKeys).contains(field.getName())){
						continue;
					}
				}
				fieldList.add(field.getName());
				keyValues.put(field.getName(),
						getStringValue(getFieldValue(item, field.getName())));
			}
		}
		return keyValues;
	}
	/**
	 * lay danh sach field va value cua class và class cha
	 * @param item
	 * @param allSuper
	 * @return
	 */
	public static <T> Map<String, String> getFieldValueMap(T item, boolean allSuper) {
		Map<String, String> keyValues = new HashMap<String, String>();
		List<String> fieldList = new ArrayList<String>();
		List<Field> fields = getInheritedFields(item.getClass());
		String[] ignoreKeys = getIgnoreKeys(item.getClass());
		for (Field field : fields) {
			if (!Lib.isBlank(field.getName())) {
				if(null!=ignoreKeys && ignoreKeys.length>0){
					if(Arrays.asList(ignoreKeys).contains(field.getName())){
						continue;
					}
				}
				fieldList.add(field.getName());
				keyValues.put(field.getName(),
						getStringValue(getFieldValue(item, field.getName())));
			}
		}
		
		return keyValues;
	}
	/**
	 * lay danh sach properties cua lop cha
	 * @param type
	 * @return
	 */
	public static List<Field> getInheritedFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }
	
	/**
	 * get Class Name not include package
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassName(Class clazz) {
		//uu tien lay class name alias
		String className=RedisAttributes.getAliasClassName(clazz);
		if(Lib.isBlank(className)){
			className = clazz.getName();
			int lastIndex = className.lastIndexOf('.');
			return className.substring(lastIndex + 1);
		}
		return className;
	}
	/**
	 * get Class Name not include package
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassName(Class clazz,boolean getClassName) {
		//uu tien lay class name alias
		String className=RedisAttributes.getAliasClassName(clazz);
		if(getClassName){
			if(Lib.isBlank(className)){
				className = clazz.getName();
				int lastIndex = className.lastIndexOf('.');
				return className.substring(lastIndex + 1);
			}
		}
		return className==null?"":className;
	}

	/**
	 * convert result value to string
	 * 
	 * @param value
	 * @return
	 */
	public static <T> String getStringValue(T value) {
		if (value == null)
			return "";
		if (value instanceof String)
			return value.toString();

		if (value instanceof Character)
			return value.toString();

		if (value instanceof Integer)
			return value.toString();

		if (value instanceof Long)
			return value.toString();

		if (value instanceof Double)
			return value.toString();

		if (value instanceof Float)
			return value.toString();

		if (value instanceof Boolean)
			return value.toString().toLowerCase();

		if (value instanceof Date)
			return Lib.dateToString((Date) value, "dd/MM/yyyy HH:mm:ss");

		if (value instanceof List) {
			String josn = Lib.convertObj2JsonString(value);
			return josn;
		}
		try {
			//ngay mai hoi lai dai ca
//			return value.toString();
			return Lib.convertObj2JsonString(value);
		} catch (Exception ex) {
			return null;
		}
		// return value.toString();
	}

	public static <T> Class<T> convertToClassT(Class<?> clazz) {

		if (clazz.equals(String.class))
			return (Class<T>) String.class;

		if (clazz.equals(Character.class))
			return (Class<T>) Character.class;

		if (clazz.equals(Integer.class))
			return (Class<T>) Integer.class;

		if (clazz.equals(int.class)) {
			return (Class<T>) Integer.class;
		}

		if (clazz.equals(Long.class))
			return (Class<T>) Long.class;

		if (clazz.equals(long.class))
			return (Class<T>) Long.class;

		if (clazz.equals(Double.class))
			return (Class<T>) Double.class;

		if (clazz.equals(double.class))
			return (Class<T>) Double.class;

		if (clazz.equals(Float.class))
			return (Class<T>) Float.class;

		if (clazz.equals(float.class))
			return (Class<T>) Float.class;

		if (clazz.equals(Boolean.class))
			return (Class<T>) Boolean.class;

		if (clazz.equals(boolean.class))
			return (Class<T>) Boolean.class;

		if (clazz.equals(Date.class))
			return (Class<T>) Date.class;

		if (clazz.equals(List.class))
			return (Class<T>) List.class;

		if (clazz.equals(HashMap.class))
			return (Class<T>) HashMap.class;
		if (clazz.equals(Map.class))
			return (Class<T>) Map.class;
		return (Class<T>) clazz;
	}
}
