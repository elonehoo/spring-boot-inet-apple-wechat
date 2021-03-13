package com.inet.code.service;

import com.inet.code.entity.po.Cipher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 密码表 服务类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
public interface CipherService extends IService<Cipher> {

    /**
     * 通过用户序号查找用户密码
     * @author HCY
     * @since 2021/3/7 下午6:18
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Cipher
    */
    Cipher getByUserId(String userId);
}
