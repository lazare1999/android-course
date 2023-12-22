package ge.msda.myapplication.api.models;

import com.google.gson.annotations.SerializedName;

public class Resource {

    private Long id;
    private String name;
    private String year;
    private String color;
    @SerializedName("pantone_value")
    private String pantoneValue;


    public Resource() {
    }

    public Resource(Long id, String name, String year, String color, String pantoneValue) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantoneValue = pantoneValue;
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


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantoneValue() {
        return pantoneValue;
    }

    public void setPantoneValue(String pantoneValue) {
        this.pantoneValue = pantoneValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", pantoneValue='" + pantoneValue + '\'' +
                '}';
    }
}
