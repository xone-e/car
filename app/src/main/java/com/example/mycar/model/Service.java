package com.example.mycar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("oilKilometer")
    @Expose
    private String oilKilometer;
    @SerializedName("EstimateKilometer")
    @Expose
    private String estimateKilometer;
    @SerializedName("EstimateDate")
    @Expose
    private String estimateDate;
    @SerializedName("notificationDate")
    @Expose
    private String notificationDate;
    @SerializedName("isDone")
    @Expose
    private boolean isDone;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOilKilometer() {
        return oilKilometer;
    }

    public void setOilKilometer(String oilKilometer) {
        this.oilKilometer = oilKilometer;
    }

    public String getEstimateKilometer() {
        return estimateKilometer;
    }

    public void setEstimateKilometer(String estimateKilometer) {
        this.estimateKilometer = estimateKilometer;
    }

    public String getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(String estimateDate) {
        this.estimateDate = estimateDate;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
