package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.atguigu.system.service.SysOperLogService;
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

@Api(tags = "日志管理接口")
@RestController
@RequestMapping("/admin/system/operLog")
public class SysOperLogController {

    @Resource
    private SysOperLogService sysOperLogService;


    @ApiOperation("查询日志信息")
    @PostMapping("selectOperLog")
    public Result selectOperLog(@RequestBody SysOperLogQueryVo sysOperLogQueryVo){
        // 设置分页
        PageHelper.startPage(sysOperLogQueryVo.getPage(), sysOperLogQueryVo.getLimit());
        List<SysOperLog> list = sysOperLogService.selectOperLog(sysOperLogQueryVo);
        // 查询用户角色列表（需要分页的查询）
        PageInfo<SysOperLog> pageInfo = new PageInfo<SysOperLog>(list);
        return Result.ok(pageInfo);
    }

}
