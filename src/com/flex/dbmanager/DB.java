package com.flex.dbmanager;

//~--- non-JDK imports --------------------------------------------------------

//import com.ibatis.common.resources.Resources;


import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.flex.utils.IniFile;
import com.flex.utils.Lib;
import com.flex.utils.SecretCards;

import java.io.FileInputStream;
import java.io.InputStream;

//~--- JDK imports ------------------------------------------------------------


import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
//~--- classes ----------------------------------------------------------------
/**
 * **************************************************************************
 * <P>
 *  Class DB provide an interface to all services supported by SqlMap Framework
 * </P>
 * <PRE><SMALL>
 * **************************************************************************
 * System          :   Questionair System
 * ClassID         :   DB Class
 * Class Name      :   DB
 * Functions       :
 *
 * Remark          :
 *   // Create a new DB instance
 *   DB  sql = new DB();
 *   // Call services
 *   sql.insert("insertCategory", Category);
 *
 * **************************************************************************
 * </SMALL></PRE>
 * @version     Header:
 */
public class DB {
	private static String DB_CONFIG_FILENAME = "properties.ini";
	private static String DB_CATEGORY_NAME = "Mysql Configuration";
    public static SqlSessionFactory sqlMap;
    protected static final FLLogger log = FLLogger.getLogger("db/mysqlLog");
    private static String environment="development";

    //~--- static initializers ------------------------------------------------

    /* Create a singleton SqlMap object */
    static {
        try {
            Reader configReader=null;
           try{
            configReader =
                Resources.getResourceAsReader("com/flex/resource/MyBatisMapconfig.xml");
               log.info("read sqlmapconfig.xml is ok");
           }catch(Exception ex) {
               log.info("read sqlmapconfig.xml is not ok");
           }
           try{
        	   //Nếu sqlMap lỗi thì kiểm tra lại file cấu hình sqlmapconfig.xml
        	   //và các file map entity bảo đảm tên entity, class đúng hợp lệ
        	   //các funtion tên phải khác nhau và duy nhất trong tất cả các entity
//            sqlMap = SqlMapClientBuilder.buildSqlMapClient(configReader);
//        	   Properties props = new Properties();
//        	   InputStream input = new FileInputStream(proties_file);
//        	   props.load(input);
        	   Properties props =initProperties();
        	   sqlMap = new SqlSessionFactoryBuilder().build(configReader,environment,props);
            log.info("connect DB Successful");
           }catch(Exception ex1){
        	   sqlMap=null;
        	   log.error(ex1);
           }
        } catch (Throwable ex) {
     	    sqlMap=null;
            log.error(ex);
            log.error("connect DB unSuccessful");
            throw new ExceptionInInitializerError(ex);
        }
    }
public static void main(String[] args){
	DB db =new DB();
	while(true){
		try{
			Thread.sleep(2000);
			ExecutorService executor = Executors.newSingleThreadExecutor();
//			<String> task = new Task<String>() {
//				@Override
//				protected String call() throws Exception {
//					
//				}
//			};
			Future future = executor.submit(new Runnable() {
				public void run() {
					try{
						List lst = db.executeFuncForList("getRedisConfigs");
						System.out.println("connect");
					}catch (Exception e) {
						System.out.println(e);
					}
				}
			});
			future.get(5,TimeUnit.SECONDS);
//			System.out.println();
//			System.out.println(future.get(1,TimeUnit.SECONDS));
		}catch (Exception e) {
			System.out.println(e);
		}
//		System.out.println("ddd");
	}
}
	private static Properties initProperties(){
		try{
			Properties props = new Properties();
			String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
			// File(absolutePath).getParentFile().getAbsolutePath();
			String dbConfigPath = Lib.combine(path, DB_CONFIG_FILENAME);
			String driver =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "driver", "com.mysql.jdbc.Driver", String.class);
			
			String url =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "url", "jdbc:mysql://192.168.2.3:3306/hpm_vpc", String.class);
			
			String encryptedUsername =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "username", "root", String.class);
			
			String encryptedPassword =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "password", "admin", String.class);
			String isEnscrypt =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "encrypt", "true", String.class);
			String mysql_maximum_active_connections =IniFile.readConfigFileString(dbConfigPath,
					DB_CATEGORY_NAME, "mysql_maximum_active_connections", "50", String.class);
			if(!"0".equals(mysql_maximum_active_connections)){
				props.setProperty("poolMaximumActiveConnections", mysql_maximum_active_connections);
			}
			
			/* Put the plain text for driver and url */
			props.setProperty("database.driver", driver);
			props.setProperty("database.url", url);
			SecretCards secretCards = new SecretCards();
			if("false".equals(isEnscrypt)){
				props.setProperty("database.username", encryptedUsername);
				props.setProperty("database.password", encryptedPassword);
			}else{
				/* Decrypt the username and password, then put in map */
				String username=secretCards.decrypt(encryptedUsername);
				String pass=secretCards.decrypt(encryptedPassword);
				props.setProperty("database.username", username);
				props.setProperty("database.password", pass);
			}
			return props;
		}catch (Exception e) {
			return null;
		}
	}
    //~--- fields -------------------------------------------------------------

    /* Auto transaction management mode flag */
    //private boolean pAutoTransactionMode;

    /* Use transaction flag */
   // private boolean pIsTransaction;

    //~--- constructors -------------------------------------------------------

    /**
     * DB class' Constructor
     */
    public DB() {

        // Default is not using Transaction
       // this.pIsTransaction = false;    // This means AutoCommit also

        // Let SqlMap to manage transaction in default
       // this.pAutoTransactionMode = true;
    	if(sqlMap==null){
    		try {
                Reader configReader=null;
               try{
                configReader =
                    Resources.getResourceAsReader("com/flex/resource/MyBatisMapconfig.xml");
                   log.info("read sqlmapconfig.xml is ok");
               }catch(Exception ex) {
                   log.info("read sqlmapconfig.xml is not ok");
               }
               try{
            	   Properties props =initProperties();
            	   sqlMap = new SqlSessionFactoryBuilder().build(configReader,environment,props);
                log.error("connect DB Successful");
               }catch(Exception ex1){
            	   sqlMap=null;
            	   log.error(ex1);
               }
            } catch (Throwable ex) {
            	sqlMap=null;
                log.error(ex);
                log.error("connect DB error");
                throw new ExceptionInInitializerError(ex);
            }
    	}
    	//sqlMap.getUserConnection().setAutoCommit(true);
    }
    
    //~--- methods ------------------------------------------------------------

    /**
     * 
     * Commit changes to Oracle DB
     * 
     */
    public void commit() throws SQLException {
    	SqlSession session = sqlMap.openSession();
    	session.commit();
    }

    /**
     * 
     * Make an Delete call to DB
     * Method Name:  delete
     *
     * @param aSqlID a mapped Delete SQL orgID
     * @param aParamObj parameter class
     * @return int          number of rows effected
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected int delete(String aSqlID, Object aParamObj) throws SQLException {
        // Number of rows effected
        int iRet=0;
    	SqlSession session = sqlMap.openSession(true);
        try {
            // Ask SqlMap to do delete task
            iRet = session.delete(aSqlID, aParamObj);
           
        } catch(Exception ex) {
        	log.error(ex);
        }finally {
			session.close();
		}

        // return number of effected rows
        return iRet;
    }
    /**
     * 
     * Set value of Transaction Mode Flag to true for using Transaction
     * 
     */
    public void doTransaction() {
        this.doTransaction(true);
    }

    /**
     * 
     * Set value to Transaction Mode Flag. True for using Transaction
     *
     * @param   aFlag using transaction mode flag
     * 
     */
    protected void doTransaction(boolean aFlag) {
      //  this.pIsTransaction = aFlag;
    }

    /**
     * 
     * End Transaction
     * 
     */
    public void endTransaction() throws SQLException {

        // When not in auto mode
//        if (!this.pAutoTransactionMode) {
//
//            // Do commit
//            this.commit();
//
//            // end transaction
//            sqlMap.endTransaction();
//
//            // turn on auto transaction management mode
//            this.pAutoTransactionMode = true;
//        }
    }

    /**
     * 
     * An alias of queryForList function
     *
     * @param aSqlID a mapped query orgID
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List executeFuncForList(String aSqlID) throws SQLException {

        // HashMap for returns
        HashMap mapParams = new HashMap();

        // Query for list and result will be put into the HashMap
        return this.executeFuncForList(aSqlID, mapParams,
                                       Constants.DEFAULT_RETURN_KEY);
    }

    /**
     * 
     * An alias of queryForList function
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List executeFuncForList(String aSqlID, Object aParamObj)
            throws SQLException {

        // Query for list and result will be put into the HashMap
        return this.executeFuncForList(aSqlID, aParamObj,
                                       Constants.DEFAULT_RETURN_KEY);
    }

    /**
     * 
     * An alias of queryForList function
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List executeFuncForList(String aSqlID, Object aParamObj,
                                      String returnID)
            throws SQLException {
        List ret = this.queryForList(aSqlID, aParamObj);

        if ((ret == null) || ((ret != null) && ret.isEmpty())) {
            try {
                HashMap map = (HashMap) aParamObj;

                ret = (List) map.get(returnID);
            } catch (ClassCastException ex) {
                ret = new ArrayList();
            }
        }

        return ret;
    }

    /**
     * 
     * An alias of executeFuncForList function without return ID parameter.
     * This ID will be take from the class Constants
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param skip The number of results to ignore.
     * @param max The maximum number of results to return.
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List executeFuncForList(String aSqlID, Object aParamObj,
                                      int skip, int max)
            throws SQLException {
        return this.executeFuncForList(aSqlID, aParamObj, skip, max,
                                       Constants.DEFAULT_RETURN_KEY);
    }

    /**
     * 
     * An alias of queryForList function.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param skip The number of results to ignore.
     * @param max The maximum number of results to return.
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List executeFuncForList(String aSqlID, Object aParamObj,
                                      int skip, int max, String returnID)
            throws SQLException {
        List ret = this.queryForList(aSqlID, aParamObj, skip, max);

        if ((ret == null) || ((ret != null) && ret.isEmpty())) {
            try {
                HashMap map = (HashMap) aParamObj;

                ret = (List) map.get(returnID);
            } catch (ClassCastException ex) {
                ret = new ArrayList();
            }
        }

        return ret;
    }

    /**
     * 
     * An alias of queryForMap function.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aKeyProp The property to be used as the key in the Map.
     * @return Map          A Map keyed by aKeyProp with values being the result
     * object instance
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Map executeFuncForMap(String aSqlID, Object aParamObj,
                                    String aKeyProp)
            throws SQLException {
        return queryForMap(aSqlID, aParamObj, aKeyProp);
    }

    /**
     * 
     * An alias of queryForMap function.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aKeyProp The property to be used as the key in the Map.
     * @param aValueProp The property to be used as the value in the Map.
     * @return Map          A Map keyed by aKeyProp with values being the result
     * object instance
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Map executeFuncForMap(String aSqlID, Object aParamObj,
                                    String aKeyProp, String aValueProp)
            throws SQLException {
        return this.queryForMap(aSqlID, aParamObj, aKeyProp, aValueProp);
    }

    /**
     * 
     * An alias of queryForObject function
     *
     * @param aSqlID The orgName of the statement to execute
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object executeFuncForObject(String aSqlID) throws SQLException {

        // HashMap for returns
        HashMap mapParams = new HashMap();

        // Return one object specified by returnID
        return executeFuncForObject(aSqlID, mapParams,
                                    Constants.DEFAULT_RETURN_KEY);
    }

    /**
     * 
     * An alias of queryForObject function
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object executeFuncForObject(String aSqlID, Object aParamObj)
            throws SQLException {
        return executeFuncForObject(aSqlID, aParamObj,
                                    Constants.DEFAULT_RETURN_KEY);
    }

    /**
     * 
     * An alias of queryForObject function
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aResultObject The result object instance that should be populated
     * with result data.
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object executeFuncForObject(String aSqlID, Object aParamObj,
            Object aResultObject)
            throws SQLException {
        return queryForObject(aSqlID, aParamObj, aResultObject);
    }

    /**
     * 
     * An alias of queryForObject function
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object executeFuncForObject(String aSqlID, Object aParamObj,
            String returnID)
            throws SQLException {
        Object ret = this.queryForObject(aSqlID, aParamObj);

        try {
            if (ret == null) {
                HashMap map = (HashMap) aParamObj;
                try{
                List lst = (List)map.get(returnID);
                if(lst==null || lst.size()==0) return null;
                ret = lst.get(0);
                }catch(ClassCastException ex) {
                    return map.get(returnID);
                }
            } else {
                List lst = (List) ret;

                if (lst.isEmpty()) {
                    HashMap map = (HashMap) aParamObj;

                    ret = map.get(returnID);
                }
            }
        } catch (ClassCastException ex) {
            log.error(ex);
            // Do nothing
        }

        return ret;
    }

    /**
     * using for break page
     * @param aSqlID
     * @param aParamObj
     * @param curPage should be >0, start=1...
     * @param rsPerPage
     * @return
     * @throws SQLException
     */
    protected List executeFuncForPaginatedList(String aSqlID,
          Object aParamObj, int curPage, int rsPerPage)
          throws SQLException {
    	int skip=(curPage-1)* rsPerPage;
    	int max=Constants.MAXRECORD;
      return this.executeFuncForList(aSqlID, aParamObj, skip, max);
  }
    /**
     * 
     * An alias of queryForObject function
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected void executeFuncForUpdate(String aSqlID, Object aParamObj)
            throws SQLException {
        queryForObject(aSqlID, aParamObj);
        
    }

    /**
     * 
     * Make an Insert call to DB
     * Method Name:  insert
     *
     * @param aSqlID an Insert call orgID
     * @param aObj parameter class
     * @return Object  primaryKey object generated by RDBMS
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object insert(String aSqlID, Object aObj) throws SQLException {
    	SqlSession session = sqlMap.openSession(true);
        // Primary key return by Insert command
        Object retPrk=null;
        try {
            // Ask SqlMap to do insert task
            retPrk = session.insert(aSqlID, aObj);
            //session.commit();
        } catch(Exception ex) {
        	log.error(ex);
            //session.rollback();
        	throw ex;
        }finally {
			session.close();
		}

        // Return the primary key
        return retPrk;
    }

    /**
     * 
     * Make an query to DB for a list of objects
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List queryForList(String aSqlID, Object aParamObj)
            throws SQLException {

        // A List of result objects
        List lsRet=null;
    	SqlSession session = sqlMap.openSession(true);
        try {
            // Ask SqlMap to do queryForList task
        	lsRet = session.selectList(aSqlID,aParamObj);
//            lsRet = session.queryForList(aSqlID, aParamObj);
        } catch(Exception ex) {
        	log.error(ex);
        	throw ex;
        }finally {
			session.close();
		}

        // return a list of result objects
        return lsRet;
    }

    /**
     * 
     * Executes a mapped SQL SELECT statement that returns data to populate
     * a number of result objects within a certain range.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param skip The number of results to ignore.
     * @param max The maximum number of results to return.
     * @return List         A List of result objects
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected List queryForList(String aSqlID, Object aParamObj, int skip,
                                int max)
            throws SQLException {

        // A List of result objects
        List lsRet=null;
    	SqlSession session = sqlMap.openSession(true);
        try {
            // Ask SqlMap to do queryForList task
        	RowBounds bound = new RowBounds(skip, max);
            lsRet = session.selectList(aSqlID, aParamObj, bound);
        } catch (Exception ex) {
			log.error(ex);
			throw ex;
		}finally {
			session.close();
		}
        // return a list of result objects
        return lsRet;
    }

    /**
     * 
     * Executes a mapped SQL SELECT statement that returns data to populate
     * a number of result objects that will be keyed into a Map.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aKeyProp The property to be used as the key in the Map.
     * @return Map          A Map keyed by aKeyProp with values being the result
     * object instance
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Map queryForMap(String aSqlID, Object aParamObj, String aKeyProp)
            throws SQLException {

        // A Map keyed by aKeyProp
        Map mapRet=null;
 	   SqlSession session = sqlMap.openSession(true);
       try {
            // Ask SqlMap to do queryForMap task
    	   mapRet = session.selectMap(aSqlID, aParamObj, aKeyProp);
//            mapRet = session.queryForMap(aSqlID, aParamObj, aKeyProp);
        } catch (Exception ex) {
			log.error(ex);
			throw ex;
		} finally {
			session.close();
		}
        // return a map keyed by aKeyProp
        return mapRet;
    }

    /**
     * 
     * Executes a mapped SQL SELECT statement that returns data to populate a
     * number of result objects from which one property will be keyed into a Map.
     *
     * @param aSqlID a mapped query orgID
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aKeyProp The property to be used as the key in the Map.
     * @param aValueProp The property to be used as the value in the Map.
     * @return Map          A Map keyed by aKeyProp with values being the result
     * object instance
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Map queryForMap(String aSqlID, Object aParamObj,
                              String aKeyProp, String aValueProp)
            throws SQLException {

        // A Map keyed by aKeyProp
        Map mapRet;
        // Ask SqlMap to do queryForMap task
    	SqlSession session = sqlMap.openSession(true);
        try {
//        	RowBounds bound = new RowBounds(skip, max);
        	mapRet = session.selectMap(aSqlID, aKeyProp,
                    aValueProp);
//        	mapRet = session.selectList(aSqlID, aParamObj, aKeyProp,
//                    aValueProp);
//            mapRet = sqlMap.queryForMap(aSqlID, aParamObj, aKeyProp,
//                                             aValueProp);
        }catch (Exception e) {
			log.error(e);
			throw e;
		}finally {
			session.close();
		}
        // return a map keyed by aKeyProp
        return mapRet;
    }

    /**
     * 
     * Make an query to DB for a single object instance populated with the
     * result set data.
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object queryForObject(String aSqlID, Object aParamObj)
            throws SQLException {

        // The result object instance
        Object objRet=null;
    	SqlSession session = sqlMap.openSession(true);
        try {
            // Ask SqlMap to do queryForObject task
            objRet = session.selectOne(aSqlID, aParamObj);
        }catch(Exception ex) {
            log.error(ex);
            throw ex;
        }finally {
			session.close();
		}        
        // return the result object instance
        return objRet;
    }

    /**
     * 
     * Executes a mapped SQL SELECT statement that returns data to populate
     * the supplied result object.
     *
     * @param aSqlID The orgName of the statement to execute
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aResultObject The result object instance that should be populated
     * with result data.
     * @return Object       The single result object populated with the result
     * set data, or null if no result was found
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected Object queryForObject(String aSqlID, Object aParamObj,
                                    Object aResultObject)
            throws SQLException {

        // The result object instance
        Object objRet=null;
 	   SqlSession session = sqlMap.openSession(true);
       try {
            // Ask SqlMap to do queryForObject task
    	   objRet = session.selectOne(aSqlID, aParamObj);
//            objRet = sqlMap.queryForObject(aSqlID, aParamObj,
//                    aResultObject);
        }catch (Exception e) {
			log.error(e);
			throw e;
		}finally {
			session.close();
		}      
       // return the result object instance
        return objRet;
    }

    /**
     * 
     * Executes a mapped SQL SELECT statement that returns data to populate
     * a number of result objects a page at a time.
     *
     * @param aSqlID The orgName of the statement to execute.
     * @param aParamObj The parameter object (e.g. JavaBean, Map, XML etc.)
     * @param aPageSize The maximum number of result objects each page can hold.
     * @return PaginatedList - A PaginatedList of result objects
     *
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
//    protected PaginatedList queryForPaginatedList(String aSqlID,
//            Object aParamObj, int aPageSize)
//            throws SQLException {
//
//        // A PaginatedList of result objects
//        PaginatedList pageRet;
//
//        // Start transaction if in the AutoTransaction mode
//        if (this.pAutoTransactionMode) {
//
//            // Start Transaction
//            sqlMap.startTransaction();
//
//            // use transaction means not using AutoCommit mode
//            sqlMap.getCurrentConnection().setAutoCommit(
//                !this.pIsTransaction);
//        }
//
//        try {
//
//            // Ask SqlMap to do queryForMap task
//            pageRet = sqlMap.queryForPaginatedList(aSqlID, aParamObj,
//                    aPageSize);
//            
//            // Commit change to DB in case of using Transaction
//            if (this.pIsTransaction) {
//                sqlMap.commitTransaction();
//            }
//        } finally {
//
//            // End transaction if in the Auto Transaction Mode
//            if (this.pAutoTransactionMode) {
//
//                // Commit change to DB in case of using Transaction
//                if (this.pIsTransaction) {
//
//                    // Commit
//                    this.commit();    // sqlMap.commitTransaction();
//                }
//
//                // End transaction
//                sqlMap.endTransaction();
//            }
//        }
//
//        // return a paginatedList of result objects
//        return pageRet;
//    }

    /**
     * 
     * Rollback all changes from Oracle DB
     * 
     */
//    public void rollback() throws SQLException {
//    	SqlSession session = sqlMap.openSession();
//    	session.rollback();
////        sqlMap.getCurrentConnection().rollback();
//        // End transaction
//        sqlMap.endTransaction();
//    }

    /**
     * 
     * Start Transaction
     * 
     */
//    protected void startTransaction() throws SQLException {
//
//        // enable transaction
//        this.doTransaction();
//
////        if (this.pAutoTransactionMode) {
////
////            // start transaction
////            sqlMap.startTransaction();
////
////            // Turn off autoCommit off
////            sqlMap.getCurrentConnection().setAutoCommit(false);
////
////            // turn off auto transaction management mode
////            this.pAutoTransactionMode = false;
////        }
//    }

    /**
     * 
     * Make an Update call to DB
     * Method Name:  update
     *
     * @param aSqlID a mapped Update SQL call orgID
     * @param aParamObj parameter class
     * @return int          number of rows effected
     * @throws java.sql.SQLException - If an error occurs
     * 
     */
    protected int update(String aSqlID, Object aParamObj) {
        // Ask SqlMap to do update task
    	SqlSession session = sqlMap.openSession();
        // Number of rows effected
        int iRet=0;
        try {
            iRet = session.update(aSqlID, aParamObj);
            session.commit();
        }catch (Exception e) {
			log.error(e);
			throw e;
		}finally {
			session.close();
		}
        // return number of effected rows
        return iRet;
    }

    //~--- get methods --------------------------------------------------------

    /**
     * 
     * Return the SqlMap instance
     * Method Name: getSqlMap
     *
     * @return  SqlMapClient
     * 
     */
//    protected SqlMapClient getSqlMap() {
//        return sqlMap;
//    }

    /**
     * 
     * Return true if transaction mode is enabled
     *
     * @return  boolean    Is using transaction flag
     * 
     */
    protected boolean isTransaction() {

        // Return transaction flag value
        return false;//this.pIsTransaction;
    }
//    public boolean startBatch() throws SQLException{
//    	try {
//    		SqlSession session = sqlMap.openSession();
//            // Ask SqlMap to do update task
//    		session..startBatch();
//        }catch (SQLException e) {
//			log.error(e);
//			throw e;
//		}
//        // return number of effected rows
//        return true;
//    }
//    public int executeBatch() throws SQLException{
//    	// Number of rows effected
//        int iRet=0;
//        try {
//            iRet = sqlMap.executeBatch();
//        }catch (SQLException e) {
//			log.error(e);
//			throw e;
//		}
//        // return number of effected rows
//        return iRet;
//    }
}
