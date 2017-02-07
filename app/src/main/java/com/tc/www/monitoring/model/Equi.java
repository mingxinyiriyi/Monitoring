package com.tc.www.monitoring.model;

/**
 * Created by ming on 2016-10-28.
 */

public class Equi {
    private int id;
    private String name;

    public Equi(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
