package com.entity;

import javax.persistence.*;

/**
 * Created by user on 16.09.2016.
 */
@Entity
@Table(name = "group_members")
public class GroupMembers {
    private Long id;
    private String username;
    private Group  group;

    public GroupMembers(String username, Group group) {
        this.username = username;
        this.group = group;
    }

    public GroupMembers() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "group_id", nullable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMembers that = (GroupMembers) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
