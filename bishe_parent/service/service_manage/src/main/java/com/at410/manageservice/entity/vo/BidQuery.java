package com.at410.manageservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "招标查询对象", description = "招标")
@Data
public class BidQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "招标名字")
    private String title;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

    @ApiModelProperty(value = "部门id")
    private String departmentId;
}