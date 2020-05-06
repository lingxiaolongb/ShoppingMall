package com.itlong.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopInfo implements Serializable {

    private String prodId;
    private int num;
    private BigDecimal price;


    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShopInfo{" +
                "prodId='" + prodId + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }



}
