package com.example.app.Retrofit;

public class ClassModel {
    String degree_id,module,day,time,venue;

    public ClassModel(String module, String day, String time, String venue) {
        this.module = module;
        this.day = day;
        this.time = time;
        this.venue = venue;
    }

    public String getDegree_id() {
        return degree_id;
    }

    public void setDegree_id(String degree_id) {
        this.degree_id = degree_id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
