package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Coupon;
import com.jt.pojo.CouponUser;
import com.jt.service.DubboCouponService;
import com.jt.util.UserThreadLocalUtil;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/coupon")
public class CouponController {
    @Reference(check = false)
    private DubboCouponService couponService;


    /*
     *用户抽取红包并储存
     */
//    @RequestMapping("/select")
//    @ResponseBody
//    public SysResult selectCoupon(){
//        Long userId = UserThreadLocalUtil.get().getId();
//        Coupon coupon = couponService.selectCouponByRandom();
//        if (coupon==null){
//            return SysResult.fail();
//        }
//        CouponUser couponUser = new CouponUser();
//        couponUser.setUserId(userId)
//                .setCouponId(coupon.getId())
//                .setStartTime(new Date())
//                .setEndTime(coupon.getEndTime())
//                .setCreated(new Date())
//                .setUpdated(new Date());
//        couponService.saveCoupon(couponUser);
//        return SysResult.success(coupon);
//    }

//这是个后期程序，要用户登录才能用
    @RequestMapping("/index2")
    public String toIndex2( Model model){
        Long userId = UserThreadLocalUtil.get().getId();
        System.out.println(userId);
        Coupon coupon = couponService.selectAndSaveCoupon(userId);
        if (coupon==null){
//            throw new RuntimeException("没有可用红包");
            return "problem";
        }else {
            model.addAttribute(coupon);
            return "index1";
        }
    }

    @RequestMapping("/index1")
    public String toIndex( Model model){
        Coupon coupon = couponService.selectCouponByRandom();
        model.addAttribute(coupon);
        return "index1";
    }
}
