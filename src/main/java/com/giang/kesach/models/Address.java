package com.giang.kesach.models;

public class Address {
    private String city;
    private String district;
    private String ward;
    private String number;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", ward='" + ward + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
