package com.at410.manageservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "投标审查", description = "投标")
@Data
public class BidingQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "招标信息编号")
    private String mbId;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

    @ApiModelProperty(value = "投标公司", example = "腾讯公司")
    private String companyName;
    @ApiModelProperty(value = "状态")
    private String status;

}