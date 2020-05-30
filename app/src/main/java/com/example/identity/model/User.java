package com.example.identity.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("person_dob")
    private String personDob;

    @SerializedName("national_id")
    private String nationalId;

    @SerializedName("person_fullname")
    private String personFullname;

    @SerializedName("team_tx_id")
    private String teamTxId;

    @SerializedName("match_name")
    private Boolean matchName;

    public User(String personDob, String nationalId, String personFullname, String teamTxId, Boolean matchName) {
        this.personDob = personDob;
        this.nationalId = nationalId;
        this.personFullname = personFullname;
        this.teamTxId = teamTxId;
        this.matchName = matchName;
    }

    public String getPersonDob() {
        return personDob;
    }

    public void setPersonDob(String personDob) {
        this.personDob = personDob;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPersonFullname() {
        return personFullname;
    }

    public void setPersonFullname(String personFullname) {
        this.personFullname = personFullname;
    }

    public String getTeamTxId() {
        return teamTxId;
    }

    public void setTeamTxId(String teamTxId) {
        this.teamTxId = teamTxId;
    }

    public Boolean getMatchName() {
        return matchName;
    }

    public void setMatchName(Boolean matchName) {
        this.matchName = matchName;
    }
}
