package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.system.config.SelfException;
import com.atguigu.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @return 查询所有记录
     */
    @ApiOperation("查询所有用户权限记录")
    @GetMapping("findAll")
    public Result findAllRole(){
////        模拟自定义异常
//        try{
////        模拟ArithmeticException异常
//            int i = 9 / 0;
//        }catch (Exception e){
//            throw new SelfException("20001","这里是自定义异常");
//        }
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }


    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }

    }

    @ApiOperation("添加或者修改角色")
    @PostMapping("updateRole")
    public Result updateRole(@RequestBody SysRole sysRole){
//       根据请求体是否有id 有则修改 否则为添加
        String sysRoleId = sysRole.getId();
        SysRole id = sysRoleService.getById(sysRoleId);
        if(id == null){
            return saveRole(sysRole);
        }
        else {
            return updateByIdRole(sysRole);
        }

    }

    @ApiOperation("添加角色")
    public Result saveRole(SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess){
            return Result.ok();
        } else {
            return Result.fail();
        }

    }

    @ApiOperation("修改角色")
    public Result updateByIdRole(SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        } else {
            return Result.fail();
        }

    }
}
