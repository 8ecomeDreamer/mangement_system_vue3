package com.atguigu.system.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfException extends RuntimeException{

    private String code;
    private String msg;
}
