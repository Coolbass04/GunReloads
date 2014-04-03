package jant.gunreloads.app.sql.model;

import java.util.Date;

/**
 * Created by Adam on 4/1/2014.
 */
public class Ammo extends PersistanceObject {
    private long bulletId;
    private long powderId;
    private long primerId;
    private long resultsId;
    private float caseLength;
    private float cartridgeLength;
    private Date dateManufactured;
    private int quantityMade;

    private String AMMO_LOG = "Ammo";

    public Ammo() {}

    public Ammo(long bulletId, long powderId, long primerId, long resultsId, float caseLength, float cartridgeLength, Date dateManufactured, int quantityMade) {
        this.bulletId = bulletId;
        this.powderId = powderId;
        this.primerId = primerId;
        this.resultsId = resultsId;
        this.caseLength = caseLength;
        this.cartridgeLength = cartridgeLength;
        this.dateManufactured = dateManufactured;
        this.quantityMade = quantityMade;
    }

    public Ammo(long id, long bulletId, long powderId, long primerId, long resultsId, float caseLength, float cartridgeLength, Date dateManufactured, int quantityMade) {
        this.bulletId = bulletId;
        this.powderId = powderId;
        this.primerId = primerId;
        this.resultsId = resultsId;
        this.caseLength = caseLength;
        this.cartridgeLength = cartridgeLength;
        this.dateManufactured = dateManufactured;
        this.quantityMade = quantityMade;
    }

    public long getBulletId() {
        return bulletId;
    }

    public void setBulletId(long bulletId) {
        this.bulletId = bulletId;
    }

    public long getPowderId() {
        return powderId;
    }

    public void setPowderId(long powderId) {
        this.powderId = powderId;
    }

    public long getPrimerId() {
        return primerId;
    }

    public void setPrimerId(long primerId) {
        this.primerId = primerId;
    }

    public long getResultsId() {
        return resultsId;
    }

    public void setResultsId(long resultsId) {
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
