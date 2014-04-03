package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Firearm extends PersistanceObject {
    private long manufacturerId;
    private String model;
    private String extraInfo;

    public Firearm() {}

    public Firearm(long manufacturerId, String model, String extraInfo) {
        this.extraInfo = extraInfo;
        this.manufacturerId = manufacturerId;
        this.model = model;
    }

    public Firearm(long id, long manufacturerId, String model, String extraInfo) {
        this.id = id;
        this.extraInfo = extraInfo;
        this.manufacturerId = manufacturerId;
        this.model = model;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
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
