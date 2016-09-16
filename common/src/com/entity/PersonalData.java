package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "personal_data")
public class PersonalData {
    private Long id;
    private String email;
    private String lastName;
    private String midleName;
    private String firstName;
    private String userName;
    private String fullName;
    private String companyName;
    private String adress;
    private String town;
    private String zip;
    private String phone;
    private Operations operationIn;
    private Operations operationOut;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public void setUserName(String userName) {
        this.userName = userName;
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


    public void setOperationIn(Operations operationIn) {
        this.operationIn = operationIn;
    }

    public void setOperationOut(Operations operationOut) {
        this.operationOut = operationOut;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 45)
    public String getFullName() {
        return fullName;
    }

    @Basic
    @Column(name = "company_name", nullable = true, length = 45)
    public String getCompanyName() {
        return companyName;
    }

    @Basic
    @Column(name = "adress", nullable = false, length = 45)
    public String getAdress() {
        return adress;
    }

    @Basic
    @Column(name = "town", nullable = true, length = 45)
    public String getTown() {
        return town;
    }

    @Basic
    @Column(name = "zip", nullable = false, length = 45)
    public String getZip() {
        return zip;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
