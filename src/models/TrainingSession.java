package models;

import java.sql.Date;
import java.sql.Time;

public class TrainingSession {
    private String sessionId;
    private Date dayStart;
    private Date dayEnd;
    private String sessionLocation;
    private String memberId;
    private String trainerId;

    
    public TrainingSession(String sessionId, Date dayStart, Date dayEnd, String sessionLocation, String memberId, String trainerId) {
        this.sessionId = sessionId;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.sessionLocation = sessionLocation;
        this.memberId = memberId;
        this.trainerId = trainerId;
    }


    public String getSessionId() {
        return sessionId;
    }


    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public Date getDayStart() {
        return dayStart;
    }


    public void setDayStart(Date dayStart) {
        this.dayStart = dayStart;
    }


    public Date getDayEnd() {
        return dayEnd;
    }


    public void setDayEnd(Date dayEnd) {
        this.dayEnd = dayEnd;
    }


    public String getSessionLocation() {
        return sessionLocation;
    }


    public void setSessionLocation(String sessionLocation) {
        this.sessionLocation = sessionLocation;
    }

    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public String getTrainerId() {
        return trainerId;
    }


    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    
}
