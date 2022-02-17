package com.at410.manageservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wtw
 * @since 2021-12-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ConsumerBiding对象", description="")
public class ConsumerBiding implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    @ApiModelProperty(value = "投标人编号")
    private Integer tbId;

    @ApiModelProperty(value = "招标信息编号")
    private String mbId;

    @ApiModelProperty(value = "投标企业名称")
    @TableField("companyName")
    private String companyName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "投标文件")
    private String file;

    @ApiModelProperty(value = "创建日期")
    private Date gmtCreate;

    private String status;

    private String title;


}
