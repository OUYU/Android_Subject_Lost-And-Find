package com.example.myapplication;

/**
 * Created by 歐歐 on 2016/5/19.
 */
public class PickedThingDataModel {
    int exist;
    String name;
    int imgid;
    String date;
    String people_who_picked_up;
    String address;
    String phone;

    PickedThingDataModel(int exist,String name, int imgid, String date,String user,String address,String phone) {
        this.exist = exist;
        this.name = name;
        this.imgid = imgid;
        this.date = date;
        this.people_who_picked_up = user;
        this.address = address;
        this.phone = phone;
    }
}
