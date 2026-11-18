package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.LoginInfo;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class LoginController {

    @Autowired
    private EmpService empService;

    //6.1 登录
    @PostMapping("/login")
    public Result getLoginInfo(@RequestBody Emp emp) {
        //记录日志
        log.info("员工来登录啦,{}",emp);
        //调用service层
        LoginInfo loginInfo=empService.getLoginInfo(emp);
        //进行判断
        //返回数据
        return Result.success(loginInfo);
    }
}
