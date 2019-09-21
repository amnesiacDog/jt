package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("tb_coupon_cat")
public class CouponUser extends BasePojo{
    @TableId
    private String couponId;
    @TableId
    private Long userId;
    private Date startTime;
    private Date endTime;
    private Integer status;
}
