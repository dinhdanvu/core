package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.ReportSummaryEntity;

@SuppressWarnings("rawtypes")
public class ReportSummaryService extends DB {
	
//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	public static final Logger _infoLog = Logger.getLogger("infoLog");
//	SimpleDateFormat formatDateRP = new SimpleDateFormat("yyyyMMdd");
	
	String keyPrefix = "SM:";

	String keySubSummary = "";

	public ReportSummaryService() {
	}
	
	public List getAllReportSummmary() {
		List dataList = new ArrayList();
		try {
			ReportSummaryEntity obj = new ReportSummaryEntity();
			obj.setData_group(1);
			dataList = queryForList("getAllReportSummary", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}

	public boolean insertSummary(ReportSummaryEntity objS) {
		try
        {
            insert("insertSummary", objS);
            return true;
        }
        catch(Exception ex)
        {
            log.error("Insert Summary:", ex);
            return false;
        }
	}
	

	public boolean insertReportSummary(ReportSummaryEntity obj) {
		try {
			update("insertSummary", obj);
			return true;
		} catch (Exception ex) {
			log.error("update", ex);
			return false;
		}
	}
}
