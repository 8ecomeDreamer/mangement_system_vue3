package com.atguigu.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenuQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer limit;

    private String type;
    private String path;
    private String name;
    private String component;
    private String parentId;


}
