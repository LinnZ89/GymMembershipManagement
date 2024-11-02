package controllers;

import models.Trainer;
import repositories.TrainerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrainerController {
    private final TrainerRepository trainerRepository = new TrainerRepository();
    private final List<Trainer> trainers = new ArrayList<>();

    public TrainerController() {
        this.trainers.addAll(trainerRepository.getAllTrainers());
    }

    public Trainer createTrainer(Scanner scanner) {
        System.out.print("Enter Trainer's ID: ");
        String trainerId = scanner.nextLine();

        System.out.print("Enter Trainer's Name: ");
        String trainerName = scanner.nextLine();

        System.out.print("Enter Trainer's Specialization: ");
        String trainerSpecialization = scanner.nextLine();

        Trainer trainer = new Trainer(trainerId, trainerName, trainerSpecialization);

        trainerRepository.addTrainer(trainer);
        trainers.add(trainer);
        System.out.println("Trainer added successfully.");
        
        return trainer;
    }

    public Trainer getTrainerByID(String trainerId) {
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId().equals(trainerId)) {
                return trainer;
            }
        }
        System.out.println("Trainer not found.");
        return null;
    }

    public boolean updateTrainer(String trainerId, String newName, String newSpecialization) {
        Trainer trainer = getTrainerByID(trainerId);
        if (trainer != null) {
            trainer.setName(newName);
            trainer.setSpecialization(newSpecialization);

            trainerRepository.updateTrainer(trainerId, newName, newSpecialization);
            System.out.println("Trainer updated successfully.");
            return true;
        }
        System.out.println("Trainer not found.");
        return false;
    }

    public boolean deleteTrainer(String trainerId) {
        Trainer trainer = getTrainerByID(trainerId);
        if (trainer != null) {
            trainers.remove(trainer);
            trainerRepository.deleteTrainer(trainerId);
            System.out.println("Trainer deleted successfully.");
            return true;
        }
        System.out.println("Trainer not found.");
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
            System.out.println(String.format("| %-10s | %-20s | %-20s |",
                trainer.getTrainerId(),
                trainer.getName(),
                trainer.getSpecialization()
            ));
        }
    }

    public void reloadTrainersFromDatabase() {
        trainers.clear();
        trainers.addAll(trainerRepository.getAllTrainers());
    }
}
