package com.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "video")
public class Video {

    private Long id;
    private String link;
    private String description;
    private Long productId;
    private Operations operationIn;
    private Operations operationOut;
    private Date data;
    private String section;
    private String headline;
    private String language;
    private String brand;


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
    @Column(name = "link", nullable = false, length = 255)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Basic
    @Column(name = "product_id", nullable = true)
    public Long getProductId() {
        return productId;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="operation_in", updatable = true,insertable = true)
    public Operations getOperationIn() {
        return operationIn;
    }
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="operation_out", updatable = true,insertable = true)
    public Operations getOperationOut() {
        return operationOut;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setOperationIn(Operations operationIn) {
        this.operationIn = operationIn;
    }

    public void setOperationOut(Operations operationOut) {
        this.operationOut = operationOut;
    }

    @Basic
    @Column(name = "data", nullable = true)
    public Date getData() {
        return data;
    }

    @Basic
    @Column(name = "section", nullable = true, length = 255)
    public String getSection() {
        return section;
    }

    @Basic
    @Column(name = "headline", nullable = true, length = 255)
    public String getHeadline() {
        return headline;
    }
    @Basic
    @Column(name = "language", nullable = true, length = 255)
    public String getLanguage() {
        return language;
    }
    @Basic
    @Column(name = "brand", nullable = true, length = 255)
    public String getBrand() {
        return brand;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
