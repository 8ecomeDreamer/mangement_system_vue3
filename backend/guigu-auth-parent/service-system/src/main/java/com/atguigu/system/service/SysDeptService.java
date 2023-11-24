package com.atguigu.system.service;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.vo.SysDeptMergeVo;
import com.atguigu.model.vo.SysDeptQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_dept(组织机构)】的数据库操作Service
* @createDate 2023-11-24 21:42:14
*/
public interface SysDeptService extends IService<SysDept> {
    int insertDeptInfo(SysDeptMergeVo sysDeptMergeVo);

    List<SysDept> selectDeptInfo(SysDeptQueryVo sysDeptQueryVo);

    int deleteDeptInfo(List<String> deleteIds);

    int updateDeptInfo(SysDeptMergeVo sysDeptMergeVo);
}
