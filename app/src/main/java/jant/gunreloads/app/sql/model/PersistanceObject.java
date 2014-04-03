package jant.gunreloads.app.sql.model;

/**
 * Created by Adam on 4/2/2014.
 */
public class PersistanceObject {
    protected long id;
    protected String created_at;

    public PersistanceObject(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
