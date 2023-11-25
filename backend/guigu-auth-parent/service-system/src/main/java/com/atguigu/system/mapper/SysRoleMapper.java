package com.atguigu.system.mapper;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleMergeVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_role(角色)】的数据库操作Mapper
* @createDate 2023-11-24 21:34:31
* @Entity generator.domain.SysRole
*/

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRoleInfo(@Param("sysRoleQueryVo") SysRoleQueryVo sysRoleQueryVo);

    int deleteRoleInfo(@Param("deleteIds") List<String> deleteIds);

    int insertRoleInfo(@Param("sysRoleMergeVo") SysRoleMergeVo sysRoleMergeVo);

    int updateRoleInfo(@Param("sysRoleMergeVo") SysRoleMergeVo sysRoleMergeVo);
}




