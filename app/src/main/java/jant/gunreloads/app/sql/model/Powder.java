package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Powder extends PersistanceObject {
    private int manufacturerId;
    private String type;

    public Powder() {}

    public Powder(int manufacturerId, String type) {
        this.manufacturerId = manufacturerId;
        this.type = type;
    }

    public Powder(int id, int manufacturerId, String type) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
