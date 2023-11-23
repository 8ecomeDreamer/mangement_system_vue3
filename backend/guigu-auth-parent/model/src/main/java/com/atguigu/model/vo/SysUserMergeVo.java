package com.atguigu.model.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 添加和更新的vo类
 */

@Data
public class SysUserMergeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String name;

    private String phone;

    private String description;

    private String deptId;

    private String postId;

}
