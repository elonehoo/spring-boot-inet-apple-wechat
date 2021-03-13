package com.inet.code.mapper;

import com.inet.code.entity.po.Cipher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 密码表 Mapper 接口
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Mapper
public interface CipherMapper extends BaseMapper<Cipher> {

    /**
     * 通过用户序号查找用户密码
     * @author HCY
     * @since 2021/3/7 下午6:18
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Cipher
     */
    Cipher getByNumber(String userId);
}
