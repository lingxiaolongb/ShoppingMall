package com.itlong.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 6323436254656380977L;

    private String prodId;
    private int num;
    private  BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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


    @Override
    public String toString() {
        return "ShopInfo{" +
                "prodId='" + prodId + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
