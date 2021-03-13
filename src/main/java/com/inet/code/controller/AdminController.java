package com.inet.code.controller;


import com.inet.code.custom.AdminCustom;
import com.inet.code.entity.dto.SignUpdate;
import com.inet.code.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;

/**
 * <p>
 * 管理员的请求
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
@Api(tags = {"管理员模块"})
public class AdminController {

    @Resource
    private AdminCustom adminCustom;

    /**
     * 查看已签到但未同意的
     * @author HCY
     * @since 2021/3/8 下午2:23
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.result.Result
    */
    @ApiOperation("查看已签到但未同意的")
    @GetMapping("/checkedNotAgreed")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "页数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "条目数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query")
    })
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(logical = Logical.OR, value = {"agreeSignIn"})
    public Result getCheckedNotAgreed(@RequestHeader(value = "Token",defaultValue = "") String token,
                                      @RequestParam(value = "current",defaultValue = "1") Integer current,
                                      @RequestParam(value = "size",defaultValue = "10") Integer size) {
        return adminCustom.getCheckedNotAgreed(token,current,size,"/admin/checkedNotAgreed");
    }

    /**
     * 同意用户签到
     * @author HCY
     * @since 2021/3/8 下午6:27
     * @param token: 令牌
     * @param signUpdate: 同意用户签到或者拒绝用户签到
     * @return com.inet.code.result.Result
    */
    @ApiOperation("同意用户签到")
    @PutMapping("/agreeSign")
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"agreeSignIn"})
    public Result putAgreeSign(@RequestHeader(value = "Token",defaultValue = "") String token,
                               @RequestBody SignUpdate signUpdate){
        return adminCustom.getAgreedSign(token,signUpdate,"/admin/agreeSign");
    }

    /**
     * 拒绝用户签到
     * @author HCY
     * @since 2021/3/8 下午7:10
     * @param token: 令牌
     * @param signUpdate: 同意用户签到或者拒绝用户签到
     * @return com.inet.code.result.Result
    */
    @ApiOperation("拒绝用户签到")
    @PutMapping("/refusedSign")
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"agreeSignIn"})
    public Result putRefusedSign(@RequestHeader(value = "Token",defaultValue = "") String token,
                                 @RequestBody SignUpdate signUpdate){
        return adminCustom.getRefusedSign(token,signUpdate,"/admin/refusedSign");
    }

    /**
     * 取消签到
     * @author HCY
     * @since 2021/3/8 下午7:26
     * @param token: 令牌
     * @param signUpdate:  同意用户签到或者拒绝用户签到
     * @return com.inet.code.result.Result
    */
    @ApiOperation("取消签到")
    @PutMapping("/cancelSign")
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(logical = Logical.AND, value = {"checkingProgress"})
    public Result putCancelSign(@RequestHeader(value = "Token",defaultValue = "") String token,
                                @RequestBody SignUpdate signUpdate){
        return adminCustom.getCancelSign(token,signUpdate,"/admin/cancelSign");
    }


    /**
     * 查看排名
     * @author HCY
     * @since 2021/3/8 下午7:50
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.result.Result
    */
    @ApiOperation("查看排名")
    @GetMapping("/showRankings")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "页数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "条目数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query")
    })
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(value = {"monthRanking"})
    public Result getShowRankings(@RequestHeader(value = "Token",defaultValue = "") String token,
                                  @RequestParam(value = "current",defaultValue = "1") Integer current,
                                  @RequestParam(value = "size",defaultValue = "10") Integer size){
        return adminCustom.getShowRankings(token,current,size,"/admin/showRankings");
    }

    /**
     * 查看正在签到的用户
     * @author HCY
     * @since 2021/3/10 下午2:17
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.result.Result
    */
    @ApiOperation("查看正在签到的用户")
    @GetMapping("/showSigned")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current",value = "页数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "size",value = "条目数",dataTypeClass = Integer.class,dataType = "Integer",paramType = "query")
    })
    @RequiresRoles(logical = Logical.OR,value = {"administrator","superAdministrator"})
    @RequiresPermissions(value = {"agreeSignIn"})
    public Result getShowSigned(@RequestHeader(value = "Token",defaultValue = "") String token,
                                @RequestParam(value = "current",defaultValue = "1") Integer current,
                                @RequestParam(value = "size",defaultValue = "10") Integer size){
        return adminCustom.getShowSigned(current,size,"/admin/showSigned");
    }
}
