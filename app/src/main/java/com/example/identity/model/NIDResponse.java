package com.example.identity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NIDResponse implements Serializable {

    @SerializedName("voter")
    private Voter voter;

    @SerializedName("passKyc")
    private String passKyc;

    @SerializedName("errorCode")
    private String errorCode;

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public String getPassKyc() {
        return passKyc;
    }

    public void setPassKyc(String passKyc) {
        this.passKyc = passKyc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "NIDResponse{" +
                "voter=" + voter +
                ", passKyc='" + passKyc + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
