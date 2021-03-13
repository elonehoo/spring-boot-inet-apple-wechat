package com.inet.code.service.impl;

import com.inet.code.entity.po.Cipher;
import com.inet.code.mapper.CipherMapper;
import com.inet.code.service.CipherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 密码表 服务实现类
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@Service
public class CipherServiceImpl extends ServiceImpl<CipherMapper, Cipher> implements CipherService {

    @Resource
    private CipherMapper cipherMapper;

    /**
     * 通过用户序号查找用户密码
     * @author HCY
     * @since 2021/3/7 下午6:18
     * @param userId: 用户序号
     * @return com.inet.code.entity.po.Cipher
     */
    @Override
    public Cipher getByUserId(String userId) {
        return cipherMapper.getByNumber(userId);
    }
}
