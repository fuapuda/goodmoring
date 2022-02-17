package com.at410.manageservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "消息", description = "消息查询对象封装")
@Data
public class MessageQuery implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "状态")
    private Integer status;
}