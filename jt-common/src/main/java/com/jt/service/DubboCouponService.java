package com.jt.service;

import com.jt.pojo.Coupon;
import com.jt.pojo.CouponUser;

public interface DubboCouponService {
    Coupon selectCouponByRandom();

    void saveCoupon(CouponUser couponUser);

    Coupon selectAndSaveCoupon(Long userId);
}
