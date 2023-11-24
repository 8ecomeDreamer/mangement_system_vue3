package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultCodeEnum;
import com.atguigu.model.system.SysDept;
import com.atguigu.model.vo.SysDeptMergeVo;
import com.atguigu.model.vo.SysDeptQueryVo;
import com.atguigu.system.service.SysDeptService;
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
@RequestMapping("/admin/system/dept")
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;


    @ApiOperation("查询角色信息")
    @PostMapping("selectDept")
    public Result selectDept(@RequestBody SysDeptQueryVo sysDeptQueryVo){
        // 设置分页
        PageHelper.startPage(sysDeptQueryVo.getPage(), sysDeptQueryVo.getLimit());
        List<SysDept> list = sysDeptService.selectDeptInfo(sysDeptQueryVo);
        // 查询用户角色列表（需要分页的查询）
        PageInfo<SysDept> pageInfo = new PageInfo<SysDept>(list);
        return Result.ok(pageInfo);
    }

    @ApiOperation("添加角色信息")
    @PostMapping("insertDept")
    public Result insertDept(@RequestBody SysDeptMergeVo sysDeptMergeVo) {
        int flag = sysDeptService.insertDeptInfo(sysDeptMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

    @ApiOperation("更新角色信息")
    @PostMapping("updateDept")
    public Result updateDept(@RequestBody SysDeptMergeVo sysDeptMergeVo) {
        int flag = sysDeptService.updateDeptInfo(sysDeptMergeVo);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }


    @ApiOperation("删除角色信息")
    @PostMapping("deleteDept")
    public Result deleteDept(@RequestBody List<String> deleteIds) {
        if (deleteIds.isEmpty()){
            return Result.build(null, ResultCodeEnum.SELECT_DATA_ERROR);
        }
        int flag = sysDeptService.deleteDeptInfo(deleteIds);
        if(flag > 0){
            return Result.ok();
        }
        return Result.fail();
    }

}
