package com.inet.code.controller;


import com.inet.code.custom.UsersCustom;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户的请求
 * </p>
 *
 * @author HCY
 * @since 2021-03-04
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersCustom usersCustom;
}
