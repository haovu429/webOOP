package com.entities;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "a3hshop")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "image", nullable = false)
    private String image;


    @Column(name = "price")
    private Double price;


    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "cateId")
    private CategoryEntity cateId;


    @Column(name = "sale_ID")
    private Integer saleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCateId() {
        return cateId;
    }

    public void setCateId(CategoryEntity cateId) {
        this.cateId = cateId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }
}