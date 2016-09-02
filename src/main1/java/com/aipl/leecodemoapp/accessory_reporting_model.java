package com.aipl.leecodemoapp;

/**
 * Created by anantkannaik on 19/08/16.
 */
public class accessory_reporting_model {

    public String id;
   public String accessory;

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

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String volume;
    public String category;

    public accessory_reporting_model(String id, String accessory, String volume, String category) {
        this.id = id;
        this.accessory = accessory;
        this.volume = volume;
        this.category = category;
    }


}
