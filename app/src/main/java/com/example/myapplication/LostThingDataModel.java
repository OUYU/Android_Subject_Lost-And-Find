package com.example.myapplication;

/**
 * Created by 歐歐 on 2016/5/10.
 */
public class LostThingDataModel {

    int id;
    String name;
    int imgid;
    String date;
    String Owner_of_lost_property;
    String address;
    String phone;

    LostThingDataModel(int id,String name, int imgid, String date,String user,String address,String phone) {
        this.id = id;
        this.name = name;
        this.imgid = imgid;
        this.date = date;
        this.Owner_of_lost_property = user;
        this.address = address;
        this.phone = phone;
    }
}
