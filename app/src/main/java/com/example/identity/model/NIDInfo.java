package com.example.identity.model;

import com.google.gson.annotations.SerializedName;

public class NIDInfo {

    @SerializedName("person_photo")
    private String personPhotoString;

    @SerializedName("national_id")
    private String nationalId;

    @SerializedName("team_tx_id")
    private String teamTxId;

    @SerializedName("english_output")
    private Boolean englishOutput;

    @SerializedName("person_dob")
    private String personDob;

    public NIDInfo(String personPhotoString, String nationalId, String teamTxId, Boolean englishOutput, String personDob) {
        this.personPhotoString = personPhotoString;
        this.nationalId = nationalId;
        this.teamTxId = teamTxId;
        this.englishOutput = englishOutput;
        this.personDob = personDob;
    }

    public String getPersonPhotoString() {
        return personPhotoString;
    }

    public void setPersonPhotoString(String personPhotoString) {
        this.personPhotoString = personPhotoString;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getTeamTxId() {
        return teamTxId;
    }

    public void setTeamTxId(String teamTxId) {
        this.teamTxId = teamTxId;
    }

    public Boolean getEnglishOutput() {
        return englishOutput;
    }

    public void setEnglishOutput(Boolean englishOutput) {
        this.englishOutput = englishOutput;
    }

    public String getPersonDob() {
        return personDob;
    }

    public void setPersonDob(String personDob) {
        this.personDob = personDob;
    }
}