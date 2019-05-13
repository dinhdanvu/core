package com.flex.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>Title:contants of all form in HPM system </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: FSS</p>
 *
 * @author TriTich
 * @version 1.0
 */
public class Constants {
    public static final boolean TEST_KAFKA = false;
    public static final String TCP_PING = "ping";
    public static final String TCP_REPLY = "pong";
    public static final String PACKAGE = "com.flex";
    // thompt thay đổi giá trị thành hàm load giá trị từ file properties.ini
    public static final String DB_PREFIX = initDbPrefix();//"v4_"; // "v4db2_"; cho vip

    public static final String APP_DIR = "V4.DIR";

    public static final String AES_KEY = "P_Rh@95dv1Zx#=OS";
    public static final int RFID_MSG_KIND = 21;
    /**
     * 50 partitions
     */
    public static final String RFID_MSG_TOPIC = "TOPIC_RFID";
 //   public static final String RFID_MSG_TOPIC = "TOPIC_RFID_THOMPT_TEST"; // thompt test
    
    public static final String DF521_TOPIC = "TOPIC_DF521";
    /**
     * 5 partitions
     */
    public static final String RFID_ROUTE_MSG_TOPIC = "TOPIC_RFID_ROUTE";
    /**
     * 1 partitions
     */
    public static final String RFID_SUMMARY_MSG_TOPIC = "TOPIC_RFID_SUMMARY";
    /**
     * 50 partitions
     */
    public static final String RFID_DATA_MSG_TOPIC = "TOPIC_RFID_DATA";
    /**
     * Use for DB class to return result automatically without  tơp returnID
     */
    public static final String DEFAULT_RETURN_KEY = "return";
    public static final String V4_CONFIG_FILENAME = "properties.ini";
    public static final String MYSQL_CATEGORY_NAME = "Mysql Configuration"; // thompt add 
    /**
     * Using for break page of flex
     */
    public static final int STARTRECORD = 0;
    public static final int MAXRECORD = 200;
    //LOG LEVEL KEY
    public static final String ERROR_EXCEPTION = "0000";
    public static final String ERROR_NO_IMEI = "0001";
    public static final String ERROR_PACKAGE_NOVALID = "0002";
    public static final String ERROR_PACKAGE_PARSER = "0003";
    public static final String ERROR_INSERT_REDIS_PARSER = "0004";
    public static final String ERROR_CONVERT_FROM_JSON = "0005";
    //END QCVN31
    
    /**
     * defined folder which contain xml file
     */
    public static final String ERROR_WOKRER_BUILD_LASTTRACKING = "0020";
    public static final String ERROR_WOKRER_SET_LASTTRACKING = "0021";
    public static final String ERROR_WOKRER_ALGORITHMS_MYSQL = "0030";
    public static final String ERROR_WOKRER_INSERT_DATA_MYSQL = "0031";
    public static final String ERROR_WOKRER_INSERT_ROUTE_MYSQL = "0032";
    public static final String ERROR_WOKRER_INSERT_SUM_MYSQL = "0033";
    public static final String ERROR_WOKRER_INSERT_EXCEESDSPEED_MYSQL = "0034";
    public static final String ERROR_WOKRER_INSERT_EVENT_MYSQL = "0035";
    public static final String ERROR_WOKRER_INSERT_ROUTE_LOCATION_MYSQL = "0036";
    public static final String ERROR_WOKRER_INSERT_DRIVER_MYSQL = "0037";
    public static final String ERROR_WOKRER_INSERT_FEE_STATION_ROUTE_MYSQL = "0038";
    public static final String ERROR_WOKRER_INSERT_ALARM_MYSQL = "0039";
    public static final String ERROR_WOKRER_INSERT_FUEL_ABNORMALYCHANGE_MYSQL = "0060";
    public static final String ERROR_WOKRER_INSERT_FUEL_SUMMARY_MYSQL = "0061";
    public static final String ERROR_WOKRER_ALGORITHMS_REDIS = "0040";
    public static final String ERROR_WOKRER_INSERT_DATA_REDIS = "0041";
    public static final String ERROR_WOKRER_INSERT_ROUTE_REDIS = "0042";
    public static final String ERROR_WOKRER_INSERT_SUM_REDIS = "0043";
    public static final String ERROR_WOKRER_INSERT_EXCEESDSPEED_REDIS = "0044";
    public static final String ERROR_WOKRER_INSERT_EVENT_REDIS = "0045";
    public static final String ERROR_WOKRER_INSERT_ROUTE_LOCATION_REDIS = "0046";
    public static final String ERROR_WOKRER_INSERT_DRIVER_REDIS = "0047";
    public static final String ERROR_WOKRER_INSERT_TRANSPORTATION_REDIS = "0050";
    public static final String ERROR_WOKRER_INSERT_SMS_TRANSACTION_MYSQL = "0052";
    public static final String INFO_EVENT_DEVICES = "0099";
    public static final String ALARM_SMS_KEY = "ALARM_SMS_KEY";
    public static final String ALARM_MAIL_KEY = "ALARM_MAIL_KEY";
    public static final String ALARM_MYSQL_SMS_KEY = "ALARM_MYSQL_SMS_KEY";
    public static final String ALARM_MYSQL_MAIL_KEY = "ALARM_MYSQL_MAIL_KEY";

    public static final String J610_KEY = "J610_DD:";
    public static final String BK86_KEY = "BK86_DD:";
    public static final String DF42X_KEY = "DF42X_DD:";
    public static final String DF423_KEY = "DF423_DD:";
    
    public static final String DF521_KEY = "DF521_DD:";
    public static final String DF521_PARSER_KEY = "DF521_PARSER:";
    //    public static final String DF521_WORKER_KEY="DF521_WORKER:";
    public static final String DF521_IMEI_TO_RFID_KEY = "DF521_IMEI_TO_RFID";
    
    public static final String MYSQLTRACK_KEY = "MYSQL_TRACK";
    public static final String MYSQLROUTE_KEY = "MYSQL_ROUTE";
    public static final String MYSQLSUM_KEY = "MYSQL_SUM";
    public static final String MYSQLSUM_FUEL_KEY = "MYSQL_FUEL_SUM";
    public static final String MYSQLCONSUM_FUEL_KEY = "MYSQL_FUEL_CONSUM";
    public static final String MYSQLEXCEEDSP_KEY = "MYSQL_EXCEEDSP_KEY";
    public static final String MYSQLLOCATIONROUTE_KEY = "MYSQL_LOCATION_ROUTE";
    public static final String MYSQLEVENT_KEY = "MYSQL_EVENT";
    public static final String MYSQLDRIVER_KEY = "MYSQL_DRIVER";
    public static final String MYSQLFEE_STATION_ROUTE_KEY = "MYSQL_ROUTE_FEESTATION";
    public static final String MYSQL_CONCRETE_ROUTE_KEY = "MYSQL_CONCRETE_ROUTE";
    public static final String MYSQL_SMS_TRANSACTION_KEY = "MYSQL_SMS_TRANSACTION"; // thompt add
    //END LOG LEVEL KEY
    
    //redis key
    public static final String MYSQL_ALARM_KEY = "MYSQL_ALARM";
    public static final String MYSQL_SENSOR_DATA_KEY = "MYSQL_SENSOR_DATA";
    public static final String MYSQL_FUEL_ABNORMALY_CHANGE_KEY = "MYSQL_FUEL_ABNORMALY_CHANGE";
    public static final String FORWARD_KEY = "FW:";
    public static final String TRANSPORTATION_KEY = "TRANSPORTATION:";
    public static final String SNP_GIRC="SNP_GIRC";
    public static final String SNP_GARR="SNP_GARR";
    public static final String SNP_VIT="SNP_GVIT";
    public static final String SYSTEM_MONITOR="SYSTEM_MONITOR";
    public static final String SYSTEM_MONITOR_TRAILER="SYSTEM_MONITOR_TRAILER";
    
    //LOG FILE NAME
    public static final String LOG_FILE_SERVICES_START = "startlog/log";
    
    // TCP PROTOCOL
    public static final String LOG_FILE_PROTOCOL_DF521_EXCEOPTION = "tcp_protocal/df/LogException";
    public static final String LOG_FILE_PROTOCOL_DF521_LEVEL1_ERROR = "tcp_protocal/df/level1_error";
    public static final String LOG_FILE_PROTOCOL_BK86_EXCEOPTION = "tcp_protocal/bk/LogException";
    public static final String LOG_FILE_PROTOCOL_BK86_LEVEL1_ERROR = "tcp_protocal/bk/level1_error";
    public static final String LOG_FILE_PROTOCOL_FW_ERROR = "tcp_protocal/forward/fw_error";
    public static final String LOG_FILE_PROTOCOL_DF_IMEIS = "tcp_protocal/imeis/df/DataLog";
    public static final String LOG_FILE_PROTOCOL_BK_IMEIS = "tcp_protocal/imeis/bk/DataLog";
    public static final String LOG_FILE_PROTOCOL_J6_IMEIS = "tcp_protocal/imeis/j6/DataLog";
    public static final String LOG_FILE_PROTOCOL_DF42X_IMEIS = "tcp_protocal/imeis/df42x/DataLog";
    public static final String LOG_FILE_PROTOCOL_INFO = "tcp_protocal/info/log";
    public static final String LOG_FILE_PROTOCOL_ERROR = "tcp_protocal/error/log";
    public static final String LOG_FILE_PROTOCOL_OUT_DEVICE = "tcp_protocal/info/write_out_log";
    public static final String LOG_FILE_PROTOCOL_NUM = "tcp_protocal/connections/log";
    public static final String LOG_FILE_PROTOCOL_SPAM = "tcp_protocal/spam/log";
    // thompt bổ sung
    public static final String LOG_IMEI_IP = "tcp_protocal/connections/iplog_";
    
    // WORKER
    public static final String LOG_OSM_WORKER_ERROR = "worker/error/openstreetmapLog";
    public static final String LOG_FILE_WORKER_ERROR = "worker/error/log";
    public static final String LOG_FILE_WORKER_INFO = "worker/info/log";
    public static final String LOG_FILE_WORKER_EVENT = "worker/event/log";
    public static final String LOG_FILE_WORKER_LEVEL3 = "worker/loglevel3/log";
    public static final String LOG_FILE_WORKER_LEVEL3_REDIS = "worker/loglevel3/redis/log";
    public static final String LOG_FILE_WORKER_LEVEL3_MYSQL = "worker/loglevel3/mysql/log";
    public static final String LOG_FILE_WORKER_LEVEL3_REDIS_DATA = "worker/loglevel3/redis/d_data_log";
    public static final String LOG_FILE_WORKER_LEVEL3_FEE_STATION_REDIS = "sync_track/loglevel3/redis/fee_station_route_log";
    public static final String LOG_FILE_WORKER_DEBUG = "worker/debug/log";
    public static final String LOG_FILE_WORKER_ALGORITHMS = "worker/algorithms/log";
    public static final String LOG_FILE_WORKER_ALGORITHMS_SUMMARY = "worker/algorithms/summary/log";
    
    // TRAILER WORKER
    public static final String LOG_FILE_TRAILER_WORKER_ERROR = "trailer/error/log";
    public static final String LOG_FILE_TRAILER_WORKER_INFO = "trailer/info/log";
    public static final String LOG_FILE_TRAILER_WORKER_EVENT = "trailer/event/log";
    public static final String LOG_FILE_TRAILER_WORKER_DEBUG = "trailer/debug/log";
    public static final String LOG_FILE_TRAILER_WORKER_LEVEL3 = "trailer/loglevel3/log";
    public static final String LOG_FILE_TRAILER_WORKER_LEVEL3_REDIS = "trailer/loglevel3/kafka/log";
    public static final String LOG_FILE_TRAILER_WORKER_LEVEL3_MYSQL = "trailer/loglevel3/mysql/log";
    public static final String LOG_FILE_TRAILER_WORKER_LEVEL3_REDIS_DATA = "trailer/loglevel3/kafka/d_data_log";
    // END REDIS KEY
    
    // PARSER
    public static final String LOG_FILE_PARSER_ERROR = "parser/error/log";
    public static final String LOG_FILE_PARSER_INFO = "parser/info/log";
    public static final String LOG_FILE_PARSER_LEVEL2 = "parser/level2/log";
    
    // UTITLY SERVICES
    public static final String LOG_FILE_UTILITY_INFO = "utility/info/log";
    public static final String LOG_FILE_UTILITY_ERROR = "utility/error/log";
    public static final String LOG_FILE_UTILITY_ERROR_SMS = "utility/error/log/sms";
    public static final String LOG_FILE_UTILITY_ERROR_EMAIL = "utility/error/log/email";
    public static final String LOG_FILE_UTILITY_IMEI = "utility/imeis/";
    // thompt bổ sung file log cho openstreetmap TEST.
    public static final String LOG_FILE_OPM_ERROR = "utility/error/openstreetmaplog";
    public static final String LOG_FILE_OPM_INFO = "utility/info/osmfulladdress/openstreetmaplog";
    public static final String LOG_FILE_OPM_NULL = "utility/info/osmnulladdress/openstreetmaplog";
    
    // FORWARD
    public static final String LOG_FILE_FW_INFO = "fwlog/info/log";
    public static final String LOG_FILE_FW_ERROR = "fwlog/error/log";
    public static final String LOG_FILE_FW_DATA = "fwlog/data/log";
    public static final String LOG_FILE_FW_DEBUG = "fwlog/debug/log";
    
    // BGTVT
    public static final String LOG_FILE_TRANSPORTATION_INFO = "transportation/info/log";
    public static final String LOG_FILE_TRANSPORTATION_ERROR = "transportation/error/log";
    
    public static final String LOG_FILE_TRANSPORHISTORY_INFO = "transporthistory/info/log";
    public static final String LOG_FILE_TRANSPORHISTORY_ERROR = "transporthistory/error/log";
    
    // SYNC_TRACK
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_DATA = "sync_track/loglevel3/mysql/d_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_SUM = "sync_track/loglevel3/mysql/sum_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_EVENT = "sync_track/loglevel3/mysql/event_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_DRIVER = "sync_track/loglevel3/mysql/driver_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_ROUTE = "sync_track/loglevel3/mysql/route_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_EXCEEDSP = "sync_track/loglevel3/mysql/exceedsp_data_log";
    public static final String LOG_FILE_SYNC_TRACK_LEVEL3_LOC_ROUTE = "sync_track/loglevel3/mysql/loc_route_data_log";
    public static final String LOG_FILE_SYNC_LEVEL3_FEE_STATION_ROUTE = "sync_track/loglevel3/mysql/route_fee_station_log";
    public static final String LOG_FILE_SYNC_LEVEL3_ALARM = "sync_track/loglevel3/mysql/alarm_log";
    public static final String LOG_FILE_SYNC_LEVEL3_SENSOR = "sync_track/loglevel3/mysql/sensor_log";
	
    public static String BK86_PREFIX = "BK862015";
    public static String BK79_PREFIX = "BK792016";
    public static String BK88_PREFIX = "BK882018";
    public static String MAIN_RUN_BK86 = "BK86";
    public static String MAIN_RUN_DF521 = "DF521";
    public static String MAIN_RUN_J610 = "J610";
    public static String MAIN_RUN_DF42X = "DF42X";
    public static String MAIN_RUN_DF423 = "DF423";
    public static byte[] BK86_START_BIT = new byte[]{(byte) 0xFF, (byte) 0xFF};
    
    /**
     * time debug redis slowly
     */
    public static int REDIS_TIME_TIMEOUT_LIMIT = 0;
    public static int SQL_DEBUG_TIME_TIMEOUT = 5000;
    public static int GET_ADDR_TIME_WAIT = 5;// S

    public static int BATTERY_MAX_LVL = 10;
    
    public static int DF423_EX_POWER_LIMIT = 1050;
    //==========start pram hard config
    //    public static double MAXANGLETRAM = 160;
    //    public static double RADIUSQUERYTRAM = 2;// km
    //    public static double MINDISTANCETRAM = 0.07;// km
    //    public static double MINANGLETRAM = 90;//goc nhon
    //    public static double SECONDDISTANCETRAM = 0.5;// km
    //==========end pram hard code config
    //QCVN31
    public static int DRIVE_TIME_CONTINUE_LIMIT = 4;// h
    public static int DEFINE_TIME_NO_GPRS = 15;// phut
    public static int DRIVE_TIME_CONTINUE_10 = 10;// h
    //    public static int OLD_DATA_LIMIT = 30;// S
    //Cấu hình tracking category name
    public static String TCP_PROTOCOL_CATEGORY_NAME = "Tcp_protocol Configuration";
    public static String UTITLY_CATEGORY_NAME = "Utily_services Configuration";
    public static String PARSER_CATEGORY_NAME = "Parser Configuration";
    public static String WORKER_CATEGORY_NAME = "Worker Configuration";
    public static String TRAILER_WORKER_CATEGORY_NAME = "Trailer Worker Configuration";
    public static String FORWARD_CATEGORY_NAME = "Forward Configuration";
    public static String TRANSPORTATION_CATEGORY_NAME = "Transportation Configuration";
    public static String TRANSPORTHISTORY_CATEGORY_NAME = "Transporthistory Configuration";
    public static String REDIS_CATEGORY_NAME = "Redis Configuration";
    public static String KAFKA_CATEGORY_NAME = "Kafka Configuration";
    public static String KAFKA_PRODUCER_CATEGORY_NAME = "Kafka Producer Configuration";
    public static String KAFKA_CONSUMER_CATEGORY_NAME = "Kafka Consumer Configuration";
   
    //thompt bổ sung hàm init load giá trị DB_PREFIX từ file cấu hình
    
    public static String initDbPrefix() {
    	String prefix = "v4_";
    	
		String path = Lib.getCurrentLocationPath(Constants.APP_DIR);
		String v4ConfigPath = Lib.combine(path, V4_CONFIG_FILENAME);

		String prefixRead = IniFile.readConfigFileString(v4ConfigPath, MYSQL_CATEGORY_NAME, "dbprefix", "v4_", String.class);
		
		if(!Lib.isBlank(prefixRead)){
			prefix = prefixRead;
		}
		
		return prefix;
	}

    
    
    public static final String getRedisNodeBySlot(int slot) {
        if (slot >= 4681 && slot <= 7021) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeOne.getValue();
        }
        if (slot >= 0 && slot <= 2340) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeTwo.getValue();
        }
        if (slot >= 11703 && slot <= 14042) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeThree.getValue();
        }
        if (slot >= 7022 && slot <= 9361) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeFour.getValue();
        }
        if (slot >= 2341 && slot <= 4680) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeFive.getValue();
        }
        if (slot >= 14043 && slot <= 16383) {
            return MYSQL_TRACK_NODE_KEY.SlotNodeSix.getValue();
        }
        
        return MYSQL_TRACK_NODE_KEY.SlotNodeSeven.getValue();
    }
    public enum DEVICE_TYPE {
    	TK102(1),TK103(2),GT3x(3),GT4x(4),DF220(5),VT3x(6),VT4x(7),DF360(8),RVT3_0(9),RVT3_1(10),DF421(11),DF420(12),MicroTrack(13),DF521(14),J610(15),Android(16),DF720(17),THGPS_01(18),N_A_01(19),VPTech(20),ADA01(21),DF422(22),BK86(23),DF521R(24),BK86A(25),DF522(26),BK79(27),RFID(28),DF521R86(29),DF423(30);
		private int value;
		
		private DEVICE_TYPE(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
    }
    /**
     * 0,"Digital 1" = Input 1 (SOS).
     * 1,"Digital 2" = Input 2 (Ben, cẩu, xả).
     * 2,"Digital 3" = Input 3 (cửa).
     * 3,"Digital 4" = Input 4 (máy lạnh).
     * 4,"Digital 5" = Input 5 (động cơ, ACC).
     * 5,"Digital 6" = Input 6.
     * 6,"Digital 7" = Input 7.
     * 7,"Digital 8" = Input 8.
     * 8,"Analog 1" = AD1 => 9>10-AD1-4095.
     * 9,"Analog 2" = AD2 => 9>10-0-AD2.
     * 10,"Pulse 1" = Xung 1 => 4>0.000-0-Xung 1-0.
     * 11,"Pulse 2" = Xung 2.
     * 12,"Pulse 3" = Xung 3.
     * 13,"Pulse 4" = Xung 4.
     * 14,"Pulse 5" = Xung 5.
     * 15,"Pulse 6" = Xung 6.
     * 16,"Counter 1" = Tổng xung 1 => 4>0.000-Tổng xung 1-0-0.
     * 17,"Counter 2" = Tổng xung 2.
     * 18,"Counter 3" = Tổng xung 3.
     * 19,"Counter 4" = Tổng xung 4.
     * 20,"Counter 5" = Tổng xung 5.
     * 21,"Counter 6" = Tổng xung 6.
     * 22, "Counter 7" = Giờ nổ máy => 3>567.338-Counter7.
     * 23, "Power" = Nguồn.
     * 24, "AntiTheft" = Input bật cảnh báo chống trộm.
     * 25, "FuelSupply" = Input bật cung cấp xăng dầu.
     * 26, "IdeSleep" = Input bật chế độ ngủ.
     * 30, "RouteMG" = Chuyến chở hàng extension.
     * 31, "Ecode" = là mã của mỗi module tín hiệu điện. # thompt add
     * 32, "Epower" = Nguồn điện theo tín hiệu điện được sử dụng hay không (1: có tiêu thụ điện năng, 0: không tiêu thụ điện năng)
     * 33, "Amperage 1": Cảm biến dòng 1
     * 34, "Amperage 2": Cảm biến dòng 2
     * 35, "Amperage 3": Cảm biến dòng 3
     * 36, "Amperage 4": Cảm biến dòng 4
     * 37, "Amperage 5": Cảm biến dòng 5
     * 38, "Amperage 6": Cảm biến dòng 6
     * 39, "Amperage 7": Cảm biến dòng 7
     * 40, "Amperage 8": Cảm biến dòng 8
     * 41-48 : Hiệu suất tiêu thụ điện năng
     * 49: Thời gian giưã hai bản tin
     * @author jincheng, thompt
     */
    public enum SensorEnum {
        Digital1(0), Digital2(1), Digital3(2), Digital4(3), Digital5(4), Digital6(5), Digital7(6), Digital8(7), Analog1(8), Analog2(9), 
        Pulse1(10), Pulse2(11), Pulse3(12), Pulse4(13), Pulse5(14), Pulse6(15), Counter1(16), Counter2(17), Counter3(18), 
        Counter4(19), Counter5(20), Counter6(21), Counter7(22), Power(23), AntiTheft(24), FuelSupply(25), IdeSleep(26), Analog3(27), Analog4(28), Analog5(29), RouteMG(30),
        Ecode(31), Epower(32), Amperage1(33), Amperage2(34), Amperage3(35), Amperage4(36), Amperage5(37), Amperage6(38), Amperage7(39),Amperage8(40),
        Wattage1(41), Wattage2(42), Wattage3(43), Wattage4(44), Wattage5(45), Wattage6(46), Wattage7(47), Wattage8(48), Duration(49);
        private int value;
        
        private SensorEnum(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
//    public static enum MgBody{
//    	XuatBen("\"Xe xuat ben\"",1,"\"Da bat dau chuyen\""),Veben("\"Xe ve ben\"",2,"\"Da ket thuc chuyen\""),
//    	ChoLenHang("\"Cho len hang\"",3,"\"Da cho len hang\""),DangLenHang("\"Dang len hang\"",4,"\"Dang len hang\""),
//    	LenHangXong("\"Len hang xong\"",5,"\"Da len hang xong\""), DangTraHang("\"Dang tra hang\"",6,"\"Dang tra hang\""),
//    	TraHangXong("\"Tra hang xong\"",7,"\"Da tra hang xong\"");
    public static enum MgBody{
    	XuatBen("Xe xuat ben",1,"Da bat dau chuyen"),Veben("Xe ve ben",2,"Da ket thuc chuyen"),
    	ChoLenHang("Cho len hang",3,"Da cho len hang"),DangLenHang("Dang len hang",4,"Dang len hang"),
    	LenHangXong("Len hang xong",5,"Da len hang xong"), DangTraHang("Dang tra hang",6,"Dang tra hang"),
    	TraHangXong("Tra hang xong",7,"Da tra hang xong");
    	private String stringValue;
    	private int intValue;
    	private String replyString;
        
        private MgBody(String stringValue,int intValue,String replyString) {
            this.stringValue = stringValue;
            this.intValue = intValue;
            this.replyString = replyString;
        }
        
        public String getStringValue() {
            return stringValue;
        }
        public int getIntValue() {
            return intValue;
        }
        public String getReplyString() {
            return replyString;
        }
    }
    public static enum MYSQL_TRACK_NODE_KEY {
        SlotNodeOne(1), SlotNodeTwo(2), SlotNodeThree(3), SlotNodeFour(4), SlotNodeFive(5), SlotNodeSix(6), SlotNodeSeven(7);
        private String value = "";
        
        private MYSQL_TRACK_NODE_KEY(int node) {
            switch (node) {
                case 1:
                    this.value = "MYSQL_TRACK_A2";
                    break;
                case 2:
                    this.value = "MYSQL_TRACK_A3";
                    break;
                case 3:
                    this.value = "MYSQL_TRACK_C";
                    break;
                case 4:
                    this.value = "MYSQL_TRACK_D";
                    break;
                case 5:
                    this.value = "MYSQL_TRACK_A";
                    break;
                case 6:
                    this.value = "MYSQL_TRACK_B";
                    break;
                default:
                    this.value = "MYSQL_TRACK_R";
                    break;
            }
        }
        
        public String getValue() {
            return value;
        }
    }
    
    public enum J610Command
    {
        /**
         * Registration
         */
        REGIS("T1"),
        /**
         * Message from device in response to S2
         */
        SET_DEVICE_PARAM("T2"),
        /**
         * Device position report (latitude / longitude; ACC; speed, and angle)
         */
        DEVICE_POS("T3"),
        /**
         * Power supply alarm - mat nguon
         */
        POWER_ALARM("T4"),
        /**
         * Door Alarm - canh bao mo cua
         */
        DOOR_ALARM ("T5"),
        /**
         * Emergency button alarm
         */
        EMER_ALARM("T6"),
        /**
         * Low backup battery alarm - canh bao sap mat nguon
         */
        LOW_BATTERY_ALARM("T7"),
        /**
         * Illegal movement alarm - T8 cảnh báo di chuỷên ngoài phạm vi(chế độ chống trộm)
         */
        ILLEGAL_MOVE_ALARM("T8"),
        /**
         * Address request for SMS demanded position
         */
        ADDRESS_REQUEST("T9"),
        /**
         * Device reply to S10 message with its current position
         */
        DEVICE_REPLY_POS("T10"),
        /**
         * Activate anti-theft, device reply to S12
         */
        ACTIVATE_ANTI_THEFT ("T12"),
        /**
         * Device reply to S13 Deactivates anti-theft on the device, this message has no parameters
         */
        DEACTIVATES_ANTI_THEFT ("T13"),
        /**
         * Device reply to S14, Query internal device parameter
         */
        QUERY_INTERNAL ("T14"),
        /**
         * Device reply to S15 For fuel-consuming vehicles only, enables or disables engine fuel supply
         */
        ENABLES_DISABLES_FUEL("T15"),
        /**
         * For fuel-consuming vehicles only. Sent by the device when ACC is abnormally switched on
         */
        ABNORMAL_ACC_ALARM ("T16"),
        /**
         * When the device’s real speed over the set of max speed in the device, there will engendering a alarm.
         */
        OVERSPEED_ALARM("T17"),
        /**
         *Send any message, Device reply request send message 
         */
        DEVICE_REPLY_SNED_SMS("T18"),
        /**
         * When device receive a SMS MSG, then it will send to server.
         */
        DEVICE_RECV_SMS("T19"),
        /**
         * if the device is under anti-theft mode, somebody vibrate the device, the device will send out an alarm packet to server.
         */
        VIBRATION_ALARM("T21"),
        
        /**
         * Heartbeat sent by the device at interval defined by PULSE, used to keep connection alive. This message has no parameters and only contains device serial number
         */
        HEARTBEAT ("T0");
        private String value;
        
        private J610Command(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    public enum J610ResponseRequestCommand
    {
        /**
         * Registration
         */
        DEVICE_REGISTER_RES ("S1"),
        /**
         * server request device Set internal device parameter
         */
        MESS_INI_RES ("S2"),
        /**
         * Device position report (latitude / longitude, ACC, speed, and angle)
         */
        DEVICE_POS_RES ("S3"),
        /**
         * Message from server in response to T4.
         * 0 or 1. 0 means abnormal status, 1 means message received successfully
         */
        DEVICE_ABNORMAL_STATUS_RES ("S4"),
        /**
         * alarm Signals the vehicle door has been opened
         */
        DOOR_ALARM ("S5"),
        /**
         * Emergency button alarm
         */
        EMER_ALARM_RES ("S6"),
        /**
         * Low backup battery alarm
         */
        LOW_BACKUP_BATTERY_ALARM("S7"),
        /**
         * Illegal movement alarm - T8 cảnh báo di chuỷên ngoài phạm vi(chế độ chống trộm)
         */
        Illegal_MOVE_ALARM_RES ("S8"),
        /**
         * Address request for SMS demanded position
         */
        ADDRESS_REQUEST_RES ("S9"),
        /**
         * Server requested positioning
         */
        SERVER_REQUESTED_POS_REQ ("S10"),
        /**
         * Device reboot
         */
        DEVICE_REBOOT_REQ ("S11"),
        /**
         * Activate anti-theft
         */
        ACTIVATE_ANTI_THEFT_REQ ("S12"),
        /**
         * Deactivates anti-theft on the device, this message has no parameters
         */
        DEACTIVATES_ANTI_THEFT_REQ ("S13"),
        /**
         * Query internal device parameter
         */
        QUERY_INTERNAL_REQ ("S14"),
        /**
         * For fuel-consuming vehicles only, enables or disables engine fuel supply
         */
        ENABLES_DISABLES_FUEL_REQ ("S15"),
        /**
         * server reply to T16 For fuel-consuming vehicles only. Sent by the device when ACC is abnormally switched on
         */
        ABNORMAL_ACC_ALARM_REQ ("S16"),
        /**
         * When the device’s real speed over the set of max speed in the device, there will engendering a alarm.
         */
        OVER_SPEED_ALARM("S17"),
        /**
         * Server send any SMS MSG to any number by device.
         */
        RQ_DEVICE_SEND_SMS("S18"),
        /**
         * if the device is under anti-theft mode, somebody vibrate the device, the device will send out an alarm packet to server.
         */
        VIBRATION_ALARM("S21"),
        /**
         * Server reply to Heartbeat sent by the device at interval defined by PULSE, used to keep connection alive. This message has no parameters and only contains device serial number
         */
        Heartbeat ("S0");
        private String value;
        
        private J610ResponseRequestCommand(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }

    public enum MeiTrackCommand
    {
    	
    	//Automatic Event Report
    	AUTOMATIC_EVENT_REPORT("AAA");
    	
    	private String value;
        
        private MeiTrackCommand(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    	
    }
    public enum MeiTrackEventCode
    {
    	//SOS Pressed
    	SOS(1),
    	//Heartbeat
    	HEARTBEAT(31),
    	//Cornering
    	CORNERING(32),
    	//Track By Distance
    	TRACK_BY_DISTANCE(33),
    	//Reply Current (Passive)
    	REPLY_CURRENT(34),
    	//Track By Time Interval
    	TRACK_BY_TIME_INTERVAL(35);
    	
    	private double value;
        
        private MeiTrackEventCode(double value) {
            this.value = value;
        }
        
        public double getValue() {
            return value;
        }
    	
    }
    public enum DF42XModel{
    	MT90(1,""),MVT100(2,""),MVT340(3,""),T355(4,""), MVT380(5,"DF420"),MVT800(6,"DF421"),MVT600(7,"DF421"),T1(8,""),T3(9,"")
    	, T333(10,""),TC68S(11,""),T322X(12,""),T311(13,"");
    	private int value;
        private String v4Type;
        private DF42XModel(int value,String v4Type) {
            this.value = value;
            this.v4Type = v4Type;
        }
        
        public int getValue() {
            return value;
        }
        public String getV4Type() {
            return v4Type;
        }
    }

}
