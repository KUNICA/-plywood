package com.entity;

import javax.persistence.*;

/**
 * Created by user on 20.08.2016.
 */
@Entity
@Table(name = "images", schema = "plywood_work")
public class Images {
    private Long id;
    private String img;
    private String description;
    private Long productId;
    private Boolean main;
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
    @Column(name = "img", nullable = false, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    @Basic
    @Column(name = "main")
    public Boolean getMain() {
        return main;
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

    public void setMain(Boolean main) {
        this.main = main;
    }

    public void setOperationIn(Operations operationIn) {
        this.operationIn = operationIn;
    }

    public void setOperationOut(Operations operationOut) {
        this.operationOut = operationOut;
    }


}
