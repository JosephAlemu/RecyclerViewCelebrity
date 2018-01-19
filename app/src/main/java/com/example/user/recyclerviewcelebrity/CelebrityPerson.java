package com.example.user.recyclerviewcelebrity;

/**
 * Created by user on 1/16/2018.
 */

public class CelebrityPerson {

    Integer id;
    String strFirstName;
    String strLastName;
    String strTittle;
    String  strDescription;


    public CelebrityPerson(Integer id, String strFirstName, String strLastName, String strTittle, String strDescription) {
        this.id = id;
        this.strFirstName = strFirstName;
        this.strLastName = strLastName;
        this.strTittle = strTittle;
        this.strDescription = strDescription;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrFirstName() {
        return strFirstName;
    }

    public void setStrFirstName(String strFirstName) {
        this.strFirstName = strFirstName;
    }

    public String getStrLastName() {
        return strLastName;
    }

    public void setStrLastName(String strLastName) {
        this.strLastName = strLastName;
    }

    public String getStrTittle() {
        return strTittle;
    }

    public void setStrTittle(String strTittle) {
        this.strTittle = strTittle;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }


    @Override
    public String toString() {
        return "CelebrityData{" +
                "id=" + id +
                ", strFirstName='" + strFirstName + '\'' +
                ", strLastName='" + strLastName + '\'' +
                ", strTittle='" + strTittle + '\'' +
                ", event_description='" + strDescription + '\'' +
                '}';
    }
}
