package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.MemberProgress;

public class MemberProgressController {
    private List<MemberProgress> memberProgressList;

    public MemberProgressController() {
        this.memberProgressList = new ArrayList<>();
    }

    public List<MemberProgress> getMemberProgressList() {
        return memberProgressList;
    }

    public void setMemberProgressList(List<MemberProgress> memberProgressList) {
        this.memberProgressList = memberProgressList;
    }

    public MemberProgress getProgressByDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parseDate = sdf.parse(date);
            for (MemberProgress progress : memberProgressList) {
                if (sdf.format(progress.getProgressDate()).equals(sdf.format(parseDate))) {
                    return progress;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMemberProgress(MemberProgress progress) {
        if (progress != null) {
            memberProgressList.add(progress);
        }
    }

    public void removeProgressByDate(String date) {
        MemberProgress progress = getProgressByDate(date);
        if (progress != null) {
            memberProgressList.remove(progress);
        }
    }

    public void updateProgress(String date, String newActivityDescription, int newTimeTraining) {
        MemberProgress progress = getProgressByDate(date);
        if (progress != null) {
            progress.setActivityDescription(newActivityDescription);
            progress.setTimeTraining(newTimeTraining);
        }
    }

    public List<MemberProgress> searchProgressByDate(String date) {
        List<MemberProgress> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parseDate = sdf.parse(date);
            String formattedDate = sdf.format(parseDate);
            for (MemberProgress progress : memberProgressList) {
                if (sdf.format(progress.getProgressDate()).equals(formattedDate)) {
                    result.add(progress);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MemberProgress> searchProgressByActivity(String activityDescription) {
        List<MemberProgress> result = new ArrayList<>();
        for (MemberProgress progress : memberProgressList) {
            if (progress.getActivityDescription().contains(activityDescription)) {
                result.add(progress);
            }
        }
        return result;
    }

    public List<MemberProgress> searchProgressByTimeTraining(int timeTraining) {
        List<MemberProgress> result = new ArrayList<>();
        for (MemberProgress progress : memberProgressList) {
            if (progress.getTimeTraining() == timeTraining) {
                result.add(progress);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        if (memberProgressList.isEmpty()) {
            return "No member progress records available.";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder str = new StringBuilder(String.format("%40s|%25s|%10s|\n", "Activity Description", "Date", "Time Training"));
        for (MemberProgress progress : memberProgressList) {
            str.append(String.format("%40s|%25s|%10d|\n", progress.getActivityDescription(), sdf.format(progress.getProgressDate()), progress.getTimeTraining()));
        }
        return str.toString();
    }
}
