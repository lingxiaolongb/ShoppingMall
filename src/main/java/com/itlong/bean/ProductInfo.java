package com.itlong.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo  implements Serializable {
    private String prodId;

    private String prodName;

    private BigDecimal price;

    private String describe;

    private Date prodDate;

    private String prodPic;

    private Short integral;

    private String prodDetail;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public String getProdPic() {
        return prodPic;
    }

    public void setProdPic(String prodPic) {
        this.prodPic = prodPic == null ? null : prodPic.trim();
    }

    public Short getIntegral() {
        return integral;
    }

    public void setIntegral(Short integral) {
        this.integral = integral;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail == null ? null : prodDetail.trim();
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "prodId='" + prodId + '\'' +
                ", prodName='" + prodName + '\'' +
                ", price=" + price +
                ", describe='" + describe + '\'' +
                ", prodDate=" + prodDate +
                ", prodPic='" + prodPic + '\'' +
                ", integral=" + integral +
                ", prodDetail='" + prodDetail + '\'' +
                '}';
    }
}