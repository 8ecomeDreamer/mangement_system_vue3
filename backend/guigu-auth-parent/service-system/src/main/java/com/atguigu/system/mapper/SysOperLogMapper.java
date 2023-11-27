package com.atguigu.system.mapper;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Mapper
* @createDate 2023-11-25 16:52:41
* @Entity generator.domain.SysOperLog
*/

@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    List<SysOperLog> selectOperLog(@Param("sysOperLogQueryVo") SysOperLogQueryVo sysOperLogQueryVo);

}




