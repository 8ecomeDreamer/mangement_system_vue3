package com.atguigu.system.service.impl;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.mapper.SysUserMapper;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public Result login(LoginVo loginVo) {

        Result<Object> result = new Result<>();

//        1.校验密码不能为空
        if (loginVo.getUsername() == null || loginVo.getPassword() == null) {
            return Result.build(null,ResultCodeEnum.ARGUMENT_ERROR);
        }
//        判断用户是否存在
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", loginVo.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if(sysUser == null){
            return Result.build(null,ResultCodeEnum.ACCOUNT_ERROR);
        }
//        2.密码MD5加密
        String md5Password = DigestUtils.md5DigestAsHex(loginVo.getPassword().getBytes());
//          判断用户是否相等
        String password = sysUser.getPassword();
        if (!md5Password.equals(password)){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
//        3.密码相等返回用户信息 看情况是否脱敏
        Result.build(sysUser,ResultCodeEnum.PASSWORD_ERROR);
        return result;
    }

}