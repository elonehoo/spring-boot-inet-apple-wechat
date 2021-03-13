package com.inet.code.realm;

import com.inet.code.entity.po.User;
import com.inet.code.entity.token.JwtToken;
import com.inet.code.entity.vo.AllUserInfo;
import com.inet.code.entity.vo.PermissionsInfo;
import com.inet.code.service.UserService;
import com.inet.code.utils.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * INetRealm
 * @author HCY
 * @since 2021/3/5 下午12:56
*/
@Service
public class InetRealm extends AuthorizingRealm{

    private static final Logger LOGGER  = LogManager.getLogger(InetRealm.class);


    private UserService userService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    public UserService setUserService(UserService userService) {
        return this.userService = userService;
    }

    /**
     * 一定需要创建,不然出错
     * @param token token令牌
     * @return 布尔值
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限认证
     * @author HCY
     * @since 2020-10-11
     * @param principalCollection token类
     * @return AuthorizationInfo用户数据
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取
        String userId = JwtUtils.getString(principalCollection.toString(), "userId");
        AllUserInfo user = userService.getByAllUserInfo(userId);
        //创建 simpleAuthorizationInfo 对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加 role 权限
        simpleAuthorizationInfo.addRole(user.getRoleName());
        //数据的转换
        Set<String> permission = new HashSet<>();
        //循环插入
        for (PermissionsInfo resource : user.getPermissions()){
            permission.add(resource.getPermissionsName());
        }
        //添加资源
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 身份验证
     * @author HCY
     * @since 2020-10-11
     * @param authenticationToken token
     * @return AuthenticationInfo 用户信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = authenticationToken.getCredentials().toString();
        //通过解密获得账号和密码
        String userId = JwtUtils.getString(token, "userId");
        AllUserInfo userToken = (AllUserInfo) redisTemplate.opsForValue().get(token);
        //进行查询
        User user = userService.getById(userId);
        if (userToken == null){
            throw new AuthenticationException("用户未登陆");
        }
        if (user == null){
            throw new AuthenticationException("用户不存在");
        }
        if ( ! JwtUtils.verify(token)){
            throw new AuthenticationException("token出现错误");
        }
        return new SimpleAuthenticationInfo(token,token,"my_realm");

    }
}
