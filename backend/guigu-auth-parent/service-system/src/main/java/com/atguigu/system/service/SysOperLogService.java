package com.atguigu.system.service;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service
* @createDate 2023-11-25 16:52:41
*/
public interface SysOperLogService extends IService<SysOperLog> {

    List<SysOperLog> selectOperLog(SysOperLogQueryVo sysOperLogQueryVo);


}
