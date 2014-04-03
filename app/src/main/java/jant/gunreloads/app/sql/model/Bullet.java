package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 3/29/2014.
 */
public class Bullet extends PersistanceObject {
    private String manufacturerId;
    private String style;
    private Enums.Caliber caliber;
    private String weight;

    public Bullet() {}

    public Bullet(String manufacturerId, String style, Enums.Caliber caliber, String weight) {
        this.manufacturerId = manufacturerId;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public Bullet(int id, String manufacturerId, String style, Enums.Caliber caliber, String weight) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Enums.Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Enums.Caliber caliber) {
        this.caliber = caliber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
