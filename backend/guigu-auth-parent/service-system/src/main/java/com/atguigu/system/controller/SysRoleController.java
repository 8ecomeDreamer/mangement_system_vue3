package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleMergeVo;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @ApiOperation("查询角色信息")
    @PostMapping("selectRole")
    public Result selectRole(@RequestBody SysRoleQueryVo sysRoleQueryVo){
        // 设置分页
        PageHelper.startPage(sysRoleQueryVo.getPage(), sysRoleQueryVo.getLimit());
        List<SysRole> list = sysRoleService.selectRoleInfo(sysRoleQueryVo);
        // 查询用户角色列表（需要分页的查询）
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return Result.ok(pageInfo);
    }

    @ApiOperation("添加角色信息")
    @PostMapping("insertRole")
    public Result insertRole(@RequestBody SysRoleMergeVo sysRoleMergeVo) {
        int flag = sysRoleService.insertRoleInfo(sysRoleMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("更新角色信息")
    @PostMapping("updateRole")
    public Result updateRole(@RequestBody SysRoleMergeVo sysRoleMergeVo) {
        int flag = sysRoleService.updateRoleInfo(sysRoleMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }


    @ApiOperation("删除角色信息")
    @PostMapping("deleteRole")
    public Result deleteRole(@RequestBody List<String> deleteIds) {
        if (deleteIds.isEmpty()){
            return Result.build(null, ResultCodeEnum.SELECT_DATA_ERROR);
        }
        int flag = sysRoleService.deleteRoleInfo(deleteIds);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

}
