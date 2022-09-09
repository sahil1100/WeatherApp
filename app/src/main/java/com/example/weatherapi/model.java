package com.example.weatherapi;

import java.util.ArrayList;

public class model {

    public ArrayList<Weather> weather;
    public Coord coord;
//    public String base;
    public Main main;
//    public int visibility;
    public Wind wind;
//    public int dt;
    public Sys sys;
//    public int timezone;
//    public int id;
//    public String name;
//    public int cod;


    public model(ArrayList<Weather> weather, Coord coord, Main main, Wind wind, Sys sys) {
        this.weather = weather;
        this.coord = coord;
        this.main = main;
        this.wind = wind;
        this.sys = sys;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

//    public String getBase() {
//        return base;
//    }
//
//    public void setBase(String base) {
//        this.base = base;
//    }
//
//    public int getVisibility() {
//        return visibility;
//    }
//
//    public void setVisibility(int visibility) {
//        this.visibility = visibility;
//    }
//
//    public int getDt() {
//        return dt;
//    }
//
//    public void setDt(int dt) {
//        this.dt = dt;
//    }
//
//    public int getTimezone() {
//        return timezone;
//    }
//
//    public void setTimezone(int timezone) {
//        this.timezone = timezone;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getCod() {
//        return cod;
//    }
//
//    public void setCod(int cod) {
//        this.cod = cod;
//    }
}
