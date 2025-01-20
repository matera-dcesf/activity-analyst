package com.matera.eod.activity_analyst.record;

public record ActivityProcessResult(Long processingId, String processName, String activityName, int numberOfActivities, String msg) {
}
