package com.atguigu.system;

import com.atguigu.model.vo.LoginVo;
import com.atguigu.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SysUserTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void login(){
        System.out.println("ssss");
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername("admin1");
        System.out.println(loginVo);
        sysUserService.login(loginVo);
    }
}
