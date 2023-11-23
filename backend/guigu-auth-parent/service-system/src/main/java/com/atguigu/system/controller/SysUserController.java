package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysUser;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.model.vo.SysUserMergeVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/system/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo ){
        return sysUserService.login(loginVo);
    }


    @ApiOperation("用户登录")
    @PostMapping("login2")
    public Result login2(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }

    @ApiOperation("用户信息")
    @GetMapping("info")
    public Result info(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("introduction", "I am a super administrator");
        map.put("avatar", "https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif");
        map.put("name", "super admin");
        return Result.ok(map);
    }


    @ApiOperation("查询所有用户信息")
    @PostMapping("selectUserInfo")
    public Result selectUserInfo(@RequestBody SysUserQueryVo sysUserQueryVo){
        List<SysUser> list = sysUserService.selectUserInfo(sysUserQueryVo);
        return Result.ok(list);
    }

    @ApiOperation("添加所有用户信息")
    @PostMapping("insertUserInfo")
    public Result insertUserInfo(@RequestBody SysUserMergeVo sysUserMergeVo) {
        int flag = sysUserService.insertUserInfo(sysUserMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("更新所有用户信息")
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(@RequestBody SysUserMergeVo sysUserMergeVo) {
        int flag = sysUserService.updateUserInfo(sysUserMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }


    @ApiOperation("删除所有用户信息")
    @PostMapping("deleteUserInfo")
    public Result deleteUserInfo(@RequestBody List<String> deleteIds) {
        System.out.println(deleteIds);
        if (deleteIds.isEmpty()){
            return Result.build(null, ResultCodeEnum.SELECT_DATA_ERROR);
        }
        int flag = sysUserService.deleteUserInfo(deleteIds);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

}
