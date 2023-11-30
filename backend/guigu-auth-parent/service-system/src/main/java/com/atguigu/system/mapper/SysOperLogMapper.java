package com.atguigu.system.mapper;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-11-21 18:39:09
* @Entity generator.domain.SysOperLog
*/

@Mapper
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {

    List<SysOperLog> selectOperLog(@Param("sysOperLogQueryVo") SysOperLogQueryVo sysOperLogQueryVo);

}




