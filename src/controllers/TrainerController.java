package controllers;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import models.Trainer;

public class TrainerController {
    private List<Trainer> trainers = new ArrayList<>();
    private List<String> sessions = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    
    public TrainerController(){
        trainers = new ArrayList<>();
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Trainer getTrainer(int trainerId){
        for(Trainer trainer : trainers){
            if(trainer.getTrainerId() == trainerId){
                return trainer;
            }
        }
        return null;
    }

    public void addTrainer(Trainer trainer){
        if(trainer != null){
            trainers.add(trainer);
        }
    }

    public void removeTrainer(int trainerId){
        Trainer trainer = getTrainer(trainerId);
        if(trainer != null){
            trainers.remove(trainer);
        }
    }

    public void updateTrainer(int newTrainerID, String newName, String newSpecialization){
        Trainer trainer = getTrainer(newTrainerID);
        if(trainer != null){
            trainer.setName(newName);
            trainer.setSpecialization(newSpecialization);
        }
    }

    public List<Trainer> searchTrainerByName(String name){
        List<Trainer> result = new ArrayList<>();
        for(Trainer trainer : trainers){
            if(trainer.getName().contains(name)){
                result.add(trainer);
            }
        }
        return result;
    }
    public List<Trainer> searchTrainerByID(int trainerID){
        List<Trainer> result = new ArrayList<>();
        for(Trainer trainer : trainers){
            if(trainer.getTrainerId() == trainerID){
                result.add(trainer);
            }
        }
        return result;
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

    public String toString(){
        if(trainers.isEmpty()){
            return null;
        }
        String str = String.format("%15s|%15s|%35s\n", "TrainerID", "Name", "Specialization");
        for (Trainer trainer : trainers) {
            str += String.format("%15d|%15s|%35s\n", trainer.getTrainerId(), trainer.getName(), trainer.getSpecialization());
        }
        return str;
    }
    
}
