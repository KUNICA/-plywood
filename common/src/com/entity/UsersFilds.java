package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "users_filds", schema = "plywood_work")
public class UsersFilds {
    private int id;
    private String username;
    private String compare;
    private Type type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 225)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "compare", nullable = true, length = 225)
    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersFilds that = (UsersFilds) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (compare != null ? !compare.equals(that.compare) : that.compare != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (compare != null ? compare.hashCode() : 0);
        return result;
    }
}
