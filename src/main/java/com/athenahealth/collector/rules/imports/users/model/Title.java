package com.athenahealth.collector.rules.imports.users.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Title {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Id
    int id;
    String name;

}
