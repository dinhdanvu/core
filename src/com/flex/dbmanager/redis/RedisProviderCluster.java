package com.flex.dbmanager.redis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.flex.dbmanager.attributes.RedisAttributes;
import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.flex.utils.Lib;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;
import redis.clients.util.JedisClusterCRC16;
//import redis.clients.jedis.Transaction;

public class RedisProviderCluster extends JedisClusterPool implements IRedisProvider {
	public static final FLLogger log = FLLogger.getLogger("redis/log");

	public RedisProviderCluster() {

	}

	public RedisProviderCluster(Map config) {
		_config = config;
	}

	public static void main(String[] args) {
		int slot = JedisClusterCRC16.getSlot("MYSQL_TRACK");
		System.out.println(slot);
	}

	public static int getKeySlot(Object key) {
		if (Lib.isBlank(key)) {
			return 0;
		}
		int slot = JedisClusterCRC16.getSlot(key.toString());
		return slot;
	}

	// private List<Integer> getStartSlots(){
	// List<Integer> slotStarts = new ArrayList<>();
	//
	// JedisCluster jedis = acquire();
	// if (jedis == null) {
	// return null;
	// }
	//// Map<String,JedisPool> nodes = jedis.getClusterNodes();
	//// for(JedisPool node : nodes.values()){
	//// node.getResource().clusterSlots();
	//// }
	// for (ClusterNodeObject clusterNodeObject : hpToNodeObjectMap.values()) {
	// if (clusterNodeObject.isConnected() && clusterNodeObject.isMaster()) {
	// String slot = clusterNodeObject.getSlot();
	// String[] slotSplits = slot.split("-");
	// int slotStart = Integer.parseInt(slotSplits[0]);
	//// int slotEnd = Integer.parseInt(slotSplits[1]);
	// slotStarts.add(slotStart);
	// }
	// }
	// Collections.sort(slotStarts);
	// }
	/**
	 * set list json to redis
	 * 
	 * @param items
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(List<T> items, String key, boolean isDatePartition) {

		try {
			for (T item : items) {
				if (!setJsonType(item, key, isDatePartition)) {
					return false;
				}
			}

		} catch (Exception ex) {
			log.error("setJsonType", ex);
			return false;
		}
		return true;
	}

	/**
	 * set key list, convert object sang json sau do insert vo list
	 * 
	 * @param item
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(T item, String key, boolean isDatePartition) {

		return setJsonType("", item, key, isDatePartition);

	}

	/**
	 * set key list, convert object sang json sau do insert vo list
	 * 
	 * @param item
	 * @param key
	 * @param isDatePartition
	 * @return
	 */
	public <T> boolean setJsonType(String main_key, T item, String key, boolean isDatePartition) {
		// Jedis jedis = null;

		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			String json = Lib.convertObj2JsonString(item);
			// write list key
			String mainKey = main_key;
			if (main_key.equals(""))
				mainKey = RedisAttributes.getClassName(item.getClass());
			if (isDatePartition) {
				mainKey += "_" + Lib.getCurrentDate("ddMMyyyy");
			}
			if (!Lib.isBlank(key)) {
				mainKey += "_" + key;
			}

			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			jedis.rpush(mainKey, json);

			return true;
		} catch (Exception ex) {
			log.error("SetJsonType", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * lay gia tri ben dau tien cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveFirstElementInJsonList(String key, String date, Class<T> returnClass) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}

			String mainKey = RedisAttributes.getClassName(returnClass);
			if (!Lib.isBlank(date)) {
				mainKey += "_" + date;
			}
			if (!Lib.isBlank(key)) {
				mainKey += "_" + key;
			}
			// jedis = CachePool.getInstance().getJedis();
			//
			// if (jedis == null) {
			// return null;
			// }
			String values = jedis.lpop(mainKey);
			if (Lib.isBlank(values)) {
				return null;
			}

			return Lib.convertJson2Object(values, returnClass);
		} catch (Exception ex) {
			log.error("getRemoveFirstElementInJsonList", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Lấy gói tin cũ nhất của imei key và xóa gói tien đó khỏi ống imei
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	// public DeviceDataEntity popDeviceData(String prefixKey, String key,
	// String old_rd) {
	// if (Lib.isBlank(key)) {
	// return null;
	// }
	//
	// Jedis jedis = null;
	//
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// String values = "";
	// if(old_rd.equals(""))
	// values = jedis.lpop(prefixKey + ":" + key);
	// else
	// values = old_rd;
	//
	// if (null == values)
	// return null;
	//
	// DeviceDataEntity objDD = DeviceDataEntity.convertToDeviceData(values);
	// return objDD;
	// } catch (Exception ex) {
	// log.error("Khong the pop " + key);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	/**
	 * lay gia tri dau tien
	 * 
	 * @param prefixKey
	 * @param key
	 * @return
	 */

	// public String popData(String prefixKey, String key) {
	// if (Lib.isBlank(key)) {
	// return null;
	// }
	//
	// Jedis jedis = null;
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// try {
	// String finalKey = prefixKey + ":" + key;
	// if(Lib.isBlank(prefixKey)){
	// finalKey = key;
	// }
	// return jedis.lpop(finalKey);
	// } catch (Exception ex) {
	// log.error("Khong the pop " + key);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	/**
	 * lay gia tri ben dau tien cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveFirstElementInJsonList(String key, String date, Class<T> returnClass,
			boolean classNameAsKey) {
		String mainKey = RedisAttributes.getClassName(returnClass, classNameAsKey);
		if (!Lib.isBlank(date)) {
			mainKey += "_" + date;
		}
		if (!Lib.isBlank(key)) {
			if (Lib.isBlank(mainKey)) {
				mainKey = key;
			} else {
				mainKey += "_" + key;
			}
		}
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String values = jedis.lpop(mainKey);

			if (Lib.isBlank(values)) {
				return null;
			}
			return Lib.convertJson2Object(values, returnClass);
		} catch (Exception ex) {
			log.error("getRemoveFirstElementInJsonList", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * lay gia tri ben cuoi cung cua list va xoa phan tu do di
	 * 
	 * @param key
	 * @param date
	 * @param returnClass
	 * @return
	 */
	public <T> T getRemoveLastElementInJsonList(String key, String date, Class<T> returnClass) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String mainKey = RedisAttributes.getClassName(returnClass);
			if (!Lib.isBlank(date)) {
				mainKey += "_" + date;
			}
			if (!Lib.isBlank(key)) {
				mainKey += "_" + key;
			}

			String values = jedis.rpop(mainKey);

			if (Lib.isBlank(values)) {
				return null;
			}

			return Lib.convertJson2Object(values, returnClass);
		} catch (Exception ex) {
			log.error("getRemoveLastElementInJsonList", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public <T> T getElementInJsonListByIndex(int index, String key, String date, Class<T> returnClass) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String mainKey = RedisAttributes.getClassName(returnClass);
			if (!Lib.isBlank(date)) {
				mainKey += "_" + date;
			}
			if (!Lib.isBlank(key)) {
				mainKey += "_" + key;
			}

			String values = jedis.lindex(mainKey, index);

			if (Lib.isBlank(values)) {
				return null;
			}

			return Lib.convertJson2Object(values, returnClass);
		} catch (Exception ex) {
			log.error("getElementInJsonListByIndex", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public <T> List<T> GetListByIndexJson(int startIndex, int endIndex, String key, String date, Class<T> returnClass) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String mainKey = RedisAttributes.getClassName(returnClass);
			if (!Lib.isBlank(date)) {
				mainKey += "_" + date;
			}
			if (!Lib.isBlank(key)) {
				mainKey += "_" + key;
			}

			List<String> memebers = jedis.lrange(mainKey, startIndex, endIndex);

			List<T> resultList = new ArrayList<T>();
			for (String member : memebers) {
				resultList.add(Lib.convertJson2Object(member, returnClass));
			}

			return resultList;
		} catch (Exception ex) {
			log.error("GetListByIndexJson", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get hash key field
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String getHash(String key, String field) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }

			// get value
			return jedis.hget(key, field);

		} catch (Exception ex) {
			log.error("getHash", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Returns all fields and values of the hash stored at key. In the returned
	 * value, every field name is followed by its value, so the length of the
	 * reply is twice the size of the hash.
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, String> hGetAll(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			Map<String, String> map = jedis.hgetAll(key);
			return map;
		} catch (Exception ex) {
			log.error("hGetAll", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}
	// public Map<String, String> hGetAllByLuaScript(String key) {
	// // Jedis jedis = null;
	// try (JedisCluster jedis = acquire()) {
	// if (jedis == null) {
	// return null;
	// }
	// // jedis = CachePool.getInstance().getJedis();
	// // if (jedis == null) {
	// // return null;
	// // }
	// String script="return redis.call('HGETALL',
	// '"+key+"')";//redis.call('HGETALL', key)
	// Object result = jedis.eval(script);
	// if(null==result){
	// return null;
	// }
	// List res = new ArrayList<String>();
	// if(result instanceof List) {
	// res = (ArrayList<String>) result;
	//
	// }
	// Map<String, String> map = new HashMap();
	//// String[] res = result.toString().split(",");
	// for(int i =0 ; i<res.size()-1;i+=2){
	// String k=(String)res.get(i);
	// String v=(String)res.get(i+1);
	// map.put(k, v);
	// }
	//// Map<String, String> map = jedis.hgetAll(key);
	// return map;
	// } catch (Exception ex) {
	// log.error("hGetAll", ex);
	// return null;
	// }
	// // finally {
	// // if (jedis != null)
	// // jedis.close();
	// // }
	// }

	/**
	 * set a hash
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean setHash(String key, String field, String value) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			jedis.hset(key, field, value);
			return true;
		} catch (Exception ex) {
			log.error("setHash", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public boolean mSetHash(String key, Map<String, String> hmap) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			jedis.hmset(key, hmap);
			return true;
		} catch (Exception ex) {
			log.error("mSetHash", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * push value to last of list
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean rPush(String key, String value) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			jedis.rpush(key, value);
			return true;
		} catch (Exception ex) {
			log.error("rPush", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * remove last element of list
	 * 
	 * @param key
	 * @return last element
	 */
	public String rPop(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return "";
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return "";
			// }
			// Write value
			return jedis.rpop(key);
		} catch (Exception ex) {
			log.error("rPop", ex);
			return "";
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * remove element in list base on key and value
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lRem(String key, String value) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			jedis.lrem(key, 0, value);
			return true;
		} catch (Exception ex) {
			log.error("lRem", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * push value to first of list
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean lPush(String key, String value) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			jedis.lpush(key, value);
			return true;
		} catch (Exception ex) {
			log.error("lPush", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * remove first element of list
	 * 
	 * @param key
	 * @return last element
	 */
	public String lPop(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return "";
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return "";
			// }
			// Write value
			return jedis.lpop(key);
		} catch (Exception ex) {
			log.error("lPop", ex);
			return "";
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get list
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> getList(String key, long start, long end) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			// Write value
			return jedis.lrange(key, start, end);
		} catch (Exception ex) {
			log.error("getList", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get size of list
	 * 
	 * @param key
	 * @return
	 */
	public long getListSize(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return 0;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return 0;
			// }
			// Write value
			return jedis.llen(key);
		} catch (Exception ex) {
			log.error("getListSize", ex);
			return 0;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public boolean zAdd(String key, int score, String member) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			return jedis.zadd(key, score, member) >= 0;
		} catch (Exception ex) {
			log.error("zAdd", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public boolean zRem(String key, String... member) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			return jedis.zrem(key, member) > 0;
		} catch (Exception ex) {
			log.error(member);
			log.error("zRem", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

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
	public Set<String> zRangeByScore(String key, double min, double max) {
		// Jedis jedis = null;

		JedisCluster jedis;
		try {
			jedis = acquire();
		} catch (Exception e) {
			log.error("zRangeByScore acquire error: ", e);
			jedis = null;
		}

		try {
			// JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			// Write value
			return jedis.zrangeByScore(key, min, max);
		} catch (Exception ex) {
			log.error("zRangeByScore", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Returns the rank of member in the sorted set stored at key, with the
	 * scores ordered from low to high. The rank (or index) is 0-based, which
	 * means that the member with the lowest score has rank 0
	 * 
	 * @param key
	 * @param member
	 * @return
	 */
	public long zRank(String key, String member) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return -1;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return -1;
			// }
			try {
				// Write value
				return jedis.zrank(key, member);
			} catch (Exception ex1) {
				return -1;
			}
		} catch (Exception ex) {
			log.error("zRank", ex);
			return -1;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Returns the score of member in the sorted set at key. If member does not
	 * exist in the sorted set, or key does not exist, nil is returned.
	 */
	public double zScore(String key, String member) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return -1;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return -1;
			// }
			try {
				// Write value
				return jedis.zscore(key, member);
			} catch (Exception ex1) {
				return -1;
			}
		} catch (Exception ex) {
			log.error("zScore", ex);
			return -1;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * set a key value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean setStringByKey(String key, String value, boolean... overwrite) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			if (overwrite.length > 0 && overwrite[0] == true) {
				jedis.set(key, value);
			} else {
				jedis.rpush(key, value);
			}
			return true;
		} catch (Exception ex) {
			log.error("set", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Lưu gói tin nhận từ thiết bị
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	// public boolean pushDeviceData(String prefixKey, String key, String value)
	// {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null) {
	// return false;
	// }
	// String k2 = prefixKey + ":" + key;
	// Transaction tx = jedis.multi();
	// try {
	// // Write value
	// tx.rpush(prefixKey, key);
	// tx.rpush(k2, value);
	// tx.exec();
	// return true;
	// } catch (Exception ex) {
	// log.error("pushDeviceData", ex);
	// tx.discard();
	// return false;
	// }
	// } catch (Exception ex) {
	// log.error("pushDeviceData", ex);
	// return false;
	// } finally {
	// if (jedis != null)
	// jedis.close();
	// }
	// }

	/**
	 * set time out for key
	 * 
	 * @param key
	 * @param seconds
	 */
	public void setTimeOut(String key, int seconds) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return;
			// }
			// Write value
			jedis.expire(key, seconds);
		} catch (Exception ex) {
			log.error("setTimeOut", ex);
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * check exists key
	 * 
	 * @param key
	 * @return
	 */
	public boolean checkExistsKey(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			// Write value
			return jedis.exists(key);
		} catch (Exception ex) {
			log.error("checkExistsKey", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * set an list object to redis
	 * 
	 * @param items
	 * @return
	 */
	public <T> boolean setList(List<T> items) {
		try {
			for (T item : items) {
				if (!set(item)) {
					return false;
				}
			}
		} catch (Exception ex) {
			log.error("setList", ex);
			return false;
		}
		return true;
	}

	// public <T> boolean setListTrans(List<T> items,boolean isSupper){
	// try(Jedis jedis = CachePool.getInstance().getJedis()) {
	// if (jedis == null) {
	// return false;
	// }
	// List<String> listWKey = new ArrayList<String>();
	//// String[] listPKey = new String[items.size()];
	// String listKey = "";
	// for (T item : items) {
	// String key = getItemKey(item);
	// listWKey.add(key);
	// if(Lib.isBlank(listKey)){
	// listKey = RedisAttributes.getClassName(item.getClass());
	// listWKey.add(listKey);
	// }
	// }
	// String[] listWatchKey = listWKey.toArray(new String[items.size()]);
	//// String listKey = RedisAttributes.getClassName(item.getClass());
	//
	// Transaction tx = null;
	// jedis.watch(listWatchKey);
	// for (T item : items) {
	// String key = getItemKey(item);
	// String keyvalue = RedisAttributes.getKeyValue(item);
	//
	// // T existedObj = get(keyvalue, (Class<T>)
	// // item.getClass());//cach cũ không dùng
	//
	// // watch all key of table include primary key, index key,...
	// // **************************************************
	//// String listKey = RedisAttributes.getClassName(item.getClass());
	// String[] indexFields = RedisAttributes.getIndex(item.getClass());
	// if (indexFields == null)
	// indexFields = new String[0];
	// String[] indexKeys = new String[indexFields.length];
	// String[] oldIndexKeys = new String[indexFields.length];
	//
	//// Transaction tx = null;
	// while (true) {
	// try {
	//// jedis.watch(key, listKey);
	// // watch all key of table include primary key, index
	// // key,...
	// // **************************************************
	// Map<String, String> myList = jedis.hgetAll(key);
	// T existedObj = deserialize(myList, (Class<T>) item.getClass());
	// // watch index key
	// for (int i = 0; i < indexFields.length; i++) {
	// // get index key
	// String index_key = getIndexKey(item, indexFields[i]);
	// indexKeys[i] = index_key;
	//
	// jedis.watch(index_key);
	// String oldindex_key = getIndexKey(existedObj, indexFields[i]);
	// oldIndexKeys[i] = oldindex_key;
	// jedis.watch(oldindex_key);
	// }
	//
	// tx = jedis.multi();
	//
	// // lấy lại item cũ để update giá trị mới, nếu không có
	// // thì insert mới
	// if (isSupper)
	// tx.hmset(key, serialize(item, true));
	// else
	// tx.hmset(key, serialize(item));
	// tx.lrem(listKey, 0, keyvalue);
	// tx.rpush(listKey, keyvalue);
	// if (indexKeys != null && indexKeys.length > 0) {
	// for (int i = 0; i < indexKeys.length; i++) {
	// // remove old index value
	// tx.lrem(oldIndexKeys[i], 0, keyvalue);
	// // add new value
	// tx.rpush(indexKeys[i], keyvalue);
	// }
	// }
	// List<Object> result = tx.exec();
	// if (result != null) {
	// return true;
	// }
	// } catch (Exception ex) {
	// log.error("set", ex);
	// if (tx != null) {
	// tx.discard();
	// }
	// }
	// }
	// }
	// } catch (Exception ex) {
	// log.error("set", ex);
	// return false;
	// }
	//
	//// jedis.hmset(key, serialize(item));
	//// try {
	//// for (T item : items) {
	////
	//// }
	////
	//// } catch (Exception ex) {
	//// log.error("setList", ex);
	//// return false;
	//// }
	// return true;
	// }
	/**
	 * set an object(table) to redis
	 * 
	 * @param item
	 * @return
	 */
	public <T> boolean set(T item) {
		return set(item, false);
	}

	/**
	 * set an object(table) to redis
	 * 
	 * @param item
	 * @return
	 */
	// public <T> boolean set(T item, boolean isSupper) {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null) {
	// return false;
	// }
	// String key = getItemKey(item);
	//
	// String keyvalue = RedisAttributes.getKeyValue(item);
	//
	// T existedObj = get(keyvalue, (Class<T>) item.getClass());
	//
	// String listKey = RedisAttributes.getClassName(item.getClass());
	// String[] indexes = RedisAttributes.getIndex(item.getClass());
	//
	//
	//
	// Transaction tx = jedis.multi();
	// try {
	// if (isSupper)
	// tx.hmset(key, serialize(item, true));
	// else
	// tx.hmset(key, serialize(item));
	// tx.lrem(listKey, 0, keyvalue);
	// tx.rpush(listKey, keyvalue);
	// if (indexes != null && indexes.length > 0) {
	// for (String indexField : indexes) {
	// // remove old index value
	// String index_key = getIndexKey(existedObj, indexField);
	// tx.lrem(index_key, 0, keyvalue);
	// // add new value
	// index_key = getIndexKey(item, indexField);
	// tx.rpush(index_key, keyvalue);
	// }
	// }
	//
	// tx.exec();
	// } catch (Exception ex) {
	// log.error("set", ex);
	// tx.discard();
	// return false;
	// }
	//
	// return true;
	// } catch (Exception ex) {
	// log.error("set", ex);
	// return false;
	// } finally {
	// if (jedis != null)
	// jedis.close();
	// }
	// }

	// private static Object lock_obj = new Object();

	/**
	 * set an object(table) to redis by custom define key
	 * 
	 * @param item
	 * @return
	 */
	// public <T> boolean set(T item, boolean isSupper) {
	//
	// try{
	// Date time_start = Lib.getCurrentDate();
	// String key = getItemKey(item);
	// String keyvalue = RedisAttributes.getKeyValue(item);
	//
	//
	// // watch all key of table include primary key, index key,...
	// // **************************************************
	// String listKey = RedisAttributes.getClassName(item.getClass());
	// String[] indexFields = RedisAttributes.getIndex(item.getClass());
	// if (indexFields == null)
	// indexFields = new String[0];
	// String[] indexKeys = new String[indexFields.length];
	// String[] oldIndexKeys = new String[indexFields.length];
	//
	//
	// Transaction tx = null;
	// while (true) {
	//// try {
	// try (JedisCluster jedis = acquire()) {
	// if (jedis == null) {
	// return false;
	// }
	// if (Lib.timeDiffSecond(Lib.getCurrentDate(), time_start) >
	// Constants.REDIS_TIME_TIMEOUT_LIMIT) {
	// log.error("set key timeout: " + key + ","
	// + Lib.timeDiffSecond(Lib.getCurrentDate(), time_start));
	// return false;
	// }
	//// jedis.watch(key, listKey);
	// // watch all key of table include primary key, index
	// // key,...
	// // **************************************************
	// Map<String, String> myList = jedis.hgetAll(key);
	// T existedObj = deserialize(myList, (Class<T>) item.getClass());
	// // watch index key
	// for (int i = 0; i < indexFields.length; i++) {
	// // get index key
	// String index_key = getIndexKey(item, indexFields[i]);
	// indexKeys[i] = index_key;
	//
	//// jedis.watch(index_key);
	// String oldindex_key = getIndexKey(existedObj, indexFields[i]);
	// oldIndexKeys[i] = oldindex_key;
	//// jedis.watch(oldindex_key);
	// }
	//
	// tx = jedis.multi();
	//
	// // lấy lại item cũ để update giá trị mới, nếu không có
	// // thì insert mới
	// if (isSupper) {
	// tx.hmset(key, serialize(item, true));
	// } else {
	// tx.hmset(key, serialize(item));
	// }
	// tx.lrem(listKey, 0, keyvalue);
	// tx.rpush(listKey, keyvalue);
	// if (indexKeys != null && indexKeys.length > 0) {
	// for (int i = 0; i < indexKeys.length; i++) {
	// // remove old index value
	// tx.lrem(oldIndexKeys[i], 0, keyvalue);
	// // add new value
	// tx.rpush(indexKeys[i], keyvalue);
	// }
	// }
	// List<Object> result = tx.exec();
	// if (result != null && !result.isEmpty()) {
	// long sec = Lib.timeDiffSecond(Lib.getCurrentDate(), time_start);
	// if(sec>5){
	// log.info("Thoi gian hoan thanh set key: "+key+","+sec);
	// }
	// return true;
	// } else {
	// log.error("set error, key 0: " + key);
	// }
	//
	// } catch (Exception ex) {
	// log.error("set", ex);
	// if (tx != null) {
	// tx.discard();
	// }
	// }
	// }
	//
	// } catch (Exception ex) {
	// log.error("set", ex);
	// return false;
	// }
	//
	// }
	public <T> boolean set(T item, boolean isSupper) {

		try {
			Date time_start = Lib.getCurrentDate();
			String key = getItemKey(item);
			String keyvalue = RedisAttributes.getKeyValue(item);

			// watch all key of table include primary key, index key,...
			// **************************************************
			String listKey = RedisAttributes.getClassName(item.getClass());
			String[] indexFields = RedisAttributes.getIndex(item.getClass());
			if (indexFields == null)
				indexFields = new String[0];
			String[] indexKeys = new String[indexFields.length];
			String[] oldIndexKeys = new String[indexFields.length];

			// Transaction tx = null;
			while (true) {
				try {
					JedisCluster jedis = acquire();
					if (jedis == null) {
						return false;
					}
					// jedis.watch(key, listKey);
					// watch all key of table include primary key, index
					// key,...
					// **************************************************
					Map<String, String> myList = jedis.hgetAll(key);
					T existedObj = deserialize(myList, (Class<T>) item.getClass());
					// watch index key
					for (int i = 0; i < indexFields.length; i++) {
						// get index key
						String index_key = getIndexKey(item, indexFields[i]);
						indexKeys[i] = index_key;

						// jedis.watch(index_key);
						String oldindex_key = getIndexKey(existedObj, indexFields[i]);
						oldIndexKeys[i] = oldindex_key;
						// jedis.watch(oldindex_key);
					}
					// String script = "redis.call(KEYS[1]);";
					//// jedis.
					// jedis.eval(script, 1, "multi");

					// lấy lại item cũ để update giá trị mới, nếu không có
					// thì insert mới
					if (isSupper) {
						jedis.hmset(key, serialize(item, true));
					} else {
						jedis.hmset(key, serialize(item));
					}
					jedis.lrem(listKey, 0, keyvalue);
					jedis.rpush(listKey, keyvalue);
					if (indexKeys != null && indexKeys.length > 0) {
						for (int i = 0; i < indexKeys.length; i++) {
							// remove old index value
							jedis.lrem(oldIndexKeys[i], 0, keyvalue);
							// add new value
							jedis.rpush(indexKeys[i], keyvalue);
						}
					}
					if (Lib.timeDiff(Lib.getCurrentDate(), time_start,
							TimeUnit.SECONDS) > Constants.REDIS_TIME_TIMEOUT_LIMIT) {
						log.error("set key timeout: " + key + ","
								+ Lib.timeDiff(Lib.getCurrentDate(), time_start, TimeUnit.SECONDS));
						// return false;
					}
					return true;
					// List<Object> result = tx.exec();
					// if (result != null && !result.isEmpty()) {
					// long sec = Lib.timeDiffSecond(Lib.getCurrentDate(),
					// time_start);
					// if(sec>5){
					// log.info("Thoi gian hoan thanh set key: "+key+","+sec);
					// }
					// return true;
					// } else {
					// log.error("set error, key 0: " + key);
					// }

				} catch (Exception ex) {
					log.error("set", ex);
					// if (tx != null) {
					// tx.discard();
					// }
				}
			}

		} catch (Exception ex) {
			log.error("set", ex);
			return false;
		}

	}

	public <T> boolean setByLuaScript(T item) {
		// public <T> boolean set(T item) {
		try {
			JedisCluster jedis = acquire();
			if (null == jedis) {
				return false;
			}
			Date time_start = Lib.getCurrentDate();
			String key = getItemKey(item);
			String keyvalue = RedisAttributes.getKeyValue(item);
			// **************************************************
			String listKey = RedisAttributes.getClassName(item.getClass());
			String[] indexFields = RedisAttributes.getIndex(item.getClass());
			if (indexFields == null)
				indexFields = new String[0];
			String[] indexKeys = new String[indexFields.length];
			String[] oldIndexKeys = new String[indexFields.length];
			try {
				Map<String, String> myList = jedis.hgetAll(key);
				T existedObj = deserialize(myList, (Class<T>) item.getClass());
				for (int i = 0; i < indexFields.length; i++) {
					String index_key = getIndexKey(item, indexFields[i]);
					indexKeys[i] = index_key;
					String oldindex_key = getIndexKey(existedObj, indexFields[i]);
					oldIndexKeys[i] = oldindex_key;
				}
				//
				jedis.lrem(listKey, 0, keyvalue);
				jedis.rpush(listKey, keyvalue);
				if (indexKeys != null && indexKeys.length > 0) {
					for (int i = 0; i < indexKeys.length; i++) {
						// remove old index value
						jedis.lrem(oldIndexKeys[i], 0, keyvalue);
						// add new value
						jedis.rpush(indexKeys[i], keyvalue);
					}
				}
			} catch (Exception e) {

			}

			String script = "";
			/**
			 * redis.call('HMSET', 'myhash', 'field1', 'value1', 'field2',
			 * 'value2', 'field4', 'value4', 'field5', 'value5', 'field3',
			 * 'value3');
			 */
			String cmd = "redis.call('hmset','" + key + "',%s);";
			Map<String, String> map = serialize(item);
			String fieldValPair = "";
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String field = entry.getKey();
				String value = entry.getValue();
				if (!Lib.isBlank(fieldValPair)) {
					fieldValPair += ",";
				}
				fieldValPair += "\'" + field + "\',";
				fieldValPair += "\'" + value + "\'";
				// script += String.format(cmd,field,value);
			}
			script += String.format(cmd, fieldValPair);
			script += "return 1;";
			while (true) {
				Object result = jedis.eval(script, 0, "");
				long sec = Lib.timeDiffSecond(Lib.getCurrentDate(), time_start);
				if (sec > 5) {
					log.info("Thoi gian hoan thanh setByLuaScript key: " + key + "," + sec);
				}
				if (null != result) {
					if (Integer.valueOf(result.toString()) > 0) {
						// if((int)result>0){
						return true;
					}
				} else {
					if (sec > Constants.REDIS_TIME_TIMEOUT_LIMIT) {
						log.error("Thoi gian time out setByLuaScript key: " + key + "," + sec);
						return false;
					}
				}
			}
		} catch (Exception ex) {
			log.error("set", ex);
			return false;
		}
	}

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
	public String getStringByKey(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String value = jedis.get(key);
			return value;
		} catch (Exception ex) {
			log.error("get", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get an object(table) to redis
	 * 
	 * @param keyvalue
	 *            primary key of table
	 * @param returnClass
	 * @return
	 */
	public <T> T get(String keyvalue, Class<T> returnClass) {
		// Jedis jedis = null;
		if (Lib.isBlank(keyvalue)) {
			return null;
		}
		Date time_start = Lib.getCurrentDate();
		String key = getItemKey(returnClass, keyvalue);
		JedisCluster jedis;
		try {
			jedis = acquire();
		} catch (Exception e) {
			log.error("get acquire error: ", e);
			jedis = null;
		}
		try {
			// jedis = CachePool.getInstance().getJedis();
			if (jedis == null) {
				return null;
			}
			Map<String, String> myList = jedis.hgetAll(key);
			// Map<String, String> myList = hGetAll(key);
			// Map<String, String> myList = hGetAllByLuaScript(key);
			T newitem = deserialize(myList, returnClass);

			if (Lib.timeDiff(Lib.getCurrentDate(), time_start, TimeUnit.SECONDS) > Constants.REDIS_TIME_TIMEOUT_LIMIT) {
				log.error("get key timeout: " + key + ","
						+ Lib.timeDiff(Lib.getCurrentDate(), time_start, TimeUnit.SECONDS));
			}
			return newitem;
		} catch (Exception ex) {
			log.error("get: " + keyvalue, ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get an object(table) to redis
	 * 
	 * @param keyvalue
	 *            primary key of table
	 * @param returnClass
	 * @return
	 */
	// public <T> T getObjectTable(String keyvalue, Class<T> returnClass) {
	// //Jedis jedis = null;
	// try(Jedis jedis = CachePool.getInstance().getJedis()) {
	// if (jedis == null) {
	// return null;
	// }
	//// jedis = CachePool.getInstance().getJedis();
	//// if (jedis == null) {
	//// return null;
	//// }
	// Map<String, String> myList = jedis.hgetAll(keyvalue);
	// // List<KeyValuePair<String, String>> myList = new
	// // List<KeyValuePair<String, String>>(values);
	//
	// T newitem = deserialize(myList, returnClass);
	// return newitem;
	// } catch (Exception ex) {
	// log.error("getObjectTable", ex);
	// return null;
	// }
	//// finally {
	//// if (jedis != null)
	//// jedis.close();
	//// }
	// }

	/**
	 * get object by key
	 * 
	 * @param keyvalue
	 * @param returnClass
	 * @return
	 */
	// public <T> T getByKey(String keyvalue, Class<T> returnClass) {
	// //Jedis jedis = null;
	// try(Jedis jedis = CachePool.getInstance().getJedis()) {
	// if (jedis == null) {
	// return null;
	// }
	//// jedis = CachePool.getInstance().getJedis();
	//// if (jedis == null) {
	//// return null;
	//// }
	//
	// Map<String, String> myList = jedis.hgetAll(keyvalue);
	//
	// T newitem = deserialize(myList, returnClass);
	// return newitem;
	// } catch (Exception ex) {
	// log.error("getByKey", ex);
	// return null;
	// }
	//// finally {
	//// if (jedis != null)
	//// jedis.close();
	//// }
	// }

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
			int endIndex) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			String key = getIndexKey(RedisAttributes.getClassName(entityClass), indexFieldName, value);
			List<String> memebers = jedis.lrange(key, startIndex, endIndex);

			List<T> resultList = new ArrayList<T>();
			for (String member : memebers) {
				resultList.add(get(member, entityClass));
			}

			return resultList;
		} catch (Exception ex) {
			log.error("getListByIndex", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get number records of table
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> long getSize(Class<T> clazz) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return 0;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return 0;
			// }
			// List<String> values =
			// jedis.lrange(RedisAttributes.getClassName(clazz), 0, -1);
			// return values.size();
			return jedis.llen(RedisAttributes.getClassName(clazz));
		} catch (Exception ex) {
			log.error("getSize", ex);
			return 0;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	// Lấy danh sách tracking đã xử lý đang lưu ở redis
	// public Map<String, String> getListTrackingFromRedis(String key)
	// {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// Map<String, String> myList = jedis.hgetAll(key);
	// return myList;
	// } catch (Exception ex) {
	// log.error("getListTrackingFromRedis", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// Lưu tracking đã xử lý vào redis
	// public void setListTrackingFromRedis(String key,String field_,String
	// json_)
	// {
	//
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return;
	// }
	// jedis.hset(key, field_, json_);
	// } catch (Exception ex) {
	// log.error("getListTrackingFromRedis", ex);
	// return ;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	/**
	 * get list of record of a object(table)
	 * 
	 * @param entityClass
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public <T> List<T> getAllTable(Class<T> entityClass, int startIndex, int endIndex) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			List<String> memebers = jedis.lrange(RedisAttributes.getClassName(entityClass), startIndex, endIndex);
			List<T> resultList = new ArrayList<T>();
			for (String member : memebers) {
				resultList.add(get(member, entityClass));
			}

			return resultList;
		} catch (Exception ex) {
			log.error("getAllTable", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * Delete by primarykey
	 * 
	 * @param entityClass
	 * @param keyvalue
	 * @return
	 */
	public <T> boolean delete(Class<T> entityClass, String keyvalue) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			String key = getItemKey(entityClass, keyvalue);
			T existedObj = get(keyvalue, entityClass);
			if (existedObj == null)
				return false;
			String primaryKeyvalue = RedisAttributes.getKeyValue(existedObj);

			if (Lib.isBlank(primaryKeyvalue))
				return false;
			if (!keyvalue.equalsIgnoreCase(primaryKeyvalue))
				return false;
			String[] indexes = RedisAttributes.getIndex(entityClass);
			String lisKey = RedisAttributes.getClassName(entityClass);
			// Transaction trans = jedis.multi();
			// Transaction trans = jedis.
			try {
				// delete hash key
				// trans.del(key);
				jedis.del(key);
				// delete list value
				jedis.lrem(lisKey, 0, keyvalue);
				// trans.lrem(lisKey, 0, keyvalue);
				// delete index value
				if (indexes != null && indexes.length > 0)
					for (String idx : indexes) {
						jedis.lrem(getIndexKey(existedObj, idx), 0, keyvalue);
					}
				// trans.exec();
			} catch (Exception ex) {
				// trans.discard();
				log.error("delete", ex);
			}
			return true;
		} catch (Exception ex1) {
			log.error("delete", ex1);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * delete all record of table
	 * 
	 * @param entityClass
	 * @return
	 */
	public <T> boolean deleteAll(Class<T> entityClass) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }

			List<String> memebers = jedis.lrange(RedisAttributes.getClassName(entityClass), 0, -1);
			for (String member : memebers) {
				delete(entityClass, member);
			}
			return true;
		} catch (Exception ex) {
			log.error("deleteAll", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * 
	 * @param values
	 * @param returnClass
	 * @return
	 */
	public <T> T deserialize(Map<String, String> values, Class<T> returnClass) {
		T newitem = null;
		try {
			newitem = returnClass.newInstance();
		} catch (InstantiationException e) {
			log.error("deserialize", e);
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			log.error("deserialize", e);
			return null;
		}
		internalDeserializeValue(newitem, values);
		return newitem;
	}

	/**
	 * 
	 * @param newitem
	 * @param values
	 */
	private <T> void internalDeserializeValue(T newitem, Map<String, String> values) {
		if (values != null) {
			List<String> fieldList = RedisAttributes.getFieldList(newitem.getClass());

			for (Map.Entry<String, String> entry : values.entrySet()) {
				String key = entry.getKey();
				Object val = entry.getValue();
				// System.out.println(String.valueOf(val));
				String value = String.valueOf(val);// entry.getValue();

				if (!Lib.isBlank(key) && fieldList.contains(key)) {
					RedisAttributes.setFieldValue(newitem, key, value);
				}
			}

		}
	}

	/**
	 * convert serialize chi lay cac field cua lop hien tai khong lay lop thua
	 * ke
	 * 
	 * @param item
	 * @return
	 */
	private <T> Map<String, String> serialize(T item) {
		Map<String, String> result = RedisAttributes.getFieldValueMap(item);
		return result;
	}

	/**
	 * convert serialize lay luon cac field cua lop no thua ke
	 * 
	 * @param item
	 * @return
	 */
	private <T> Map<String, String> serialize(T item, boolean isSuper) {
		Map<String, String> result = RedisAttributes.getFieldValueMap(item, isSuper);
		return result;
	}

	/**
	 * get key off entity
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	// public <T> String getItemKey(T item) throws Exception {
	// if (item == null)
	// throw new Exception("Item cannot be null");
	//
	// String keyName = RedisAttributes.getKey(item.getClass());
	// String value =
	// RedisAttributes.getStringValue(RedisAttributes.getFieldValue(item,
	// keyName));
	// String key = String.format("[%s_%s:%s]",
	// RedisAttributes.getClassName(item.getClass()), keyName, value);
	// return key;
	// }

	/**
	 * get index key
	 * 
	 * @param className
	 * @param fieldname
	 * @param value
	 * @return
	 */
	public String getIndexKey(String className, String fieldname, String value) {
		return String.format("[IX_%s_%s:%s]", className, fieldname, value);
	}

	/**
	 * get index key
	 * 
	 * @param item
	 * @param fieldIndexName
	 * @return
	 */
	public <T> String getIndexKey(T item, String fieldIndexName) {
		String value = RedisAttributes.getStringValue(RedisAttributes.getFieldValue(item, fieldIndexName));
		return String.format("[IX_%s_%s:%s]", RedisAttributes.getClassName(item.getClass()), fieldIndexName, value);
	}

	/**
	 * get index key
	 * 
	 * @param clazz
	 * @param value
	 * @return
	 */
	public <T> String getItemKey(Class<T> clazz, String value) {
		String keyFieldName = RedisAttributes.getKey(clazz);
		String key = String.format("[%s_%s:%s]", RedisAttributes.getClassName(clazz), keyFieldName, value);
		return key;
	}

	/**
	 * get key off entity
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public <T> String getItemKey(T item) throws Exception {
		if (item == null) {
			throw new Exception("Item cannot be null");
		}
		String keyName = RedisAttributes.getKey(item.getClass());
		String value = RedisAttributes.getStringValue(RedisAttributes.getFieldValue(item, keyName));
		String key = String.format("[%s_%s:%s]", RedisAttributes.getClassName(item.getClass()), keyName, value);
		return key;
	}

	/**
	 * get all keys with pattern find
	 * 
	 * @param pattern
	 * @return
	 */
	public List<String> getKeys(String pattern) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			// ScanResult<String> mbKeys= jedis.scan(pattern, null);
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return null;
			// }
			// Set<String> memebers = jedis.keys(pattern);
			// jedis.get
			List<String> memebers = new ArrayList<String>();
			// String script = "return redis.call('keys',ARGV[1])";
			// jedis.getClusterNodes().
			for (JedisPool node : jedis.getClusterNodes().values()) {
				try {
					Jedis nd = node.getResource();
					Set obj = nd.keys(pattern);
					// Object obj = node.getResource().eval(script, 0, pattern);
					memebers.addAll(obj);
					nd.close();

					// if(obj instanceof List){
					//// memebers = (ArrayList<String>)obj;
					// memebers.addAll((List<String>)obj);
					// }
				} catch (Exception e) {
					// System.out.println(e);
					// TODO: handle exception
				}
			}
			// Object obj = jedis.eval(script, 0, pattern);
			// if(obj instanceof Collections){
			// memebers = (ArrayList<String>)obj;
			// }

			// Set set = new HashSet<String>();
			// List<String> resultList = new ArrayList<String>();
			// for (String member : memebers) {
			//// resultList.add(member);
			// set.add(member);
			// }
			// resultList.addAll(set);
			return memebers;
		} catch (Exception ex) {
			log.error("getKeys", ex);
			return null;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	/**
	 * get list object by key pattern
	 * 
	 * @param entityClass
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	// public <T> List<T> getListByKey(Class<T> entityClass, String keyValue) {
	// //Jedis jedis = null;
	// try(Jedis jedis = CachePool.getInstance().getJedis()) {
	// if (jedis == null) {
	// return null;
	// }
	//// jedis = CachePool.getInstance().getJedis();
	//// if (jedis == null) {
	//// return null;
	//// }
	// Set<String> memebers = jedis.keys(keyValue);
	// List<T> resultList = new ArrayList<T>();
	// for (String member : memebers) {
	// resultList.add(getObjectTable(member, entityClass));
	// }
	//
	// return resultList;
	// } catch (Exception ex) {
	// log.error("getListByKey", ex);
	// return null;
	// }
	//// finally {
	//// if (jedis != null)
	//// jedis.close();
	//// }
	// }

	/**
	 * delete keys
	 * 
	 * @param keys...
	 * @return
	 */
	public boolean deleteKeys(String... keys) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return false;
			// }
			jedis.del(keys);

			return true;
		} catch (Exception ex) {
			log.error("deleteKeys", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	// public boolean changeValue(String key,String field, String value){
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return false;
	// }
	// jedis.hset(key, field, value);
	//
	// return true;
	// } catch (Exception ex) {
	// log.error("changeValue", ex);
	// return false;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// public String getValue(String key,String field){
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// return jedis.hget(key,field);
	// } catch (Exception ex) {
	// log.error("changeValue", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// public <T> void pushRouteStopRunToRedis(String key,T item)
	// {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return ;
	// }
	// jedis.hmset(key, serialize(item));
	// } catch (Exception ex) {
	// log.error("pushRouteStopRunToRedis", ex);
	// return;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	public <T> boolean pushEntityToRedis(String key, T item) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return;
			// }
			jedis.hmset(key, serialize(item));
			return true;
		} catch (Exception ex) {
			log.error("pushEntityToRedis", ex);
			return false;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}
	// public <T> void pushEntityToRedisAndNotChangeKey(String key,String
	// removeKey,T item)
	// {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return ;
	// }
	// Map<String, String> result = serialize(item);
	// String[] ss_remove = removeKey.split(";");
	// for(String s:ss_remove)
	// {
	// result.remove(s);
	// }
	// jedis.hmset(key,result);
	// } catch (Exception ex) {
	// log.error("pushEntityToRedisAndNotChangeKey", ex);
	// return;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// public <T> T RedisToRouteStopToRun(String key,Class<T> returnClass)
	// {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// Map<String, String> myList = jedis.hgetAll(key);
	// T newitem = deserialize(myList, returnClass);
	// return newitem;
	// } catch (Exception ex) {
	// log.error("RedisToRouteStopToRun", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }

	/**
	 * Lấy giá trị trên redis từ key
	 * 
	 * @param key
	 * @return
	 */
	// public String getValue(String key) {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// String value = jedis.get(key);
	// return value;
	// } catch (Exception ex) {
	// log.error("getValue", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }

	/**
	 * Lấy thời gian timeout
	 * 
	 * @param key
	 * @return
	 */
	public long getTTL(String key) {
		// Jedis jedis = null;
		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return -2;
			}
			// jedis = CachePool.getInstance().getJedis();
			// if (jedis == null) {
			// return -2;
			// }
			long value = jedis.ttl(key);
			return value;
		} catch (Exception ex) {
			log.error("getTTL", ex);
			return -2;
		}
		// finally {
		// if (jedis != null)
		// jedis.close();
		// }
	}

	@Override
	public boolean renameKey(String oldkey, String newkey) {
		// RENAME mykey myotherkey

		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return false;
			}
			jedis.rename(oldkey, newkey);
			return true;
		} catch (Exception ex) {
			log.error("getTTL", ex);
			return false;
		}
	}
	// public void workerPush(String key,String val) {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return;
	// }
	// jedis.rpush(key, val);
	// } catch (Exception ex) {
	// log.error("workerPush", ex);
	// return;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// public List<String> workerGetTop(String key,int start, int to) {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// List<String> o = jedis.lrange(key, start, to);
	// return o;
	// } catch (Exception ex) {
	// log.error("workerGetTop", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }

	/*
	 * truyen vao keyValue va returnClass trả về Map<key,value>
	 */
	// public <T> Map<String, String> getStringObject(String keyvalue, Class<T>
	// returnClass) {
	// Jedis jedis = null;
	// try {
	// jedis = CachePool.getInstance().getJedis();
	// if (jedis == null){
	// return null;
	// }
	// String key = getItemKey(returnClass, keyvalue);
	// Map<String, String> myList = jedis.hgetAll(key);
	// // List<KeyValuePair<String, String>> myList = new
	// // List<KeyValuePair<String, String>>(values);
	// return myList;
	// } catch (Exception ex) {
	// log.error("getStringObject", ex);
	// return null;
	// } finally {
	// if (jedis!=null)
	// jedis.close();
	// }
	// }
	// public boolean eval(String script){
	// try (JedisCluster jedis = acquire()) {
	// if (jedis == null) {
	// return false;
	// }
	// jedis.eval(script);
	// return true;
	// } catch (Exception ex) {
	// log.error("getTTL", ex);
	// return false;
	// }
	// }

	@Override
	public boolean eval(String script) {
		return false;
	}

	/**
	 * function test
	 * 
	 * @return
	 */
	public Map<String, JedisPool> getNodes() {
		// RENAME mykey myotherkey

		try {
			JedisCluster jedis = acquire();
			if (jedis == null) {
				return null;
			}
			return jedis.getClusterNodes();
		} catch (Exception ex) {
			log.error("getTTL", ex);
			return null;
		}
	}
}
