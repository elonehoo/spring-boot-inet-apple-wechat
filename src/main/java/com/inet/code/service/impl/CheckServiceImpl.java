package com.inet.code.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.po.Check;
import com.inet.code.entity.vo.CheckIng;
import com.inet.code.entity.vo.Ranking;
import com.inet.code.mapper.CheckMapper;
import com.inet.code.service.CheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 签到表 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements CheckService {

    @Resource
    private CheckMapper checkMapper;

    /**
     * 通过用户序号判断用户的签到状态
     * @author HCY
     * @since 2021/3/6 上午10:49
     * @param userId: 用户序号
     * @return java.lang.Boolean
     */
    @Override
    public Integer getJudgeStateByUserId(String userId) {
        return checkMapper.getJudgeStateByUserId(userId);
    }

    /**
     * 签到操作
     * @author HCY
     * @since 2021/3/6 上午11:09
     * @param userId: 用户序号
     * @return java.lang.String
     */
    @Override
    public String getSigning(String userId) {
        Check check = this.getByUserId(userId);
        check.setStartTime(new Date());
        check.setCheckState(1);
        if (this.updateById(check)) {
            return "签到成功";
        }
        return "签到失败";
    }

    /**
     * 签退操作
     * @author HCY
     * @since 2021/3/6 下午12:11
     * @param userId: 用户序号
     * @return java.lang.String
     */
    @Override
    public String getSignOut(String userId) {
        //获取开始签到的时间
        Check check = this.getByUserId(userId);
        //设置结束时间
        check.setEndTime(new Date());
        //计算总时间
        check.setCheckTotal(
                check.getCheckTotal() +
                DateUtil.between(check.getStartTime(),check.getEndTime(),DateUnit.MINUTE));
        //修改状态
        check.setCheckState(0);
        //进行修改
        if (this.updateById(check)) {
            return "签退成功";
        }
        return "签退失败";
    }

    /**
     * 分页查看正在签到的用户
     * @author HCY
     * @since 2021/3/8 下午12:36
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.CheckIng>
     */
    @Override
    public IPage<CheckIng> checkingProgress(Page<CheckIng> page) {
        return checkMapper.getCheckingProgress(page,2);
    }

    /**
     * 查看已签到但未同意的
     * @author HCY
     * @since 2021/3/8 下午2:23
     * @param current: 页数
     * @param size: 条目数
     * @return com.inet.code.result.Result
     */
    @Override
    public IPage<CheckIng> getCheckedNotAgreed(Integer current, Integer size) {
        return checkMapper.getCheckingProgress(new Page<>(current,size),1);
    }

    /**
     * 通过用户序号查找到签到实体类
     * @author HCY
     * @since 2021/3/6 下午1:24
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Check
    */
    @Override
    public Check getByUserId(String userId) {
        return checkMapper.getByUserId(userId);
    }

    /**
     * 分页查看用户排名
     * @author HCY
     * @since 2021/3/8 下午7:58
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.Ranking>
     */
    @Override
    public IPage<Ranking> getShowRankings(Page<Ranking> page) {
        return checkMapper.showRankings(page);
    }
}
