package com.spring.business.controller;

import com.spring.Annotation.Autowired;
import com.spring.Annotation.Controller;
import com.spring.Annotation.RequestMapping;
import com.spring.Util.Constants;
import com.spring.business.entity.User;
import com.spring.business.service.UserService;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 14:36
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getName", method = Constants.HTTP_REQUEST_GET)
    public String getName() {
        String user = userService.getUser();
        return user;
    }

    @RequestMapping(value = "/getUser", method = Constants.HTTP_REQUEST_GET)
    public User getUser() {
        User user = new User();
        user.setName("fantasy");
        user.setAge(24);
        return user;
    }
}
