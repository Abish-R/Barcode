package com.aipl.leecodemoapp;

/**
 * Created by anantkannaik on 19/08/16.
 */
public class accessory_reporting_model {

    public accessory_reporting_model(String date, String accessory_id, String category_id, String accessory, String volume, String category) {
        this.date = date;
        this.accessory_id = accessory_id;
        this.category_id = category_id;
        this.accessory = accessory;
        this.volume = volume;
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccessory_id() {
        return accessory_id;
    }

    public void setAccessory_id(String accessory_id) {
        this.accessory_id = accessory_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String date;
     public String accessory_id;
    public String category_id;
    public String accessory;
     public String volume;
    public String category;


}
