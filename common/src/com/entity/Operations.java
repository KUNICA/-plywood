package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "operations")
public class Operations {
    private Long id;
    private OperationType typeOper;
    private Date dateOper;
    private String userName;

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
    @Column(name = "type_oper")
    @Enumerated(EnumType.STRING)
    public OperationType getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(OperationType typeOper) {
        this.typeOper = typeOper;
    }

    @Basic
    @Column(name = "date_oper", nullable = false)
    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operations that = (Operations) o;

        if (id != that.id) return false;
        if (typeOper != that.typeOper) return false;
        if (dateOper != null ? !dateOper.equals(that.dateOper) : that.dateOper != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (dateOper != null ? dateOper.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
