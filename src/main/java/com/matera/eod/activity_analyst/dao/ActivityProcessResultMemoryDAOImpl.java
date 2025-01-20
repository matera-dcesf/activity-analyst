package com.matera.eod.activity_analyst.dao;

import com.matera.eod.activity_analyst.record.ActivityProcessResult;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class ActivityProcessResultMemoryDAOImpl implements ActivityProcessResultDAO{

    private static final ConcurrentMap<Long, Set<ActivityProcessResult>> MEMORY_DATABASE = new ConcurrentHashMap<>();

    @Override
    public int countActivitiesByProcessingId(Long processingId) {
        Set<ActivityProcessResult> resultList = MEMORY_DATABASE.get(processingId);
        return resultList != null ? resultList.size() : 0;
    }

    @Override
    public void insert(ActivityProcessResult activityProcessResult) {
        Set<ActivityProcessResult> resultList = MEMORY_DATABASE.computeIfAbsent(activityProcessResult.processingId(),
                k -> new HashSet<>());
        resultList.add(activityProcessResult);
    }

    @Override
    public void deleteAll(Long processingId) {
        MEMORY_DATABASE.remove(processingId);
    }
}
