package com.example.user.celebrity;

/**
 * Created by user on 1/20/2018.
 */

public class Person {

    public int id;
    private String name;
    private String title;
    private int image;
    private int like;

    public Person(int id, String name, String title, int image, int like) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.image = image;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}