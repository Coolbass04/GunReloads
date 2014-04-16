package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 3/29/2014.
 */
public class Bullet extends PersistanceObject {
    private long manufacturerId;
    private String style;
    private String caliber;
    private String weight;

    public Bullet() {}

    public Bullet(long manufacturerId, String style, String caliber, String weight) {
        this.manufacturerId = manufacturerId;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public Bullet(long id, long manufacturerId, String style, String caliber, String weight) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCaliber() {
        return caliber;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
