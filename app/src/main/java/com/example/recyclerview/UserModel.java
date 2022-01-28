package com.example.recyclerview;

public class UserModel {
    private String name;
    private String followers;
    private String contributions;
    private String locations;

    public UserModel(String name,String locations,String followers,String contributions){
        this.name = name;
        this.locations = locations;
        this.followers = followers;
        this.contributions = contributions;
    }

    public String getContributions() {
        return contributions;
    }

    public String getFollowers() {
        return followers;
    }

    public String getName() {
        return name;
    }

    public String getLocations() {
        return locations;
    }
}
