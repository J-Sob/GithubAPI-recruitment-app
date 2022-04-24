package com.example.allegrosummerexperience.model;

import java.util.List;
import java.util.Map;

public class SingleRepoModel {

    private String name;
    private String owner;
    private Map<String, Integer> languages;

    public SingleRepoModel(String name, String owner, Map<String, Integer> languages) {
        this.name = name;
        this.owner = owner;
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "SingleRepoModel{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", languages=" + languages +
                '}';
    }

    public SingleRepoModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Map<String, Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, Integer> languages) {
        this.languages = languages;
    }
}
