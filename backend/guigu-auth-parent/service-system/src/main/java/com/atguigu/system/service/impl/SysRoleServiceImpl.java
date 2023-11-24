package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleMergeVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Jim
* @description 针对表【sys_role(角色)】的数据库操作Service实现
* @createDate 2023-11-24 21:34:31
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    SysRoleMapper sysRoleMapper;

    /**
     *  查询所有角色信息
     * @param sysRoleQueryVo 用户角色信息
     * @return List
     */
    @Override
    public List<SysRole> selectRoleInfo(SysRoleQueryVo sysRoleQueryVo) {
        return sysRoleMapper.selectRoleInfo(sysRoleQueryVo);
    }

    /**
     * 删除角色信息
     * @param deleteIds
     * @return int
     */
    @Override
    public int deleteRoleInfo(List<String> deleteIds) {
        return sysRoleMapper.deleteRoleInfo(deleteIds);
    }

    /**
     * 添加角色信息
     * @param sysRoleMergeVo 用户角色信息
     * @return List
     */
    @Override
    public int insertRoleInfo(SysRoleMergeVo sysRoleMergeVo) {
        return sysRoleMapper.insertRoleInfo(sysRoleMergeVo);
    }

    /**
     * 更新角色信息
     * @param sysRoleMergeVo
     * @return int
     */
    @Override
    public int updateRoleInfo(SysRoleMergeVo sysRoleMergeVo) {
        return sysRoleMapper.updateRoleInfo(sysRoleMergeVo);
    }
}




