package com.inet.code.custom.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.custom.AdminCustom;
import com.inet.code.entity.dto.SignUpdate;
import com.inet.code.entity.po.Check;
import com.inet.code.entity.vo.CheckIng;
import com.inet.code.entity.vo.Ranking;
import com.inet.code.result.Result;
import com.inet.code.service.CheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理接口的实现类
 * @author HCY
 * @since 2021/3/5 上午8:04
*/
@Service
public class AdminCustomImpl implements AdminCustom {

    @Resource
    private CheckService checkService;

    /**
     * 查看已签到但未同意的
     * @author HCY
     * @since 2021/3/8 下午2:23
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */

    @Override
    public Result getCheckedNotAgreed(String token, Integer current, Integer size, String path) {
        //计算页数
        IPage<CheckIng> page = checkService.getCheckedNotAgreed(current,size);
        return new Result().result200(page,path);
    }

    /**
     * 同意签到
     * @author HCY
     * @since 2021/3/8 下午4:51
     * @param token: 令牌
     * @param signUpdate:用户序号
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getAgreedSign(String token, SignUpdate signUpdate, String path) {
        //判断用户是否在签到未同意状态
        Check check = checkService.getByUserId(signUpdate.getUserId());
        //判断状态是否是1
        if (check.getCheckState() == 1) {
            //同意签到
            check.setCheckState(2);
            //进行修改
            if (checkService.updateById(check)) {
                return new Result().result200("同意签到成功",path);
            }
        }else {
            return new Result().result404("未进行签到操作",path);
        }
        return new Result().result500("同意签到产生异常",path);
    }

    /**
     * 拒绝签到
     * @author HCY
     * @since 2021/3/8 下午6:46
     * @param token: 令牌
     * @param signUpdate: 用户序号实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getRefusedSign(String token, SignUpdate signUpdate, String path) {
        //判断用户是否在签到未同意状态
        Check check = checkService.getByUserId(signUpdate.getUserId());
        //判断状态是否是1
        if (check.getCheckState() == 1) {
            //拒绝签到
            check.setCheckState(0);
            //进行修改
            if (checkService.updateById(check)) {
                return new Result().result200("拒绝签到成功",path);
            }
        }else {
            return new Result().result404("未进行签到操作",path);
        }
        return new Result().result500("拒绝签到产生异常",path);
    }

    /**
     * 取消签到
     * @author HCY
     * @since 2021/3/8 下午7:31
     * @param token: 令牌
     * @param signUpdate: 用户序号实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getCancelSign(String token, SignUpdate signUpdate, String path) {
        //判断用户的签到状态是否是签到状态
        Check check = checkService.getByUserId(signUpdate.getUserId());
        //判断状态是否是2
        if (check.getCheckState() == 2){
            //设置签到状态为未签到
            check.setCheckState(0);
            //进行修改
            if (checkService.updateById(check)) {
                return new Result().result200("取消签到成功",path);
            }
        }else {
            return new Result().result404("未进行签到操作",path);
        }
        return new Result().result500("取消签到产生异常",path);
    }

    /**
     * 查看每月排名
     * @author HCY
     * @since 2021/3/8 下午7:53
     * @param token: 令牌
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getShowRankings(String token, Integer current, Integer size, String path) {
        IPage<Ranking> pages = checkService.getShowRankings(new Page<Ranking>(current,size));
        return new Result().result200(pages,path);
    }

    /**
     * 查看正在签到的用户
     * @author HCY
     * @since 2021/3/10 下午2:18
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.result.Result
     */
    @Override
    public Result getShowSigned(Integer current, Integer size, String path) {
        IPage<CheckIng> page = checkService.checkingProgress(new Page<>(current, size));
        return new Result().result200(page,path);
    }
}
