package com.example.test.todo;

import androidx.annotation.NonNull;

public class ToDoModel {

    private Long id;
    private String title;
    private String description;


    public ToDoModel() {

    }

    public ToDoModel(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ToDoModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description=" + description +
                '}';
    }

    public String getNameAndYear() {
        return "title: " + this.title + ", description: " + this.description + ";";
    }

}
