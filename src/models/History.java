package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    private String contentInDay;
    private Date date;
    private int timeTraining; 

    public History(String contentInDay, Date date, int timeTraining) {
        this.contentInDay = contentInDay;
        this.date = date;
        this.timeTraining = timeTraining;
    }

    public String getContentInDay() {
        return contentInDay;
    }
    public void setContentInDay(String contentInDay) {
        this.contentInDay = contentInDay;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    public int getTimeTraining() {
        return timeTraining;
    }
    public void setTimeTraining(int timeTraining) {
        this.timeTraining = timeTraining;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s - %s - %d", sdf.format(date), contentInDay, timeTraining);
    }
    
}
