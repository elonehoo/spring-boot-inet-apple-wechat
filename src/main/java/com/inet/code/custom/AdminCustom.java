package com.inet.code.custom;

import com.inet.code.entity.dto.SignUpdate;
import com.inet.code.result.Result;

/**
 * 管理接口
 * @author HCY
 * @since 2021/3/5 上午8:04
*/
public interface AdminCustom {
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
    Result getCheckedNotAgreed(String token, Integer current, Integer size, String path);

    /**
     * 同意签到
     * @author HCY
     * @since 2021/3/8 下午4:51
     * @param token: 令牌
     * @param signUpdate:用户序号
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getAgreedSign(String token, SignUpdate signUpdate, String path);

    /**
     * 拒绝签到
     * @author HCY
     * @since 2021/3/8 下午6:46
     * @param token: 令牌
     * @param signUpdate: 用户序号实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getRefusedSign(String token, SignUpdate signUpdate, String path);

    /**
     * 取消签到
     * @author HCY
     * @since 2021/3/8 下午7:31
     * @param token: 令牌
     * @param signUpdate: 用户序号实体类
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getCancelSign(String token, SignUpdate signUpdate, String path);

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
    Result getShowRankings(String token, Integer current, Integer size, String path);

    /**
     * 查看正在签到的用户
     * @author HCY
     * @since 2021/3/10 下午2:18
     * @param current: 页数
     * @param size: 条目数
     * @param path: URL路径
     * @return com.inet.code.result.Result
    */
    Result getShowSigned(Integer current, Integer size, String path);
}
