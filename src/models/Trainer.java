package models;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String trainerId;
    private String name;
    private String specialization;


    private List<String> sessions = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public Trainer(String trainerId, String name, String specialization) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialization = specialization;
    }


    public String getTrainerId() {
        return trainerId;
    }



    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getSpecialization() {
        return specialization;
    }



    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



    public void scheduleSession(String sessionName, String date, int maxParticipants) {
        String session = sessionName + " on " + date + " (Max: " + maxParticipants + " participants)";
        sessions.add(session);
        System.out.println("Session scheduled: " + session);
    }

    public void viewSessions() {
        for (String session : sessions) {
            System.out.println(session);
        }
    }


    public void addMemberProgress(Member member, String progress) {
        members.add(member);
        member.addProgress(progress);
        System.out.println("Progress added for member: " + member.getName());
    }
    
    public String toString(){
        return String.format("%s %s %s", trainerId, name, specialization);
    }

    
}
