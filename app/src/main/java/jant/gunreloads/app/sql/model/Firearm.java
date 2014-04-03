package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Firearm extends PersistanceObject {
    private int manufacturerId;
    private String model;
    private String extraInfo;

    public Firearm() {}

    public Firearm(int manufacturerId, String model, String extraInfo) {
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.extraInfo = extraInfo;
    }

    public Firearm(int id, int manufacturerId, String powderType) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.model = model;
        this.extraInfo = extraInfo;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
