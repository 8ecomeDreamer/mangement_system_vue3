package com.atguigu.common.pageHelper;

import lombok.Data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class PageBean<T> {
    private int pageNum; //当前页的页码
    private int pageSize; //每页的数量
    private int total; //数据的总数

}
