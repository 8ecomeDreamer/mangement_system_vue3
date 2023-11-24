package com.atguigu.system.service;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.SysMenuMergeVo;
import com.atguigu.model.vo.SysMenuQueryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-11-21 18:39:09
*/
public interface SysMenuService extends IService<SysMenu> {

    int insertMenuInfo(SysMenuMergeVo sysMenuMergeVo);

    List<SysMenu> selectMenuInfo(SysMenuQueryVo sysMenuQueryVo);

    int deleteMenuInfo(List<String> deleteIds);

    int updateMenuInfo(SysMenuMergeVo sysMenuMergeVo);
}
