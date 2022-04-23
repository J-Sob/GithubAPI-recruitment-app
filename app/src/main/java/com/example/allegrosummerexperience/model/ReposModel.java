package com.example.allegrosummerexperience.model;

public class ReposModel {
    private String name;

    public ReposModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReposModel{" +
                "name='" + name +
                '}';
    }

    public ReposModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrivate(boolean aPrivate) {
    }
}
