package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysDept;
import com.atguigu.model.system.SysDept;
import com.atguigu.model.vo.SysDeptMergeVo;
import com.atguigu.model.vo.SysDeptQueryVo;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.mapper.SysDeptMapper;
import com.atguigu.system.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Jim
* @description 针对表【sys_dept(组织机构)】的数据库操作Service实现
* @createDate 2023-11-24 21:42:14
*/
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Resource
    SysDeptMapper sysDeptMapper;

    /**
     *  查询所有部门信息
     * @param sysDeptQueryVo 用户部门信息
     * @return List
     */
    @Override
    public List<SysDept> selectDeptInfo(SysDeptQueryVo sysDeptQueryVo) {
        return sysDeptMapper.selectDeptInfo(sysDeptQueryVo);
    }

    /**
     * 删除部门信息
     * @param deleteIds
     * @return int
     */
    @Override
    public int deleteDeptInfo(List<String> deleteIds) {
        return sysDeptMapper.deleteDeptInfo(deleteIds);
    }

    /**
     * 添加部门信息
     * @param sysDeptMergeVo 用户部门信息
     * @return List
     */
    @Override
    public int insertDeptInfo(SysDeptMergeVo sysDeptMergeVo) {
        return sysDeptMapper.insertDeptInfo(sysDeptMergeVo);
    }

    /**
     * 更新部门信息
     * @param sysDeptMergeVo
     * @return int
     */
    @Override
    public int updateDeptInfo(SysDeptMergeVo sysDeptMergeVo) {
        return sysDeptMapper.updateDeptInfo(sysDeptMergeVo);
    }
}




