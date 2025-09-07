package com.example.demo.Models;

public class UserInfModel{
    private Integer id;
    private String identity_number;
    private String name;
    private String surname;
    private Integer isAdmin;
    private Integer cityCode;
    private Integer institution_id;


    public UserInfModel(Integer id, String identity_number, String name, String surname, Integer isAdmin, Integer cityCode, Integer institution_id) {
        this.id = id;
        this.identity_number = identity_number;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
        this.cityCode = cityCode;
        this.institution_id = institution_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getInstitution_id() {
        return institution_id;
    }

    public void setInstitution_id(Integer institution_id) {
        this.institution_id = institution_id;
    }
}
