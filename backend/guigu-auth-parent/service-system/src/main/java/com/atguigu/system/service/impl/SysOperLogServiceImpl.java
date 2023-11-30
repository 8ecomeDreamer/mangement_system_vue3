package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.system.SysOperLog;
import com.atguigu.model.vo.SysOperLogQueryVo;
import com.atguigu.system.mapper.SysOperLogMapper;
import com.atguigu.system.mapper.SysOperLogMapper;
import com.atguigu.system.service.SysOperLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Jim
* @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Service实现
* @createDate 2023-11-25 16:52:41
*/
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog>
    implements SysOperLogService {
    @Resource
    SysOperLogMapper sysOperLogMapper;

    /**
     *  查询所有角色信息
     * @param sysOperLogQueryVo 用户角色信息
     * @return List
     */
//    @Override
    public List<SysOperLog> selectOperLog(SysOperLogQueryVo sysOperLogQueryVo) {
        return sysOperLogMapper.selectOperLog(sysOperLogQueryVo);
    }

}




