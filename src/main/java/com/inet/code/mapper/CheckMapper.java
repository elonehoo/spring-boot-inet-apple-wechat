package com.inet.code.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.inet.code.entity.po.Check;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.inet.code.entity.vo.CheckIng;
import com.inet.code.entity.vo.Ranking;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 签到表 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface CheckMapper extends BaseMapper<Check> {

    /**
     * 通过用户序号判断用户的签到状态
     * @author HCY
     * @since 2021/3/6 上午10:54
     * @param userId: 用户序号
     * @return java.lang.Integer
    */
    Integer getJudgeStateByUserId(String userId);

    /**
     * 通过用户序号查找到签到实体类
     * @author HCY
     * @since 2021/3/6 下午1:24
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Check
     */
    Check getByUserId(String userId);

    /**
     * 分页查看正在签到的用户
     * @author HCY
     * @since 2021/3/8 下午12:36
     * @param state: 状态
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.CheckIng>
     */
    IPage<CheckIng> getCheckingProgress(Page<CheckIng> page,Integer state);

    /**
     * 分页查看用户排名
     * @author HCY
     * @since 2021/3/8 下午7:58
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.inet.code.entity.vo.Ranking>
     */
    IPage<Ranking> showRankings(Page<Ranking> page);
}
