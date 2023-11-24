package com.atguigu.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenuQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer limit;

    @ApiModelProperty(value = "id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;
    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;
    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;
    @ApiModelProperty(value = "所属上级")
    @TableField("parent_id")
    private String parentId;


}
