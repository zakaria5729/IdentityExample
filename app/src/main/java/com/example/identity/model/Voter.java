package com.example.identity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Voter implements Serializable {

    @SerializedName("faceMatchResult")
    private FaceMatchResult faceMatchResult;

    @SerializedName("name")
    private String name;

    @SerializedName("nameEn")
    private String nameEn;

    @SerializedName("father")
    private String father;

    @SerializedName("mother")
    private String mother;

    @SerializedName("gender")
    private String gender;

    @SerializedName("spouse")
    private String spouse;

    @SerializedName("dob")
    private String dob;

    @SerializedName("permanentAddress")
    private String permanentAddress;

    @SerializedName("presentAddress")
    private String presentAddress;

    @SerializedName("photo")
    private String photo;

    @SerializedName("fatherEn")
    private String fatherEn;

    @SerializedName("motherEn")
    private String motherEn;

    @SerializedName("spouseEn")
    private String spouseEn;

    @SerializedName("permanentAddressEn")
    private String permanentAddressEn;

    @SerializedName("presentAddressEn")
    private String presentAddressEn;

    public FaceMatchResult getFaceMatchResult() {
        return faceMatchResult;
    }

    public void setFaceMatchResult(FaceMatchResult faceMatchResult) {
        this.faceMatchResult = faceMatchResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFatherEn() {
        return fatherEn;
    }

    public void setFatherEn(String fatherEn) {
        this.fatherEn = fatherEn;
    }

    public String getMotherEn() {
        return motherEn;
    }

    public void setMotherEn(String motherEn) {
        this.motherEn = motherEn;
    }

    public String getSpouseEn() {
        return spouseEn;
    }

    public void setSpouseEn(String spouseEn) {
        this.spouseEn = spouseEn;
    }

    public String getPermanentAddressEn() {
        return permanentAddressEn;
    }

    public void setPermanentAddressEn(String permanentAddressEn) {
        this.permanentAddressEn = permanentAddressEn;
    }

    public String getPresentAddressEn() {
        return presentAddressEn;
    }

    public void setPresentAddressEn(String presentAddressEn) {
        this.presentAddressEn = presentAddressEn;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "faceMatchResult=" + faceMatchResult +
                ", name='" + name + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", gender='" + gender + '\'' +
                ", spouse='" + spouse + '\'' +
                ", dob='" + dob + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                ", photo='" + photo + '\'' +
                ", fatherEn='" + fatherEn + '\'' +
                ", motherEn='" + motherEn + '\'' +
                ", spouseEn='" + spouseEn + '\'' +
                ", permanentAddressEn='" + permanentAddressEn + '\'' +
                ", presentAddressEn='" + presentAddressEn + '\'' +
                '}';
    }
}
