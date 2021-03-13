package com.inet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * signIn ---> 签到
 * signOut ---> 签退
 * agreeSignIn ---> 同意签到
 * checkRanking ---> 查看排名
 * checkingProgress ---> 查看正在签到
 * monthRanking ---> 本月排名
 * modifyUserInformation ---> 修改用户数据
 * quit ---> 退出
 * landing ---> 登陆
 * registered ---> 注册
 */

/**
 * 启动文件
 * @author HCY
 * @since 2021/3/3 下午7:46
 */
@SpringBootApplication
@MapperScan("com.inet.code.mapper")
public class InetApplication {
    /**
     * 运行方法
     * @author HCY
     * @since 2021/3/4 下午9:15
     * @param args: 启动参数
     * @return void
    */
    public static void main(String[] args) {
        SpringApplication.run(InetApplication.class, args);
    }

}
