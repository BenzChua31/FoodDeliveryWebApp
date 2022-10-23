package model;

import java.util.Date;

public class Coupon {
    private Integer couponId;

    private String couponName;

    private Integer couponScope;

    private Integer couponMinMoney;

    private Date createdDate;

    private Double couponValue;

    private String couponDescription;

    private String couponImage;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public Integer getCouponScope() {
        return couponScope;
    }

    public void setCouponScope(Integer couponScope) {
        this.couponScope = couponScope;
    }

    public Integer getCouponMinMoney() {
        return couponMinMoney;
    }

    public void setCouponMinMoney(Integer couponMinMoney) {
        this.couponMinMoney = couponMinMoney;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription == null ? null : couponDescription.trim();
    }

    public String getCouponImage() {
        return couponImage;
    }

    public void setCouponImage(String couponImage) {
        this.couponImage = couponImage == null ? null : couponImage.trim();
    }
}