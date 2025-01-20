package com.matera.eod.activity_analyst.dao;

import com.matera.eod.activity_analyst.record.ActivityProcessResult;

public interface ActivityProcessResultDAO {
    int countActivitiesByProcessingId(Long processingId);
    void insert(ActivityProcessResult activityProcessResult);
    void deleteAll(Long processingId);
}
