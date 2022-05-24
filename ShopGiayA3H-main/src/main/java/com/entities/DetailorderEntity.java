package com.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detailorder", schema = "a3hshop")
//@IdClass(DetailorderEntityPK.class)
public class DetailorderEntity implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "oid")
    private OrderEntity orderEntity;

    @Id
    @ManyToOne
    @JoinColumn(name = "pid")
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}