package com.coontroller;

import com.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author conlonx
 * @since 2019-08-28
 */
@RestController
@RequestMapping("/auth")
public class TestController {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 测试获取
     */
    @GetMapping(value = "/getUserAuth")
    public String getUserName() {
        return "userAuth";
    }

    @PostMapping(value = "/test01")
    @Transactional(rollbackFor = Exception.class)
    public String test01() {
        sysUserDao.isInsert("");
        int i = 1/0;
        return "userAuth";
    }
}