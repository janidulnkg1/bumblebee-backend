package com.example.bumblebeebackend.model;


import javax.persistence.*;

@Entity
public class Stock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_entity_seq")
    @SequenceGenerator(name = "my_entity_seq", sequenceName = "MY_ENTITY_SEQ", initialValue = 30000, allocationSize = 1)
    private Long productID;

    private String itemType;

    private String itemBrand;

    private String itemName;

    private String itemSize;

    private Integer itemPrice;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    private Integer itemQuantity;
}
