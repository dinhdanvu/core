package com.flex.services;

import com.flex.entities.worker.TrackingEntity;

public interface IReportDataServices {
	public <T> boolean insert(T obj);
}
