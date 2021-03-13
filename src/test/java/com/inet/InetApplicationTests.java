package com.inet;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.po.Permissions;
import com.inet.code.entity.po.PermissionsUser;
import com.inet.code.entity.po.Role;
import com.inet.code.entity.po.RoleUser;
import com.inet.code.entity.vo.PermissionsInfo;
import com.inet.code.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class InetApplicationTests {

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    @Resource
    private PermissionsService permissionsService;

    @Resource
    private CheckService checkService;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private PermissionsUserService permissionsUserService;

    @Test
    void contextLoads() {

    }


    /**
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
    @Test
    void contextLoads_1() {

    }

    @Test
    void contextLoads_2() {
        System.out.println(roleService.getByName("ordinaryMembers"));
    }

    @Test
    void contextLoads_3() {
        System.out.println(Validator.isMobile("17605818915"));
    }

    @Test
    void contextLoads_4() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Test
    void contextLoads_5() {
        String test = null;
        System.out.println(StrUtil.isBlank(test));
    }

    @Test
    void contextLoads_6() {
        System.out.println(checkService.checkingProgress(new Page<>(1, 10)));
    }

    @Test
    void contextLoads_7() {
        List<PermissionsInfo> permissions = userService.getByAllUserInfo("81b8f9c44fb1e78791e51087b555aa2e").getPermissions();
        for (PermissionsInfo permission:permissions) {
            System.out.println(permission.getPermissionsName());
        }
    }

    @Test
    void amendRole(){
        String userNumber = "2020002331";
        RoleUser roleUser = roleUserService.getByUserId(userNumber);
        roleUser.setRoleId(roleService.getByName("administrator"));
        roleUserService.updateById(roleUser);
    }

    /*
     * agreeSignIn ---> 同意签到
     * checkRanking ---> 查看排名
     * monthRanking ---> 本月排名
     * registered ---> 注册
     */
    @Test
    void amendPermissions(){
        String userNumber = "2020002331";
        String userId = userService.getByNumber(userNumber);
        List<PermissionsUser> list = new ArrayList<>();
        List<String> permissionsNames = new ArrayList<>();
        permissionsNames.add("agreeSignIn");
        permissionsNames.add("checkRanking");
        permissionsNames.add("monthRanking");
        permissionsNames.add("registered");
        for (String permissionsName : permissionsNames){
            list.add(new PermissionsUser(
                    userId
                    ,permissionsService.getByName(permissionsName)
            ));
        }
        permissionsUserService.saveBatch(list);
    }

}
