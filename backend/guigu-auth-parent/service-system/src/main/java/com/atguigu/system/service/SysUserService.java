package com.atguigu.system.service;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    Result login(LoginVo loginVo);

    List<SysUser> selectUserInfo(SysUserQueryVo sysUserQueryVo);
}
