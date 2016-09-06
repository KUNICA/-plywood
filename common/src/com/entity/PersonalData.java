package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "personal_data", schema = "plywood_work")
public class PersonalData {
    private Long id;
    private String email;
    private String lastName;
    private String midleName;
    private String firstName;
    private String userName;
    private Operations operationIn;
    private Operations operationOut;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "midle_name", nullable = true, length = 50)
    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_in", updatable = true,insertable = true)
    public Operations getOperationIn() {
        return operationIn;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="oper_out", updatable = true,insertable = true)
    public Operations getOperationOut() {
        return operationOut;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperationIn(Operations operationIn) {
        this.operationIn = operationIn;
    }

    public void setOperationOut(Operations operationOut) {
        this.operationOut = operationOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalData that = (PersonalData) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (midleName != null ? !midleName.equals(that.midleName) : that.midleName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (midleName != null ? midleName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        return result;
    }
}
