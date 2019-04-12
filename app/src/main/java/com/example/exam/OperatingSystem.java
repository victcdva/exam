package com.example.exam;

import android.graphics.drawable.Drawable;

public class OperatingSystem {
    private String _description;
    private Drawable _image;
    private String _name;
    private int _lotSize;
    private int _constructionSize;
    private int _bedrooms;
    private double _bathrooms;
    private int _price;


    public String get_description() { return _description; }
    public void set_description(String _description) { this._description = _description; }
    public Drawable get_image() { return _image; }
    public void set_image(Drawable _image) { this._image = _image; }
    public String get_name() { return _name; }
    public void set_name(String _name) { this._name = _name; }
    public int get_lotSize() { return _lotSize; }
    public void set_lotSize(int _lotSize) { this._lotSize = _lotSize; }
    public int get_constructionSize() { return _constructionSize; }
    public void set_constructionSize(int _constructionSize) { this._constructionSize = _constructionSize; }
    public int get_bedrooms() { return _bedrooms; }
    public void set_bedrooms(int _bedrooms) { this._bedrooms = _bedrooms; }
    public double get_bathrooms() { return _bathrooms; }
    public void set_bathrooms(double _bathrooms) { this._bathrooms = _bathrooms; }
    public int get_price() { return _price; }
    public void set_price(int _price) { this._price = _price; }

    public OperatingSystem() {
        this._description = "";
        this._image = null;
        this._name = "";
        this._lotSize = 0;
        this._constructionSize = 0;
        this._bedrooms = 0;
        this._bathrooms = 0.0;
        this._price = 0;
    }

    public OperatingSystem(String description, String image, String name, int lotSize, int constructionSize, int bedrooms, double bathrooms, int price) {
        this._description = description;
        this._image = Image.fromUrl(image);
        this._name = name;
        this._lotSize = lotSize;
        this._constructionSize = constructionSize;
        this._bedrooms = bedrooms;
        this._bathrooms = bathrooms;
        this._price = price;

    }
}
