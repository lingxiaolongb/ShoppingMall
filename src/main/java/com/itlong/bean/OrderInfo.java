package com.itlong.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.io.Serializable;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo  implements Serializable {


    private static final long serialVersionUID = 5570930467825992568L;

    private String orderId;

    private String ugdId;

    private String prodId;

    private Integer quantity;

    private Byte expressFee;

    private Byte orderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "Alisa/Shanghai")
    private Date purchaseTime;

    private ProductInfo productInfo;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUgdId() {
        return ugdId;
    }

    public void setUgdId(String ugdId) {
        this.ugdId = ugdId;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Byte getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(Byte expressFee) {
        this.expressFee = expressFee;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", ugdId='" + ugdId + '\'' +
                ", prodId='" + prodId + '\'' +
                ", quantity=" + quantity +
                ", expressFee=" + expressFee +
                ", orderStatus=" + orderStatus +
                ", purchaseTime=" + purchaseTime +
                ", productInfo=" + productInfo +
                '}';
    }
}