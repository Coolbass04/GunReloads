package jant.gunreloads.app.sql.model;

import java.util.Date;

/**
 * Created by Adam on 4/1/2014.
 */
public class Ammo extends PersistanceObject {
    private int bulletId;
    private int powderId;
    private int primerId;
    private int resultsId;
    private float caseLength;
    private float cartridgeLength;
    private Date dateManufactured;
    private int quantityMade;

    private String AMMO_LOG = "Ammo";

    public Ammo() {}

    public Ammo(int bulletId, int powderId, int primerId, int resultsId, float caseLength, float cartridgeLength, Date dateManufactured, int quantityMade) {
        this.bulletId = bulletId;
        this.powderId = powderId;
        this.primerId = primerId;
        this.resultsId = resultsId;
        this.caseLength = caseLength;
        this.cartridgeLength = cartridgeLength;
        this.dateManufactured = dateManufactured;
        this.quantityMade = quantityMade;
    }

    public Ammo(int id, int bulletId, int powderId, int primerId, int resultsId, float caseLength, float cartridgeLength, Date dateManufactured, int quantityMade) {
        this.bulletId = bulletId;
        this.powderId = powderId;
        this.primerId = primerId;
        this.resultsId = resultsId;
        this.caseLength = caseLength;
        this.cartridgeLength = cartridgeLength;
        this.dateManufactured = dateManufactured;
        this.quantityMade = quantityMade;
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

    public int getBulletId() {
        return bulletId;
    }

    public void setBulletId(int bulletId) {
        this.bulletId = bulletId;
    }

    public int getPowderId() {
        return powderId;
    }

    public void setPowderId(int powderId) {
        this.powderId = powderId;
    }

    public int getPrimerId() {
        return primerId;
    }

    public void setPrimerId(int primerId) {
        this.primerId = primerId;
    }

    public int getResultsId() {
        return resultsId;
    }

    public void setResultsId(int resultsId) {
        this.resultsId = resultsId;
    }

    public float getCaseLength() {
        return caseLength;
    }

    public void setCaseLength(float caseLength) {
        this.caseLength = caseLength;
    }

    public float getCartridgeLength() {
        return cartridgeLength;
    }

    public void setCartridgeLength(float cartridgeLength) {
        this.cartridgeLength = cartridgeLength;
    }

    public Date getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(Date dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public int getQuantityMade() {
        return quantityMade;
    }

    public void setQuantityMade(int quantityMade) {
        this.quantityMade = quantityMade;
    }
}
