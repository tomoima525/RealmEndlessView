package com.tomoima.realmendlessview.models;

import io.realm.RealmObject;

/**
 * Created by tomoaki on 2015/06/01.
 */
public class SimpleData extends RealmObject {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
