package com.hari.development.faasosdelight;

/**
 * Created by development on 26/09/15.
 */
public class Faassos {

    private String name;
    private String image;
    private String category;
    private int spice_meter;
    private String description;
    private double rating;
    private int price;
    private String is_veg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int isSpice_meter() {
        return spice_meter;
    }

    public void setSpice_meter(int spice_meter) {
        this.spice_meter = spice_meter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String is_veg() {
        return is_veg;
    }

    public void setIs_veg(String is_veg) {
        this.is_veg = is_veg;
    }
}
