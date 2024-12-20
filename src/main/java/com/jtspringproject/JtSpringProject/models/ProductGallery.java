package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;

@Entity(name = "PRODUCT_GALLERY")
public class ProductGallery {
    @Id
    @Column(name = "my_row_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
