package com.likandr.condortestapp.data.somedata.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.likandr.condortestapp.data._models.SomeData;

import java.util.List;

public class SomeDataResponse {

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("page")
    private String page;
    @Expose
    @SerializedName("data")
    private List<SomeData> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<SomeData> getList() {
        return data;
    }

    public void setList(List<SomeData> data) {
        this.data = data;
    }
}
