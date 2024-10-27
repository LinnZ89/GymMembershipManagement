package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.History;

public class HistoryController {
    private List<History> histories = new ArrayList<>();

    public HistoryController(){
        histories = new ArrayList<>();
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    public History getHistoryByDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date parseDate = sdf.parse(date);
            String formattedDate = sdf.format(parseDate);
            for(History history : histories){
                if(sdf.format(history.getDate()).contains(formattedDate)){
                    return history;
                }
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public void addHistory (History history){
        if(history != null){
            histories.add(history);
        }
    }

    public void removeHistoryByDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date parseDate = sdf.parse(date);
            String formattedDate = sdf.format(parseDate);
            History history = getHistoryByDate(formattedDate);
            if(history != null){
                histories.remove(history);
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    public void updateHistory(String newDate, String newContentInDay, int newTimeTraining){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            Date parseDate = sdf.parse(newDate);
            String formattedDate = sdf.format(parseDate);
            History history = getHistoryByDate(formattedDate);
            if(history != null){
                history.setContentInDay(newContentInDay);
                history.setTimeTraining(newTimeTraining);
            }

        }catch(ParseException e){
            e.printStackTrace();
        }
    }

    public List<History> searchHistoryByDate(String date){
        List<History> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
        try{
            Date parseDate = sdf.parse(date);
            String formattedDate = sdf.format(parseDate);
            History history = getHistoryByDate(formattedDate);
            for(History his : histories){
                if(sdf.format(his.getDate()).contains(formattedDate)){
                    result.add(history);
                }
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<History> searchHistoryByContent(String content){
        List<History> result = new ArrayList<>();
        for(History history : histories){
            if(history.getContentInDay().contains(content)){
                result.add(history);
            }
        }
        return result;
    }

    public List<History> searchHistoryByTimeTraining(int timeTraining){
        List<History> result = new ArrayList<>();
        for(History history : histories){
            if(history.getTimeTraining() == timeTraining){
                result.add(history);
            }
        }
        return result;
    }

    public String toString(){
        if(histories.isEmpty()){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String str = String.format("%40s|%25s|%10s|\n", "Content In Day", "Date", "Time Training");
        for(History history : histories){
            str += String.format("%40s|%25s|%10d|\n", history.getContentInDay(), sdf.format(history.getDate()), history.getTimeTraining());
        }
        return str;
    }
}
