package com.atguigu.system.mapper;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.vo.SysDeptMergeVo;
import com.atguigu.model.vo.SysDeptQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_dept(组织机构)】的数据库操作Mapper
* @createDate 2023-11-24 21:42:14
* @Entity generator.domain.SysDept
*/
public interface SysDeptMapper extends BaseMapper<SysDept> {
    List<SysDept> selectDeptInfo(@Param("sysDeptQueryVo") SysDeptQueryVo sysDeptQueryVo);

    int deleteDeptInfo(@Param("deleteIds") List<String> deleteIds);

    int insertDeptInfo(@Param("sysDeptVo") SysDeptMergeVo sysDeptMergeVo);

    int updateDeptInfo(@Param("sysDeptVo") SysDeptMergeVo sysDeptMergeVo);
}




