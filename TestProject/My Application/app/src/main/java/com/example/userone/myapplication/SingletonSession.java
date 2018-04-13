package com.example.userone.myapplication;

/**
 * Created by userone on 3/22/2018.
 */

public class SingletonSession {

    private static SingletonSession instance;
    private String username;
    private String[] demoArray;

    //no outer class can initialize this class's object
    private SingletonSession() {}

    public static SingletonSession Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new SingletonSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public String[] getDemoArray() {
        return demoArray;
    }

    public void setUsername(String username) {
        this.username = username;

    }

    public void setDemoArray(String[] demoArray) {
        this.demoArray = demoArray;
    }
}