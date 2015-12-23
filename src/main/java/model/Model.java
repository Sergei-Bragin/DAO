package model;

import java.io.Serializable;

public class Model implements Serializable {
    private int id;

    public Model() {

    }


    public Model(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
