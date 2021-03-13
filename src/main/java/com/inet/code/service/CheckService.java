package com.inet.code.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.po.Check;
import com.baomidou.mybatisplus.extension.service.IService;
import com.inet.code.entity.vo.CheckIng;
import com.inet.code.entity.vo.Ranking;

/**
 * <p>
 * 签到表 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface CheckService extends IService<Check> {

    /**
     * 通过用户序号判断用户的签到状态
     * @author HCY
     * @since 2021/3/6 上午10:49
     * @param userId: 用户序号
     * @return java.lang.Boolean
    */
    Integer getJudgeStateByUserId(String userId);

    /**
     * 签到操作
     * @author HCY
     * @since 2021/3/6 上午11:09
     * @param userId: 用户序号
     * @return java.lang.String
    */
    String getSigning(String userId);

    /**
     * 签退操作
     * @author HCY
     * @since 2021/3/6 下午12:11
     * @param userId: 用户序号
     * @return java.lang.String
    */
    String getSignOut(String userId);

    /**
     * 分页查看正在签到的用户
     * @author HCY
     * @since 2021/3/8 下午12:36
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.CheckIng>
    */
    IPage<CheckIng> checkingProgress(Page<CheckIng> page);

    /**
     * 查看已签到但未同意的
     * @author HCY
     * @since 2021/3/8 下午2:23
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.result.Result
     */
    IPage<CheckIng> getCheckedNotAgreed(Integer current, Integer size);

    /**
     * 通过用户序号查找签到数据
     * @author HCY
     * @since 2021/3/8 下午5:12
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Check
    */
    Check getByUserId(String userId);

    /**
     * 分页查看用户排名
     * @author HCY
     * @since 2021/3/8 下午7:58
     * @param page:分页对象
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.Ranking>
    */
    IPage<Ranking> getShowRankings(Page<Ranking> page);
}
