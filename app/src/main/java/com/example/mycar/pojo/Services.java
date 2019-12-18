package com.example.mycar.pojo;

import com.example.mycar.model.Service;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Services {
    @SerializedName("services")
    @Expose
    private List<Service> services = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
