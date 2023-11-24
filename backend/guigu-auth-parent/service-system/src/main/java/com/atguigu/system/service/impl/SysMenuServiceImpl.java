package com.atguigu.system.service.impl;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.SysMenuMergeVo;
import com.atguigu.model.vo.SysMenuQueryVo;
import com.atguigu.system.mapper.SysMenuMapper;
import com.atguigu.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Jim
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-11-21 18:39:09
*/

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

    /**
     *  查询所有菜单信息
     * @param sysMenuQueryVo 用户菜单信息
     * @return List
     */
    @Override
    public List<SysMenu> selectMenuInfo(SysMenuQueryVo sysMenuQueryVo) {
        return sysMenuMapper.selectMenuInfo(sysMenuQueryVo);
    }

    /**
     * 删除菜单信息
     * @param deleteIds
     * @return int
     */
    @Override
    public int deleteMenuInfo(List<String> deleteIds) {
        return sysMenuMapper.deleteMenuInfo(deleteIds);
    }

    /**
     * 添加菜单信息
     * @param sysMenuMergeVo 用户菜单信息
     * @return List
     */
    @Override
    public int insertMenuInfo(SysMenuMergeVo sysMenuMergeVo) {
        return sysMenuMapper.insertMenuInfo(sysMenuMergeVo);
    }

    /**
     * 更新菜单信息
     * @param sysMenuMergeVo
     * @return int
     */
    @Override
    public int updateMenuInfo(SysMenuMergeVo sysMenuMergeVo) {
        return sysMenuMapper.updateMenuInfo(sysMenuMergeVo);
    }
}




