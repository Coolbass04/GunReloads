package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class Manufacturer extends PersistanceObject {
    private String name;

    public Manufacturer(){}

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
