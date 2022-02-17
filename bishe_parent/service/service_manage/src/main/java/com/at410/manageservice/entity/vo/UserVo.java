package com.at410.manageservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVo {
    private String username;

    private String password;
}
