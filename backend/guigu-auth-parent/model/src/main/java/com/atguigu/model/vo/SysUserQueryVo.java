//
//
package com.atguigu.model.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户查询实体
 * </p>
 */
@Data
public class SysUserQueryVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	private String keyword;
//
//	private String createTimeBegin;
//	private String createTimeEnd;
//
//	private Long roleId;
//	private Long postId;
//	private Long deptId;
	private Integer page;

	private Integer limit;

	private String name;

	private String userName;

	private String deptId;

	private String postId;


}

