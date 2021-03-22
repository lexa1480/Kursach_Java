package kurs.models;

public class Hotel {

    private int id;
    private String name;
    private int stars;
    private String picture;

    public Hotel() {

    }

    public Hotel(int id,String name, int stars, String picture) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.picture = picture;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
