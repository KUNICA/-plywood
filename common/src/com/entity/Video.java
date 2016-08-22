package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "video", schema = "plywood_work")
public class Video {
    private Long id;
    private String link;
    private String description;
    private Long productId;
    private Operations operationIn;
    private Operations operationOut;

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
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Basic
    @Column(name = "product_id", nullable = false)
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
}
