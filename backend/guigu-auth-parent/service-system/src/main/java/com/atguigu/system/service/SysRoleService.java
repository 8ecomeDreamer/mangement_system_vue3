package com.atguigu.system.service;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleMergeVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_role(角色)】的数据库操作Service
* @createDate 2023-11-24 21:34:31
*/
public interface SysRoleService extends IService<SysRole> {

    int insertRoleInfo(SysRoleMergeVo sysRoleMergeVo);

    List<SysRole> selectRoleInfo(SysRoleQueryVo sysRoleQueryVo);

    int deleteRoleInfo(List<String> deleteIds);

    int updateRoleInfo(SysRoleMergeVo sysRoleMergeVo);
}
