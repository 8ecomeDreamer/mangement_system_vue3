//
//
package com.atguigu.model.vo;

import com.atguigu.model.system.SysDept;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色查询实体
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */

@Data
public class SysDeptQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer page;
	private Integer limit;

	@ApiModelProperty(value = "id")
	@TableField("id")
	private String id;

	@ApiModelProperty(value = "部门名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "上级部门id")
	@TableField("parent_id")
	private String parentId;

	@ApiModelProperty(value = "负责人")
	@TableField("leader")
	private String leader;

	@ApiModelProperty(value = "电话")
	@TableField("phone")
	private String phone;

}

