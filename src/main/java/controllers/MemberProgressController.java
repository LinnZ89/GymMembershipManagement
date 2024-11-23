package controllers;

import models.MemberProgress;
import repositories.MemberProgressRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MemberProgressController {
    private final MemberProgressRepository memberProgressRepository = new MemberProgressRepository();
    private final List<MemberProgress> memberProgressList = new ArrayList<>();

    public MemberProgressController() {
        this.memberProgressList.addAll(memberProgressRepository.getAllMemberProgress());
    }

    public MemberProgress createMemberProgress(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter Progress ID: ");
        String progressId = scanner.nextLine();

        System.out.print("Enter Progress Date (dd/MM/yyyy): ");
        Date progressDate;
        try {
            progressDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return null;
        }

        System.out.print("Enter Muscle Trained: ");
        String muscleTrain = scanner.nextLine();

        System.out.print("Enter Bodyweight: ");
        float bodyweight = scanner.nextFloat();

        System.out.print("Enter Height: ");
        float height = scanner.nextFloat();

        System.out.print("Enter BMI: ");
        float bmi = scanner.nextFloat();
        scanner.nextLine(); 

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        MemberProgress newProgress = new MemberProgress(progressId, progressDate, muscleTrain, bodyweight, height, bmi, memberId);

        memberProgressRepository.addMemberProgress(newProgress);
        memberProgressList.add(newProgress);
        System.out.println("Member progress created successfully!");
        
        return newProgress;
    }

    public boolean updateMemberProgress(String progressId, Scanner scanner) {
        MemberProgress progress = getProgressById(progressId);
        if (progress == null) {
            System.out.println("Progress not found.");
            return false;
        }

        System.out.print("Enter new Muscle Trained (current: " + progress.getMuscleTrain() + "): ");
        String newMuscleTrain = scanner.nextLine();
        if (!newMuscleTrain.isEmpty()) {
            progress.setMuscleTrain(newMuscleTrain);
        }

        System.out.print("Enter new Bodyweight (current: " + progress.getBodyweight() + "): ");
        String bodyweightInput = scanner.nextLine();
        if (!bodyweightInput.isEmpty()) {
            progress.setBodyweight(Float.parseFloat(bodyweightInput));
        }

        System.out.print("Enter new Height (current: " + progress.getHeight() + "): ");
        String heightInput = scanner.nextLine();
        if (!heightInput.isEmpty()) {
            progress.setHeight(Float.parseFloat(heightInput));
        }

        System.out.print("Enter new BMI (current: " + progress.getBmi() + "): ");
        String bmiInput = scanner.nextLine();
        if (!bmiInput.isEmpty()) {
            progress.setBmi(Float.parseFloat(bmiInput));
        }

        memberProgressRepository.updateMemberProgress(progress);
        System.out.println("Member progress updated successfully.");
        return true;
    }

    public boolean deleteMemberProgress(String progressId) {
        MemberProgress progress = getProgressById(progressId);
        if (progress != null) {
            memberProgressList.remove(progress);
            memberProgressRepository.deleteMemberProgress(progressId);
            System.out.println("Member progress deleted successfully.");
            return true;
        }
        System.out.println("Member progress not found.");
        return false;
    }

    public void viewAllMemberProgress() {
        if (memberProgressList.isEmpty()) {
            System.out.println("No member progress records available.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(String.format("| %-12s | %-12s | %-15s | %-10s | %-10s | %-10s | %-10s |", 
                                         "Progress ID", "Date", "Muscle Trained", "Bodyweight", "Height", "BMI", "Member ID"));
        System.out.println("------------------------------------------------------------------------------------------");

        for (MemberProgress progress : memberProgressList) {
            System.out.println(String.format("| %-12s | %-12s | %-15s | %-10.2f | %-10.2f | %-10.2f | %-10s |",
                                             progress.getProgressId(),
                                             dateFormat.format(progress.getProgressDate()),
                                             progress.getMuscleTrain(),
                                             progress.getBodyweight(),
                                             progress.getHeight(),
                                             progress.getBmi(),
                                             progress.getMemberId()));
        }
    }

    public MemberProgress getProgressById(String progressId) {
        for (MemberProgress progress : memberProgressList) {
            if (progress.getProgressId().equals(progressId)) {
                return progress;
            }
        }
        System.out.println("Progress not found in memory.");
        return null;
    }
}
