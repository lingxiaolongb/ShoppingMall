package com.itlong.dto;




import com.itlong.bean.ProductInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


public class OrderItem  {

    private String orderId;
    private Map<ProductInfo,Integer> productInfos;
    private Byte expressFee;
    private String ugdId;
    private Byte orderStatus;
    private Date purchaseTime;
    private BigDecimal totalPrice;

    public OrderItem(){}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Map<ProductInfo, Integer> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(Map<ProductInfo, Integer> productInfos) {
        this.productInfos = productInfos;
    }



    public String getUgdId() {
        return ugdId;
    }

    public void setUgdId(String ugdId) {
        this.ugdId = ugdId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId='" + orderId + '\'' +
                ", productInfos=" + productInfos +
                ", express_fee='" + expressFee + '\'' +
                ", ugdId='" + ugdId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", purchaseTime=" + purchaseTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
