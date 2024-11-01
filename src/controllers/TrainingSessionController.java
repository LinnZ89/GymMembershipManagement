package controllers;

import models.TrainingSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TrainingSessionController {
    private List<TrainingSession> trainingSessions = new ArrayList<>();

    public TrainingSession createTrainingSession(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter Session ID: ");
        String sessionId = scanner.nextLine();

        System.out.print("Enter Start Date (dd/MM/yyyy): ");
        Date dayStart = null;
        try {
            dayStart = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        System.out.print("Enter End Date (dd/MM/yyyy): ");
        Date dayEnd = null;
        try {
            dayEnd = dateFormat.parse(scanner.nextLine());
            if (dayEnd.before(dayStart)) {
                System.out.println("End date must be after the start date.");
                return null;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        System.out.print("Enter Session Location: ");
        String sessionLocation = scanner.nextLine();

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Trainer ID: ");
        String trainerId = scanner.nextLine();

        TrainingSession newSession = new TrainingSession(sessionId, dayStart, dayEnd, sessionLocation, memberId, trainerId);
        this.addTrainingSession(newSession);
        System.out.println("Training session created successfully!");
        return newSession;
    }

    public void addTrainingSession(TrainingSession session) {
        if (session != null && session.getDayStart().before(session.getDayEnd())) {
            trainingSessions.add(session);
            System.out.println("Training session added.");
        } else {
            System.out.println("Invalid session details.");
        }
    }

    public boolean deleteTrainingSession(String sessionId) {
        TrainingSession session = getSessionById(sessionId);
        if (session != null) {
            trainingSessions.remove(session);
            System.out.println("Training session deleted successfully.");
            return true;
        }
        System.out.println("Training session not found.");
        return false;
    }

    public boolean updateTrainingSession(String sessionId, String newLocation, String newTrainerId) {
        TrainingSession session = getSessionById(sessionId);
        if (session != null) {
            session.setSessionLocation(newLocation);
            session.setTrainerId(newTrainerId);
            System.out.println("Training session updated successfully.");
            return true;
        }
        System.out.println("Training session not found.");
        return false;
    }

    public TrainingSession getSessionById(String trainingSessionId) {
        for (TrainingSession trainingSession : trainingSessions) {
            if (trainingSession.getMemberId() == trainingSessionId) {
                return trainingSession;
            }
        }
        return null;
    }

    public void viewAllTrainingSessions() {
        if (trainingSessions.isEmpty()) {
            System.out.println("No training sessions available.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(String.format("| %-12s | %-12s | %-12s | %-15s | %-10s | %-10s |", 
                                         "Session ID", "Start Date", "End Date", "Location", "Member ID", "Trainer ID"));
        System.out.println("---------------------------------------------------------------------------------------");

        for (TrainingSession session : trainingSessions) {
            System.out.println(String.format("| %-12s | %-12s | %-12s | %-15s | %-10s | %-10s |",
                                             session.getSessionId(),
                                             dateFormat.format(session.getDayStart()),
                                             dateFormat.format(session.getDayEnd()),
                                             session.getSessionLocation(),
                                             session.getMemberId(),
                                             session.getTrainerId()));
        }
    }
}
