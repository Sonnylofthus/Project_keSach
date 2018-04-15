package com.giang.kesach.models;


import java.util.ArrayList;
import java.util.List;


public class Author {
    //aId
    private int aId;
    //author_name
    private String aName;
    //author_description
    private String aDescription;
    //author_birthday
    private int aBirthDay;
    //author_img
    private String aImg;
    private int writtenBookCount;
    private List<Book> writtenBooks;
    public int getaBirthDay() {
        return aBirthDay;
    }

    public void setaBirthDay(int aBirthDay) {
        this.aBirthDay = aBirthDay;
    }
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getWrittenBookCount() {
        return writtenBookCount;
    }

    public void setWrittenBookCount(int writenBookCount) {
        this.writtenBookCount = writenBookCount;
    }

    public List<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(List<Book> writenBooks) {
        this.writtenBooks = writenBooks;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }


    public String getaDescription() {
        // TODO Auto-generated method stub
        return aDescription;
    }

    public void setaDescription(String aDescription) {
        this.aDescription = aDescription;
    }


    public String getaImg() {
        return aImg;
    }

    public void setaImg(String aImg) {
        this.aImg = aImg;
    }


}
