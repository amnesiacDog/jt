package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CouponMapper;
import com.jt.mapper.CouponUserMapper;
import com.jt.pojo.Coupon;
import com.jt.pojo.CouponUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DubboCouponServiceImpl implements DubboCouponService {
   @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUserMapper couponUserMapper;

    /*
     *随机查询红包
     */
    @Override
    public Coupon selectCouponByRandom() {
        List<Coupon> couponList = couponMapper.selectList(new QueryWrapper<Coupon>().eq("status", 1));
        int key = (int) (Math.random() * couponList.size());
        return couponList.get(key);
    }

    /*
     *储存用户红包
     */
    @Override
    @Transactional
    public void saveCoupon(CouponUser couponUser) {
        couponUserMapper.insert(couponUser);
    }

    /*
     *用户随机抽取红包并储存
     * 1.查询用户是否已经领了红包,有了直接抛异常返回
     * 2.查询可用红包,并且随机抽取一个红包返回
     * 3.储存用户红包表
     */
    @Override
    @Transactional
    public Coupon selectAndSaveCoupon(Long userId) {
        Integer count = couponUserMapper.selectCount(new QueryWrapper<CouponUser>().eq("user_id", userId));
        if (count>0){
            throw new RuntimeException("已经领取了红包");
        }
        List<Coupon> couponList = couponMapper.selectList(new QueryWrapper<Coupon>().eq("status", 1));
        int key = (int) Math.random() * couponList.size();
        Coupon coupon = couponList.get(key);
        CouponUser couponUser = new CouponUser();
        couponUser.setUserId(userId)
                .setCouponId(coupon.getId())
                .setStartTime(new Date())
                .setEndTime(coupon.getEndTime())
                .setCreated(new Date())
                .setUpdated(new Date());
        couponUserMapper.insert(couponUser);
        return coupon;
    }


}
