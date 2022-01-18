package com.example.controlsystem;

import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;

public class WorkerRecord {
    private SimpleStringProperty name;
    private SimpleStringProperty day1;
    private SimpleStringProperty day2;

    public HashMap<String, String> fields=new HashMap<>();

    public WorkerRecord(String name, String day1, String day2, String day3, String day4,
                        String day5, String day6, String day7) {
        this.name = new SimpleStringProperty(name);
        this.day1 = new SimpleStringProperty(day1);
        this.day2 = new SimpleStringProperty(day2);
        this.day3 = new SimpleStringProperty(day3);
        this.day4 = new SimpleStringProperty(day4);
        this.day5 = new SimpleStringProperty(day5);
        this.day6 = new SimpleStringProperty(day6);
        this.day7 = new SimpleStringProperty(day7);
        this.fields.put("name", name);
        this.fields.put("day1", day1);
        this.fields.put("day2", day2);
        this.fields.put("day3", day3);
        this.fields.put("day4", day4);
        this.fields.put("day5", day5);
        this.fields.put("day6", day6);
        this.fields.put("day7", day7);
    }
    public WorkerRecord(){
        this.name = new SimpleStringProperty("");
        this.day1 = new SimpleStringProperty("");
        this.day2 = new SimpleStringProperty("");
        this.day3 = new SimpleStringProperty("");
        this.day4 = new SimpleStringProperty("");
        this.day5 = new SimpleStringProperty("");
        this.day6 = new SimpleStringProperty("");
        this.day7 = new SimpleStringProperty("");
        this.fields.put("name", "");
        this.fields.put("day1", "");
        this.fields.put("day2", "");
        this.fields.put("day3", "");
        this.fields.put("day4", "");
        this.fields.put("day5", "");
        this.fields.put("day6", "");
        this.fields.put("day7", "");
    }


    public String getName() {
        return this.name.get();
    }

    public SimpleStringProperty nameProperty() {
        return this.name;
    }

    public void setName(String name) {
        this.fields.put("name", name);
        this.name.set(name);
    }

    public String getDay1() {
        return this.day1.get();
    }
    public String getStatus(){
        return "READY";
    }

    public SimpleStringProperty day1Property() {
        return this.day1;
    }

    public void setDay1(String day1) {
        this.fields.put("day1", day1);
        this.day1.set(day1);
    }

    public String getDay2() {
        return day2.get();
    }

    public SimpleStringProperty day2Property() {
        return day2;
    }

    public void setDay2(String day2) {
        this.fields.put("day2", day2);
        this.day2.set(day2);
    }

    public String getDay3() {
        return day3.get();
    }

    public SimpleStringProperty day3Property() {
        return day3;
    }

    public void setDay3(String day3) {
        this.fields.put("day3", day3);
        this.day3.set(day3);
    }

    public String getDay4() {
        return day4.get();
    }

    public SimpleStringProperty day4Property() {
        return day4;
    }

    public void setDay4(String day4) {
        this.fields.put("day4", day4);
        this.day4.set(day4);
    }

    public String getDay5() {
        return day5.get();
    }

    public SimpleStringProperty day5Property() {
        return day5;
    }

    public void setDay5(String day5) {
        this.fields.put("day5", day5);
        this.day5.set(day5);
    }

    public String getDay6() {
        return day6.get();
    }

    public SimpleStringProperty day6Property() {
        return day6;
    }

    public void setDay6(String day6) {
        this.fields.put("day6", day6);
        this.day6.set(day6);
    }

    public String getDay7() {
        return day7.get();
    }

    public SimpleStringProperty day7Property() {
        return day7;
    }

    public void setDay7(String day7) {
        this.fields.put("day7", day7);
        this.day7.set(day7);
    }

    private SimpleStringProperty day3;
    private SimpleStringProperty day4;
    private SimpleStringProperty day5;
    private SimpleStringProperty day6;
    private SimpleStringProperty day7;
}
