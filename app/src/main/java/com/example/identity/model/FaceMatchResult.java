package com.example.identity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FaceMatchResult implements Serializable {

    @SerializedName("matched")
    private Boolean matched;

    @SerializedName("percentage")
    private Integer percentage;

    public Boolean getMatched() {
        return matched;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "FaceMatchResult{" +
                "matched=" + matched +
                ", percentage=" + percentage +
                '}';
    }
}
