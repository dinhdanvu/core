package com.flex.entities.worker;

import java.util.Date;

public class SystemConfigEntity {
	private Boolean should_stop_all = false;
	private Date system_start_ = new Date();
	
	//theo dõi worker khi đang chạy trên server
	private String worker_tracking = "";
	private Boolean worker_check = false;
	private Boolean worker_run_to_stop = false;
	private Boolean worker_summary = false;
	private int max_pack_tracking = 0;
	
	public int getMax_pack_tracking() {
		return max_pack_tracking;
	}

	public void setMax_pack_tracking(int max_pack_tracking) {
		this.max_pack_tracking = max_pack_tracking;
	}

	public String getWorker_tracking() {
		return worker_tracking;
	}

	public void setWorker_tracking(String worker_tracking) {
		this.worker_tracking = worker_tracking;
	}

	public Boolean getWorker_check() {
		return worker_check;
	}

	public void setWorker_check(Boolean worker_check) {
		this.worker_check = worker_check;
	}

	public Boolean getWorker_run_to_stop() {
		return worker_run_to_stop;
	}

	public void setWorker_run_to_stop(Boolean worker_run_to_stop) {
		this.worker_run_to_stop = worker_run_to_stop;
	}

	public Boolean getWorker_summary() {
		return worker_summary;
	}

	public void setWorker_summary(Boolean worker_summary) {
		this.worker_summary = worker_summary;
	}

	public Boolean getShould_stop_all() {
		return should_stop_all;
	}

	public void setShould_stop_all(Boolean should_stop_all) {
		this.should_stop_all = should_stop_all;
	}

	public Date getSystem_start_() {
		return system_start_;
	}

	public void setSystem_start_(Date system_start_) {
		this.system_start_ = system_start_;
	}
	
}
