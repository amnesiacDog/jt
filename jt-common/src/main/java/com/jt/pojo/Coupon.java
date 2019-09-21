package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@TableName("tb_coupon")
@Data
@Accessors(chain = true)
public class Coupon extends BasePojo{
    @TableId
    private String id;
    private String name;
    private String cut;
    private String bound;
    private Integer status;
    private Date endTime;
    private Integer validTime;
}
