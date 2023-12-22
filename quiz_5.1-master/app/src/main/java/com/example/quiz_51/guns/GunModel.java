package com.example.quiz_51.guns;

import androidx.annotation.NonNull;

public class GunModel {

    private Long id;
    private String name;
    private Float caliber;
    private Integer year;


    public GunModel() {

    }

    public GunModel(Long id, String name, Float engine, Integer year) {
        this.id = id;
        this.name = name;
        this.caliber = engine;
        this.year = year;
    }

    public GunModel(String name, Float engine, Integer year) {
        this.name = name;
        this.caliber = engine;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCaliber() {
        return caliber;
    }

    public void setCaliber(Float engine) {
        this.caliber = engine;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    @NonNull
    @Override
    public String toString() {
        return "Gun{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", caliber=" + caliber +
                ", year=" + year +
                '}';
    }

    public String getNameAndCaliberAndYear() {
        return this.name + ", caliber: " + this.caliber + ", year: " + this.year + ";";
    }

}
