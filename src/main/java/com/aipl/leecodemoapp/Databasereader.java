package com.aipl.leecodemoapp;

public class Databasereader {
	
	int _id;
    String uniqueid;
    String uniquename;
 
    // Empty constructor
    public Databasereader(){
 
    }
    // constructor
    public Databasereader(int id, String uniqueid , String uniquename){
        this._id = id;
        this.uniqueid = uniqueid;
        this.uniquename = uniquename;
    }
 
    // constructor
    public Databasereader(String name, String _phone_number){
    	this.uniqueid = uniqueid;
        this.uniquename = uniquename;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
 
    // setting id
    public void setID(int id){
        this._id = id;
    }
 
    // getting name
    public String getuniqueid(){
        return this.uniqueid ;
    }
 
    // setting name
    public void setuniqueid(String name){
        this.uniqueid = name;
    }
 
    // getting phone number
    public String getuniquename(){
        return this.uniquename;
    }
 
    // setting phone number
    public void setuniquename(String phone_number){
        this.uniquename = phone_number;
    }
	

}
