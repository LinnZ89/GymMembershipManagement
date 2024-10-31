package controllers;

import java.util.ArrayList;
import java.util.List;
import models.TrainingSession;

public class TrainingSessionController {

    private List<TrainingSession> trainingSessions;

    public TrainingSessionController() {
        this.trainingSessions = new ArrayList<>();
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(List<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public TrainingSession getSessionById(String sessionId) {
        for (TrainingSession session : trainingSessions) {
            if (session.getSessionId().equals(sessionId)) {
                return session;
            }
        }
        return null;
    }

    public void addTrainingSession(TrainingSession session) {
        if (session != null) {
            trainingSessions.add(session);
        }
    }

    public boolean updateTrainingSession(String sessionId, String newLocation, String newTrainerId, String newTime) {
        TrainingSession session = getSessionById(sessionId);
        if (session != null) {
            session.setSessionLocation(newLocation);
            session.setTrainerId(newTrainerId);
            session.setSessionTime(newTime);
            return true;
        }
        return false;
    }

    public boolean deleteTrainingSession(String sessionId) {
        TrainingSession session = getSessionById(sessionId);
        if (session != null) {
            trainingSessions.remove(session);
            return true;
        }
        return false;
    }

    public List<TrainingSession> searchSessionsByTrainer(String trainerId) {
        List<TrainingSession> result = new ArrayList<>();
        for (TrainingSession session : trainingSessions) {
            if (session.getTrainerId().equalsIgnoreCase(trainerId)) {
                result.add(session);
            }
        }
        return result;
    }

    public List<TrainingSession> searchSessionsByDate(String date) {
        List<TrainingSession> result = new ArrayList<>();
        for (TrainingSession session : trainingSessions) {
            if (session.getSessionDate().equals(date)) {
                result.add(session);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (trainingSessions.isEmpty()) {
            return "No training sessions available.";
        }
        StringBuilder str = new StringBuilder(String.format("|%15s|%20s|%15s|%15s|\n", "Session ID", "Location", "Trainer ID", "Time"));
        for (TrainingSession session : trainingSessions) {
            str.append(String.format("|%15s|%20s|%15s|%15s|\n", session.getSessionId(), session.getSessionLocation(), session.getTrainerId(), session.getSessionTime()));
        }
        return str.toString();
    }
}