package jant.gunreloads.app.sql.model;

import java.util.Date;

/**
 * Created by Adam on 4/2/2014.
 */
public class Results extends PersistanceObject {
    private int firearmId;
    private Date dateShot;
    private String explanation;

    public Results(){}

    public Results(int firearmId, Date dateShot, String explanation) {
        this.firearmId = firearmId;
        this.dateShot = dateShot;
        this.explanation = explanation;
    }

    public Results(int id, int firearmId, Date dateShot, String explanation) {
        this.id = id;
        this.firearmId = firearmId;
        this.dateShot = dateShot;
        this.explanation = explanation;
    }

    public int getFirearmId() {
        return firearmId;
    }

    public void setFirearmId(int firearmId) {
        this.firearmId = firearmId;
    }

    public Date getDateShot() {
        return dateShot;
    }

    public void setDateShot(Date dateShot) {
        this.dateShot = dateShot;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
