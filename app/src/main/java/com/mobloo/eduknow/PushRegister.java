package com.mobloo.eduknow;


import java.io.Serializable;

public class PushRegister implements Serializable
{

    private String device_channel;
    private int student_id;

    PushRegister(String token,int id)
    {
        this.device_channel = token;
        this.student_id = id;
    }


    public String getToken()
    {
        return device_channel;
    }

    public int getSID()
    {
        return student_id;
    }

}
