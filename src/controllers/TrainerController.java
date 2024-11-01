package controllers;

import models.Trainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerController {
    private List<Trainer> trainers = new ArrayList<>();

    public Trainer createTrainer (Scanner scanner) {
        System.out.println("Enter Trainer's ID: ");
        String trainerId = scanner.nextLine();

        System.out.println("Enter Trainer's Name: ");
        String trainerName = scanner.nextLine();

        System.out.println("Enter Trainer's Specialization: ");
        String trainerSpecialization = scanner.nextLine();

        Trainer trainer = new Trainer(trainerId, trainerName, trainerSpecialization);
        this.addTrainer(trainer);
        return trainer;
    }
    
    public void addTrainer(Trainer trainer) {
        if (trainer != null) {
            trainers.add(trainer);
            System.out.println("Trainer added.");
        } else {
            System.out.println("Invalid trainer details.");
        }
    }

    public Trainer getTrainerByID(String trainerID) {
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId() == trainerID) {
                return trainer;
            }
        }
        return null;
    }

    public boolean deleteTrainer(String trainerID) {
        Trainer trainer = getTrainerByID(trainerID);
        if (trainer != null) {
            trainers.remove(trainer);
            return true;
        }
        return false;
    }

    public boolean updateTrainer(String trainerId, String newName, String newSpecialization) {
        Trainer trainer = getTrainerByID(trainerId);
        if (trainer != null) {
            trainer.setName(newName);
            trainer.setSpecialization(newSpecialization);
            return true;
        }
        return false;
    }

    public void viewAllTrainers() {
        if (trainers.isEmpty()) {
            System.out.println("No trainers available.");
            return;
        }

        System.out.println(String.format("| %-10s | %-20s | %-20s |", "Trainer ID", "Name", "Specialization"));
        System.out.println("------------------------------------------------------------");
        
        for (Trainer trainer : trainers) {
            System.out.println(String.format("| %-10d | %-20s | %-20s |", 
                trainer.getTrainerId(),
                trainer.getName(),
                trainer.getSpecialization()
            ));
        }
    }
    
}
