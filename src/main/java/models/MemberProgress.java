package models;

import java.util.Date;

public class MemberProgress {
    private String progressId;
    private Date progressDate;
    private String muscleTrain;
    private float bodyweight;
    private float height;
    private float bmi;
    private String memberId;


    public MemberProgress(String progressId, Date progressDate, String muscleTrain, float bodyweight, float height,
            float bmi, String memberId) {
        this.progressId = progressId;
        this.progressDate = progressDate;
        this.muscleTrain = muscleTrain;
        this.bodyweight = bodyweight;
        this.height = height;
        this.bmi = bmi;
        this.memberId = memberId;
    }


    public String getProgressId() {
        return progressId;
    }


    public void setProgressId(String progressId) {
        this.progressId = progressId;
    }


    public Date getProgressDate() {
        return progressDate;
    }


    public void setProgressDate(Date progressDate) {
        this.progressDate = progressDate;
    }


    public String getMuscleTrain() {
        return muscleTrain;
    }


    public void setMuscleTrain(String muscleTrain) {
        this.muscleTrain = muscleTrain;
    }


    public float getBodyweight() {
        return bodyweight;
    }


    public void setBodyweight(float bodyweight) {
        this.bodyweight = bodyweight;
    }


    public float getHeight() {
        return height;
    }


    public void setHeight(float height) {
        this.height = height;
    }


    public float getBmi() {
        return bmi;
    }


    public void setBmi(float bmi) {
        this.bmi = bmi;
    }


    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        return "MemberProgress [progressId=" + progressId + ", progressDate=" + progressDate + ", muscleTrain="
                + muscleTrain + ", bodyweight=" + bodyweight + ", height=" + height + ", bmi=" + bmi + ", memberId="
                + memberId + "]";
    }

}
