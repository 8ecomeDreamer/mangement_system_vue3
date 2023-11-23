package com.atguigu.model.vo;

import com.atguigu.model.system.SysMenu;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class SysMenuMergeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属上级")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "排序")
    @TableField("sort_value")
    private Integer sortValue;

}
