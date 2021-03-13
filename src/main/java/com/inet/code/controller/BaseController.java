package com.inet.code.controller;

import com.inet.code.custom.BaseCustom;
import com.inet.code.entity.dto.Amend;
import com.inet.code.entity.dto.Login;
import com.inet.code.entity.dto.Register;
import com.inet.code.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 通用方法
 * @author HCY
 * @since 2021/3/5 下午1:36
*/
@CrossOrigin
@RestController
@RequestMapping("/base")
@Api(tags = {"通用用户模块"})
public class BaseController {

    @Resource
    private BaseCustom baseCustom;

    /**
     * 注册操作
     * @author HCY
     * @since 2021/3/5 下午9:29
     * @param register: 注册的信息
     * @return com.inet.code.result.Result
    */
    @ApiOperation("注册操作，不需要token")
    @PostMapping(value="/register")
    public Result postRegister(@RequestBody Register register){
        return baseCustom.register(register,"/base/register");
    }


    /**
     * 跟着token获取用户的数据
     * @author HCY
     * @since 2021/3/9 下午3:42
     * @param token: 令牌
     * @return com.inet.code.result.Result
     */
    @ApiOperation("跟着token获取用户的数据")
    @GetMapping("/findUser")
    public Result getFindUser(@RequestHeader(value = "Token",defaultValue = "") String token){
        return baseCustom.getFindUser(token,"/base/findUser");
    }

    /**
     * 通过令牌获取用户的头像数据
     * @author HCY
     * @since 2021/3/9 下午5:50
     * @param token: 令牌
     * @return com.inet.code.result.Result
    */
    @ApiOperation("通过令牌获取用户的头像数据")
    @GetMapping("/amendInfo")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(value = {"modifyUserInformation"})
    public Result getAmendInfo(@RequestHeader(value = "Token",defaultValue = "") String token){
        return baseCustom.getAmendInfo(token,"/base/amendInfo");
    }

    /**
     * 登陆操作
     * @author HCY
     * @since 2021/3/6 上午9:24
     * @param login: 登陆的信息
     * @return com.inet.code.result.Result
    */
    @ApiOperation("登陆操作，不需要token")
    @PostMapping(value = "/login")
    public Result postLogin(@RequestBody Login login){
        return baseCustom.login(login,"/base/login");
    }

    /**
     * 上传图片
     * @author HCY
     * @since 2021/3/9 上午9:36
     * @param file: 文件
     * @return com.inet.code.result.Result
    */
    @ApiOperation("上传图片，不需要token")
    @PostMapping("/upload")
    public Result postUpload(@PathVariable MultipartFile file){
        return baseCustom.getUpload(file,"/base/upload");
    }

    /**
     * 退出操作
     * @author HCY
     * @since 2021/3/6 上午9:46
     * @param token: 令牌
     * @return com.inet.code.result.Result
    */
    @ApiOperation("退出操作")
    @DeleteMapping("/logOut")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"quit"})
    public Result deleteLogOut(@RequestHeader(value = "Token",defaultValue = "") String token){
        return baseCustom.logOut(token,"/base/logOut");
    }

    /**
     * 签到，签退操作
     * @author HCY
     * @since 2021/3/6 上午10:08
     * @param token:令牌
     * @return com.inet.code.result.Result
    */
    @ApiOperation("签到，签退操作")
    @GetMapping("/signing")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"signIn","signOut"})
    public Result getSigning(@RequestHeader(value = "Token",defaultValue = "") String token){
        return baseCustom.signing(token,"/base/signing");
    }

    /**
     * 获取签到的具体数据
     * @author HCY
     * @since 2021/3/9 下午8:34
     * @param token: 令牌
     * @return com.inet.code.result.Result
    */
    @ApiOperation("获取签到的具体数据")
    @GetMapping("/signInfo")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"signIn","signOut"})
    public Result getSignInfo(@RequestHeader(value = "Token",defaultValue = "") String token){
        return baseCustom.getSignInfo(token,"/base/signInfo");
    }

    /**
     * 修改头像
     * @author HCY
     * @since 2021/3/6 下午11:25
     * @param file: 文件
     * @return com.inet.code.result.Result
    */
    @ApiOperation("修改头像")
    @PostMapping("/amendPicture")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.OR, value = {"modifyUserInformation"})
    public Result postAmendPicture(@RequestHeader(value = "Token",defaultValue = "") String token,
                                  @PathVariable MultipartFile file){
        return baseCustom.getAmendPicture(token,file,"/base/amendPicture");
    }

    /**
     * 修改密码
     * @author HCY
     * @since 2021/3/7 下午7:31
     * @param token: 令牌
     * @param amend: 修改密码的实体信息
     * @return com.inet.code.result.Result
     */
    @ApiOperation("修改密码")
    @PutMapping("/amendCipher")
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.OR, value = {"modifyUserInformation"})
    public Result putAmendCipher(@RequestHeader(value = "Token",defaultValue = "") String token,
                                 @RequestBody Amend amend){
        return baseCustom.getAmendCipher(token,amend,"/base/amendCipher");
    }

    /**
     * 分页查看正在签到的用户
     * @author HCY
     * @since 2021/3/8 上午9:07
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目书
     * @return com.inet.code.result.Result
    */
    @ApiOperation("分页查看正在签到的用户")
    @GetMapping("/checkingProgress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "页数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "条目数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query")
    })
    @RequiresRoles(logical = Logical.OR,value = {"administrator", "ordinaryMembers","superAdministrator"})
    @RequiresPermissions(logical = Logical.OR, value = {"checkingProgress"})
    public Result getCheckingProgress(@RequestHeader(value = "Token",defaultValue = "") String token,
                                      @RequestParam(value = "current",defaultValue = "1") Integer current,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size){
        return baseCustom.pageIng(token,current,size,"/base/checkingProgress");
    }
}
