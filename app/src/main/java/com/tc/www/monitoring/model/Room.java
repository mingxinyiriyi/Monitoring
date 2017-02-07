package com.tc.www.monitoring.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/8.
 */

public class Room implements Serializable{
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
