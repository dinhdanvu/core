package com.flex.services;

import com.flex.dbmanager.DB;

import com.flex.entities.SmsTransactionEntity;

public class SmsTransactionService extends DB {

	public boolean insertSmsTransaction(SmsTransactionEntity obj){
		try
        {
            insert("insertSmsTransaction", obj);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertSmsTransaction", ex);
            return false;
        }
	}

}
