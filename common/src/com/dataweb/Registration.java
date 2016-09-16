package com.dataweb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.validation.Phone;
import com.validation.UserName;
import com.validation.ZipCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by user on 15.09.2016.
 */
public class Registration implements Serializable {
    @NotEmpty
    @Size(min=2, max=30)
    private String fullName;

    @NotEmpty
    @UserName
    @Size(min=2, max=30)
    private String lastName;

    @JsonProperty("companyName")
    private String companyName;

    @NotEmpty
    @Size(min=1, max=100)
    private String address;

    private String town;

    @NotEmpty
    @ZipCode
    private String zip;

    @NotEmpty
    @Email
    private String email;

    @Phone
    private String phone;

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getTown() {
        return town;
    }

    public String getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
