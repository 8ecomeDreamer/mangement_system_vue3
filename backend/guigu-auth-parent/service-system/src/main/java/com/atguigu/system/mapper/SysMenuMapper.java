package com.atguigu.system.mapper;

import com.atguigu.model.system.SysMenu;
import com.atguigu.model.vo.SysMenuMergeVo;
import com.atguigu.model.vo.SysMenuQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Jim
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-11-21 18:39:09
* @Entity generator.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    List<SysMenu> selectMenuInfo(@Param("sysMenuQueryVo") SysMenuQueryVo sysMenuQueryVo);

    int deleteMenuInfo(@Param("deleteIds") List<String> deleteIds);

    int insertMenuInfo(@Param("sysMenuVo") SysMenuMergeVo sysMenuMergeVo);

    int updateMenuInfo(@Param("sysMenuVo") SysMenuMergeVo sysMenuMergeVo);

}




