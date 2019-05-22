package com.flex.dbmanager.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisProvider {

	/**
	 * set list json to redis
	 * 
	 * @param items
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(List<T> items, String key, boolean isDatePartition);
	/**
	 * set key list, convert object sang json sau do insert vo list
	 * 
	 * @param item
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(T item, String key, boolean isDatePartition);

	/**
	 * set key list, convert object sang json sau do insert vo list
	 * 
	 * @param item
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(String main_key, T item, String key, boolean isDatePartition);

	/**
	 * lay gia tri ben dau tien cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveFirstElementInJsonList(String key, String date, Class<T> returnClass);

	/**
	 * lay gia tri ben dau tien cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveFirstElementInJsonList(String key, String date, Class<T> returnClass,
			boolean classNameAsKey);

	/**
	 * lay gia tri ben cuoi cung cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveLastElementInJsonList(String key, String date, Class<T> returnClass);

	/**
	 * lay gia tri 1 phan tu trong list dua vao index neu index=-1 la lay gia
	 * tri cuoi cung
	 * 
	 * @param index
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getElementInJsonListByIndex(int index, String key, String date, Class<T> returnClass);
	/**
	 * lay danh sach trong List
	 * 
	 * @param startIndex
	 * @param endIndex
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> List<T> GetListByIndexJson(int startIndex, int endIndex, String key, String date, Class<T> returnClass);
	/**
	 * get hash key field
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String getHash(String key, String field);
	/**
	 * Returns all fields and values of the hash stored at key. In the returned
	 * value, every field name is followed by its value, so the length of the
	 * reply is twice the size of the hash.
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hGetAll(String key);
	/**
	 * set a hash
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean setHash(String key, String field, String value);
	/**
	 * set multi fields hashMap Sets the specified fields to their respective
	 * values in the hash stored at key. This command overwrites any existing
	 * fields in the hash. If key does not exist, a new key holding a hash is
	 * created.
	 * 
	 * @param key
	 * @param hmap
	 * @return boolean
	 */
	public boolean mSetHash(String key, Map<String, String> hmap);
	/**
	 * push value to last of list
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean rPush(String key, String value);
	/**
	 * remove last element of list
	 * 
	 * @param key
	 * @return last element
	 */
	public String rPop(String key);
	/**
	 * remove element in list base on key and value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lRem(String key, String value);
	/**
	 * push value to first of list
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lPush(String key, String value);
	/**
	 * remove first element of list
	 * 
	 * @param key
	 * @return last element
	 */
	public String lPop(String key);
	/**
	 * get list
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> getList(String key, long start, long end);
	/**
	 * get size of list
	 * 
	 * @param key
	 * @return
	 */
	public long getListSize(String key);
	/**
	 * Adds all the specified members with the specified scores to the sorted
	 * set stored at key. It is possible to specify multiple score / member
	 * pairs. If a specified member is already a member of the sorted set, the
	 * score is updated and the element reinserted at the right position to
	 * ensure the correct ordering.
	 * 
	 * If key does not exist, a new sorted set with the specified members as
	 * sole members is created, like if the sorted set was empty. If the key
	 * exists but does not hold a sorted set, an error is returned.
	 * 
	 * The score values should be the string representation of a double
	 * precision floating point number. +inf and -inf values are valid values as
	 * well.
	 * 
	 * @param key
	 * @return last element
	 */
	public boolean zAdd(String key, int score, String member);
	/**
	 * Removes the specified members from the sorted set stored at key. Non
	 * existing members are ignored.
	 * 
	 * An error is returned when key exists and does not hold a sorted set.
	 * 
	 * @param key
	 * @param members
	 * @return
	 */
	public boolean zRem(String key, String... member);
	/**
	 * Returns the score of member in the sorted set at key.
	 * 
	 * If member does not exist in the sorted set, or key does not exist, nil is
	 * returned.
	 * 
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	public Set<String> zRangeByScore(String key, double min, double max);
	/**
	 * Returns the rank of member in the sorted set stored at key, with the
	 * scores ordered from low to high. The rank (or index) is 0-based, which
	 * means that the member with the lowest score has rank 0
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public long zRank(String key, String member);

	/**
	 * Returns the score of member in the sorted set at key.
	 * If member does not exist in the sorted set, or key does not exist, nil is returned.
	 * @param key
	 * @param member
	 * @return
	 */
	public double zScore(String key, String member);
	/**
	 * set a key value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean setStringByKey(String key, String value, boolean... overwrite);
	/**
	 * set time out for key
	 * 
	 * @param key
	 * @param seconds
	 */
	public void setTimeOut(String key, int seconds);
	/**
	 * check exists key
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkExistsKey(String key);
	/**
	 * set an list object to redis
	 * 
	 * @param items
	 * @return
	 */
	public <T> boolean setList(List<T> items);
	/**
	 * set an object(table) to redis
	 * 
	 * @param item
	 * @return
	 */
	public <T> boolean set(T item);
	public  <T> boolean set(T item, boolean isSupper);
	public  <T> boolean setByLuaScript(T item);
	/**
	 * Get the value of key. If the key does not exist the special value nil is
	 * returned. An error is returned if the value stored at key is not a
	 * string, because GET only handles string values. Bulk string reply: the
	 * value of key, or nil when key does not exist.
	 * 
	 * @param key
	 * @return value
	 * @author jincheng
	 */
	public String getStringByKey(String key);
	/**
	 * get an object(table) to redis
	 * 
	 * @param keyvalue
	 *            primary key of table
	 * @param returnClass
	 * @return
	 */
	public <T> T get(String keyvalue, Class<T> returnClass);
	/**
	 * get list base on index, the same with search index
	 * 
	 * @param entityClass
	 * @param indexFieldName
	 * @param value
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public <T> List<T> getListByIndex(Class<T> entityClass, String indexFieldName, String value, int startIndex,
			int endIndex);
	/**
	 * get number records of table
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> long getSize(Class<T> clazz);
	/**
	 * get list of record of a object(table)
	 * 
	 * @param entityClass
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public <T> List<T> getAllTable(Class<T> entityClass, int startIndex, int endIndex);
	/**
	 * Delete by primarykey
	 * 
	 * @param entityClass
	 * @param keyvalue
	 * @return
	 */
	public <T> boolean delete(Class<T> entityClass, String keyvalue);
	/**
	 * delete all record of table
	 * 
	 * @param entityClass
	 * @return
	 */
	public <T> boolean deleteAll(Class<T> entityClass);
	/**
	 * 
	 * @param values
	 * @param returnClass
	 * @return
	 */
	public <T> T deserialize(Map<String, String> values, Class<T> returnClass);
	
	public String getIndexKey(String className, String fieldname, String value);
	/**
	 * get index key
	 * 
	 * @param item
	 * @param fieldIndexName
	 * @return
	 */
	public <T> String getIndexKey(T item, String fieldIndexName) ;
	/**
	 * get index key
	 * 
	 * @param clazz
	 * @param value
	 * @return
	 */
	public <T> String getItemKey(Class<T> clazz, String value);
	/**
	 * get key off entity
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public <T> String getItemKey(T item) throws Exception;

	/**
	 * get all keys with pattern find
	 * 
	 * @param pattern
	 * @return
	 */
	public List<String> getKeys(String pattern);

	/**
	 * delete keys
	 * 
	 * @param keys
	 * @return
	 */
	public boolean deleteKeys(String... keys);
	public <T> boolean pushEntityToRedis(String key, T item);
	/**
	 * Lấy thời gian timeout
	 * 
	 * @param key
	 * @return
	 */
	public long getTTL(String key);

	public boolean renameKey(String oldkey, String newkey);
	public boolean eval(String script);

	boolean rollbackData(List<String> strings, String key_get);
}
