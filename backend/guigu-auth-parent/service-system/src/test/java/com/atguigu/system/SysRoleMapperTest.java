package com.atguigu.system;

import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.LoginVo;
import com.atguigu.model.vo.SysUserQueryVo;
import com.atguigu.system.mapper.SysRoleMapper;
import com.atguigu.system.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    /**
     * 查询所有记录
     */
    @Test
    public void findAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole:sysRoles) {
            System.out.println(sysRole);
        }
    }

    /**
     * 添加操作
     */
    @Test
    public void  add(){
        SysRole sysRole = new SysRole();
        sysRole.setId("111");
        sysRole.setRoleName("测试用例1");
        int num = sysRoleMapper.insert(sysRole);
        System.out.println(num);
    }

    /**
     * 修改操作
     */
    @Test
    public void  update(){
//        查询想要修改的记录
        SysRole sysRole = sysRoleMapper.selectById(111);
//        修改记录
        sysRole.setDescription("测试记录1");
//        调用修改方法
        sysRoleMapper.updateById(sysRole);
    }


    /**
     * 逻辑删除
     */
    @Test
    public void  delete(){
        int i = sysRoleMapper.deleteById(111);

    }

    /**
     * 批量逻辑删除
     */
    @Test
    public void  deleteMore(){
        int i = sysRoleMapper.deleteBatchIds(Arrays.asList(1,2,6));

    }

    /**
     * 条件查询
     */
    @Test
    public void  selectBy(){
//        创建queryWrapper对象
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
//        设置queryWrapper
        queryWrapper.like("role_code", "%y%");
//        根据queryWrapper查询列表
        List<SysRole> sysRoles = sysRoleMapper.selectList(queryWrapper);
        System.out.println(sysRoles);

    }

}
