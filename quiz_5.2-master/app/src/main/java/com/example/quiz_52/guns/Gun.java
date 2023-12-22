package com.example.quiz_52.guns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Gun {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "GUN_ID")
    private Long gunId;

    @ColumnInfo(name = "CALIBER")
    private Float caliber;

    @ColumnInfo(name = "GUN_NAME")
    private String gunName;

    @ColumnInfo(name = "GUN_RELEASE_YEAR")
    private Integer year;

    public Gun() {
    }

    public Gun(Long gunId, Float caliber, String gunName, Integer year) {
        this.gunId = gunId;
        this.caliber = caliber;
        this.gunName = gunName;
        this.year = year;
    }

    public Long getGunId() {
        return gunId;
    }

    public void setGunId(Long gunId) {
        this.gunId = gunId;
    }

    public Float getCaliber() {
        return caliber;
    }

    public void setCaliber(Float caliber) {
        this.caliber = caliber;
    }

    public String getGunName() {
        return gunName;
    }

    public void setGunName(String gunName) {
        this.gunName = gunName;
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
        return "gun{" +
                "gunId=" + gunId +
                ", caliber=" + caliber +
                ", gunName='" + gunName + '\'' +
                ", year=" + year +
                '}';
    }


    public String getNameAndCaliberAndYear() {
        return this.gunName + ", caliber: " + this.caliber + ", year: " + this.year + ";";
    }

}