package com.aipl.leecodemoapp;

/**
 * Created by anantkannaik on 22/08/16.
 */
public class weekly_stock_reporting_model {

    public String date;

    public weekly_stock_reporting_model(String date, String model_no, String model_no_id, String volume) {
        this.date = date;
        this.model_no = model_no;
        this.model_no_id = model_no_id;
        this.volume = volume;
    }

    public String model_no;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getModel_no() {
        return model_no;
    }

    public void setModel_no(String model_no) {
        this.model_no = model_no;
    }

    public String getModel_no_id() {
        return model_no_id;
    }

    public void setModel_no_id(String model_no_id) {
        this.model_no_id = model_no_id;
    }

    public String model_no_id;
    public String volume;

}
