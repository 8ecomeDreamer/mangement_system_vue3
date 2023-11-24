package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.SysMenuMergeVo;
import com.atguigu.model.vo.SysMenuQueryVo;
import com.atguigu.system.service.SysMenuService;
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

@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/system/menu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;


    @ApiOperation("查询菜单信息")
    @PostMapping("selectMenu")
    public Result selectMenu(@RequestBody SysMenuQueryVo sysMenuQueryVo){
        // 设置分页
        PageHelper.startPage(sysMenuQueryVo.getPage(), sysMenuQueryVo.getLimit());
        List<SysMenu> list = sysMenuService.selectMenuInfo(sysMenuQueryVo);
        // 查询用户角色列表（需要分页的查询）
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
        return Result.ok(pageInfo);
    }

    @ApiOperation("添加菜单信息")
    @PostMapping("insertMenu")
    public Result insertMenu(@RequestBody SysMenuMergeVo sysMenuMergeVo) {
        int flag = sysMenuService.insertMenuInfo(sysMenuMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("更新菜单信息")
    @PostMapping("updateMenu")
    public Result updateMenu(@RequestBody SysMenuMergeVo sysMenuMergeVo) {
        int flag = sysMenuService.updateMenuInfo(sysMenuMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }


    @ApiOperation("删除菜单信息")
    @PostMapping("deleteMenu")
    public Result deleteMenu(@RequestBody List<String> deleteIds) {
        if (deleteIds.isEmpty()){
            return Result.build(null, ResultCodeEnum.SELECT_DATA_ERROR);
        }
        int flag = sysMenuService.deleteMenuInfo(deleteIds);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

}
