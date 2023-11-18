package com.atguigu.system.mapper;

import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper  extends BaseMapper<SysUser> {
    List<SysUser> selectUserInfo(@Param("entity") SysUserQueryVo sysUserQueryVo);
}
