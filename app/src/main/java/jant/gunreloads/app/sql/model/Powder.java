package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Powder extends PersistanceObject {
    private long manufacturerId;
    private String type;

    public Powder() {}

    public Powder(int manufacturerId, String type) {
        this.manufacturerId = manufacturerId;
        this.type = type;
    }

    public Powder(long id, long manufacturerId, String type) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.type = type;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
