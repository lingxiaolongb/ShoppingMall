package com.itlong.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo  implements Serializable {
    private String orderId;

    private String ugdId;

    private String consignee;

    private String prodId;

    private String prodName;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Byte expressFee;

    private Byte orderStatus;


    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Alisa/Shanghai")
    private Date purchaseTime;

    private ProductInfo productInfo;

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUgdId() {
        return ugdId;
    }

    public void setUgdId(String ugdId) {
        this.ugdId = ugdId == null ? null : ugdId.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderId='" + orderId + '\'' +
                ", ugdId='" + ugdId + '\'' +
                ", consignee='" + consignee + '\'' +
                ", prodId='" + prodId + '\'' +
                ", prodName='" + prodName + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", expressFee=" + expressFee +
                ", orderStatus=" + orderStatus +
                ", purchaseTime=" + purchaseTime +
                ", productInfo=" + productInfo +
                '}';
    }
}