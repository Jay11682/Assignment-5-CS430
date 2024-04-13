package com.example.model;

public class Use {
    private int userId;
    private int deviceId;
    private String usageDate;
    private int usageDuration;

    // Constructor
    public Use(int userId, int deviceId, String usageDate, int usageDuration) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.usageDate = usageDate;
        this.usageDuration = usageDuration;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(String usageDate) {
        this.usageDate = usageDate;
    }

    public int getUsageDuration() {
        return usageDuration;
    }

    public void setUsageDuration(int usageDuration) {
        this.usageDuration = usageDuration;
    }
}