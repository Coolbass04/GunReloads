package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Primer extends PersistanceObject {
    private long manufacturerId;
    private String type;

    public Primer() {}

    public Primer(long manufacturerId, String type) {
        this.manufacturerId = manufacturerId;
        this.type = type;
    }

    public Primer(long id, long manufacturerId, String type) {
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
