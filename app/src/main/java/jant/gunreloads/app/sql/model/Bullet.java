package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 3/29/2014.
 */
public class Bullet {
    private int _id;
    private String created_at;
    private String manufacturer;
    private String style;
    private Enums.Caliber caliber;
    private String weight;

    public Bullet() {}

    public Bullet(String manufacturer, String style, Enums.Caliber caliber, String weight) {
        this.manufacturer = manufacturer;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public Bullet(int id, String manufacturer, String style, Enums.Caliber caliber, String weight) {
        this._id = id;
        this.manufacturer = manufacturer;
        this.style = style;
        this.caliber = caliber;
        this.weight = weight;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
