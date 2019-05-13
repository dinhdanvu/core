package com.flex.dbmanager.redis;

import com.flex.utils.Constants;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;
import com.flex.utils.SecretCards;

import redis.clients.jedis.Protocol;

public class RedisParamEntity {
	/**
	 * flg using cluster or not
	 */
	public int use_cluster;
	/**
	 * ip, if use_cluster=1 then ip:port, else then ip
	 */
	public String host;
	/**
	 * password of server redis
	 */
	public String password;
	/**
	 * port of single redis server
	 */
	public int port=6379;
	/**
	 * index db of single redis
	 */
	public int database=0;
	/**
	 * passwrod server is encrypted
	 */
	public String encrypt="false";
	/**
	 * khong can su dung cung dc
	 */
	public String clientName="v4";
	/**
	 * thoi giam timeout khi open 1 connection
	 */
	public int connectionTimeout=5000;
	/**
	 * thoi gian timeout khi read hoac thuc thi 1 command
	 */
	public int soTimeout=5000;
	/**
	 * so luong min connection idle
	 */
	public int minIdle=1;
	/**
	 * so luong max connection idle
	 */
	public int maxIdle=10;
	public int poolSize;
	/**
	 * max connection trong pool, chi cho phep tao toi da connection
	 */
	public int maxTotal=10000;
	/**
	 * thoi gian cho khi mo 1 connection
	 */
	public int maxWaitMillis=30000;
	public RedisParamEntity(String configFileName,String cateGoryName){
		LoadConfig( configFileName, cateGoryName);
	}
	/**
     * load config
     */
    private void LoadConfig(String configFileName,String cateGoryName) {
			String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
			String dbConfigPath = Lib.combine(path, configFileName);
			
			String hosts = IniFile.readConfigFileString(dbConfigPath, cateGoryName, "host", "127.0.0.1:6379",
					String.class);
			this.host = hosts;
		

//			database = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "database", "0",
//					int.class);

			String pass = IniFile.readConfigFileString(dbConfigPath, cateGoryName, "password", "", String.class);
			String password = Lib.isBlank(pass) ? null : Lib.safeTrim(pass);
			this.password=password;

//			clientName = IniFile.readConfigFileString(dbConfigPath, DB_CATEGORY_NAME, "clientName",
//					"V4", String.class);
			int connectionTimeout=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "connectionTimeout", Protocol.DEFAULT_TIMEOUT+"",
					Integer.class);
			this.connectionTimeout=connectionTimeout;
			
			int soTimeout=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "soTimeout", Protocol.DEFAULT_TIMEOUT+"",
					Integer.class);
			this.soTimeout = soTimeout;
			int maxIdle=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "maxIdle", "100",
					Integer.class);
			this.maxIdle=maxIdle;
			int minIdle=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "minIdle", "10",
					Integer.class);
			this.minIdle=minIdle;
			int poolSize=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "maxTotal", "100",
					Integer.class);
			this.poolSize=poolSize;
			int maxWaitMillis=IniFile.readConfigFileString(dbConfigPath, cateGoryName, "maxWaitMillis", "30000",
					Integer.class);
			this.maxWaitMillis=maxWaitMillis;
			String encrypt = Lib.safeTrim(
					IniFile.readConfigFileString(dbConfigPath, cateGoryName, "encrypt", "", String.class));
			this.encrypt=encrypt;
			if (!"false".equalsIgnoreCase(encrypt)) {
				SecretCards secret = new SecretCards();
				password = secret.decrypt(password);
				this.password=password;
			}
	}
}
